package com.example.cinemamanagement.configurations;

import com.example.cinemamanagement.filters.JwtAuthenticationFilter;
import com.example.cinemamanagement.utils.CustomAccessDeniedHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Thêm để sử dụng @PreAuthorize
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable) // Tắt giao diện form login
                // Request đi qua bộ lọc này trước
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> {
                    requests
                            .requestMatchers(HttpMethod.POST, "/api/v1/users/register").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/v1/users/login").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/v1/users/logout").permitAll()
                            .requestMatchers(HttpMethod.POST, "/api/v1/users/refreshToken").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/categories/**").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/products/**").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/movieTypes/**").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/movies/**").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/vouchers/*").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/rooms/**").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/showTimes/**").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/seats/**").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/bills/*").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/bills/users/*").permitAll()
                            .anyRequest().authenticated(); // Các api khác chỉ cần đăng nhập là call được
                })
                // Không lưu token ở session phía server
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Custom message khi gặp lỗi 403
                .exceptionHandling(config -> config.accessDeniedHandler(customAccessDeniedHandler)); // Nếu dùng @PreAuthorize thì k dùng được cái này
        return http.build();
    }
}
