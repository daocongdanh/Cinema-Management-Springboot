package com.example.cinemamanagement.filters;

import com.example.cinemamanagement.services.JwtService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        try{
            String authHeader = request.getHeader("Authorization");
            if(authHeader != null && authHeader.startsWith("Bearer ")){
                String token = authHeader.substring(7); // Lấy ra token
                String username = jwtService.extractUsername(token); // Lấy username từ token
                // Lấy ra đối tượng UserDetails
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if(jwtService.isTokenValid(token, userDetails)){
                    // 2 tham số -> mặc định authenticated false
//                public UsernamePasswordAuthenticationToken(Object principal, Object credentials) {
//                    super((Collection)null);
//                    this.principal = principal;
//                    this.credentials = credentials;
//                    this.setAuthenticated(false);
//                }
                    // 3 tham số có quyền -> mặc định authenticated true
//                public UsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority > authorities) {
//                    super(authorities);
//                    this.principal = principal;
//                    this.credentials = credentials;
//                    super.setAuthenticated(true);
//                }
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            filterChain.doFilter(request,response);
        }
        catch (JwtException e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid token");
        }
        catch (Exception e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Authentication failed");
        }
    }

    // Không cần cho lắm :v
//    private boolean isBypassToken(@NonNull HttpServletRequest request){
//        List<Pair<String, String>> byPassTokens = Arrays.asList(
//                Pair.of("/api/v1/users/register", "POST"),
//                Pair.of("/api/v1/users/login", "POST")
//        );
//        String requestPath = request.getServletPath();
//        String requestMethod = request.getMethod();
//        for(Pair<String, String> token : byPassTokens){
//            String path = token.getFirst();
//            String method = token.getSecond();
//            if(requestPath.matches(path.replace("**", ".*")) // regular expression
//                && requestMethod.equalsIgnoreCase(method))
//                return true;
//        }
//        return false;
//    }
}
