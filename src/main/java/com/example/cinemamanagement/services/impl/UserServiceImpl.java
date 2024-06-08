package com.example.cinemamanagement.services.impl;

import com.example.cinemamanagement.dtos.LoginDTO;
import com.example.cinemamanagement.dtos.LogoutDTO;
import com.example.cinemamanagement.dtos.RefreshTokenDTO;
import com.example.cinemamanagement.dtos.RegisterDTO;
import com.example.cinemamanagement.exceptions.DuplicateValueException;
import com.example.cinemamanagement.exceptions.ExpiredTokenException;
import com.example.cinemamanagement.exceptions.ResourceNotFoundException;
import com.example.cinemamanagement.models.Token;
import com.example.cinemamanagement.models.User;
import com.example.cinemamanagement.models.UserRole;
import com.example.cinemamanagement.repositories.RoleRepository;
import com.example.cinemamanagement.repositories.UserRepository;
import com.example.cinemamanagement.repositories.UserRoleRepository;
import com.example.cinemamanagement.responses.LoginResponse;
import com.example.cinemamanagement.responses.UserResponse;
import com.example.cinemamanagement.services.JwtService;
import com.example.cinemamanagement.services.TokenService;
import com.example.cinemamanagement.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Security;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    @Value("${jwt.refreshExpiration}")
    private int refreshExpiration;
    @Override
    @Transactional // Nếu xảy ra lỗi trong quá trình xử lý -> rollback
    public UserResponse createUser(RegisterDTO registerDTO) {
        if(userRepository.existsByUsername(registerDTO.getUsername()))
            throw new DuplicateValueException("Username already exists");
        for(String role : registerDTO.getRoles()){
            if(roleRepository.findByRoleName(role).isEmpty())
                throw new ResourceNotFoundException("Cannot find role with roleName = " + role);
        }
        User user = User.builder()
                .firstName(registerDTO.getFirstName())
                .lastName(registerDTO.getLastName())
                .phone(registerDTO.getPhone())
                .email(registerDTO.getEmail())
                .gender(registerDTO.isGender())
                .username(registerDTO.getUsername())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .active(registerDTO.isActive())
                .build();
        userRepository.save(user);
        List<UserRole> userRoles = new ArrayList<>();
        for(String role : registerDTO.getRoles()){
            UserRole userRole = UserRole.builder()
                    .user(user)
                    .role(roleRepository.findByRoleName(role).get())
                    .build();
            userRoleRepository.save(userRole);
            userRoles.add(userRole);
        }
        user.setUserRoles(userRoles);
        return UserResponse.fromUser(user);
    }

    @Override
    public UserResponse getUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return UserResponse.fromUser(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::fromUser)
                .toList();
    }

    @Override
    @Transactional
    public UserResponse updateUser(long id, RegisterDTO registerDTO) {
//        User user = getUserById(id);
//        if(!user.getUsername().equals(registerDTO.getUsername())
//                && userRepository.existsByUsername(registerDTO.getUsername())){
//            throw new DuplicateValueException("Username already exists");
//        }
//        user.setFirstName(registerDTO.getFirstName());
//        user.setLastName(registerDTO.getLastName());
//        user.setPhone(registerDTO.getPhone());
//        user.setEmail(registerDTO.getEmail());
//        user.setGender(registerDTO.isGender());
//        user.setUsername(registerDTO.getUsername());
//        user.setPassword(registerDTO.getPassword());
//        user.setActive(registerDTO.isActive());
//        return userRepository.save(user);
        return null;
    }

    @Override
    public LoginResponse login(LoginDTO loginDTO, HttpServletRequest request) {

//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

        User user = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Invalid Username or Password"));
        if(!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword()))
            throw new BadCredentialsException("Invalid Username or Password");
        String token = jwtService.generateToken(user);
        boolean isMobile = request.getHeader("User-Agent").equals("mobile");
        Token newToken =tokenService.addToken(user, token, isMobile);
        return LoginResponse.builder()
                .token(newToken.getToken())
                .refreshToken(newToken.getRefreshToken())
                .id(user.getId())
                .username(user.getUsername())
                .roles(user.getUserRoles().stream()
                        .map(userRole -> userRole.getRole().getRoleName())
                        .toList())
                .build();
    }

    @Override
    public void logout(LogoutDTO logoutDTO) {
        tokenService.deleteToken(logoutDTO.getToken());
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenDTO refreshTokenDTO) {
        Token token = tokenService.getTokenByRefreshToken(refreshTokenDTO.getRefreshToken());
        if(token.getRefreshExpirationDate().isBefore(LocalDateTime.now()))
            throw new ExpiredTokenException("RefreshToken expired");
        User user = token.getUser();
        Token newToken = tokenService.updateToken(Token.builder()
                        .id(token.getId())
                .token(jwtService.generateToken(user))
                .refreshToken(UUID.randomUUID().toString())
                .refreshExpirationDate(LocalDateTime.now().plusSeconds(refreshExpiration/1000))
                .user(user)
                .isMobileDevice(token.isMobileDevice())
                .build());
        return LoginResponse.builder()
                .token(newToken.getToken())
                .refreshToken(newToken.getRefreshToken())
                .id(user.getId())
                .username(user.getUsername())
                .roles(user.getUserRoles().stream()
                        .map(userRole -> userRole.getRole().getRoleName())
                        .toList())
                .build();
    }
}
