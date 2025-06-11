package com.example.swp.security;

import com.example.swp.service.Impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    // 1. Cấu hình filter chain cho security
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/","/home","/register","/service","/service-details","/service-booking","/booking-success"
                                ,"/forgot-password","/service","/reset-password"
                                ,"/login", "/css/**", "/js/**", "/images/**")
                        .permitAll()
                        .requestMatchers("/admin-manage").hasAuthority("ADMIN") // 👈 THÊM DÒNG NÀY
                        .anyRequest().authenticated()// public routes
                )
                .formLogin(form -> form
                        .loginPage("/login")             // Trang login tùy chỉnh
                        .successHandler(customAuthenticationSuccessHandler) // Chuyển hướng sau đăng nhập
                        .failureHandler(customAuthenticationFailureHandler)
                        .permitAll()
                ).logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                ).exceptionHandling(exception -> exception
                        .accessDeniedPage("/403")
                );

        return http.build();
    }

    // 2. Cấu hình AuthenticationManager sử dụng UserDetailsService
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder()); // Dùng plain-text password

        return authBuilder.build();
    }

    // 3. Dùng NoOpPasswordEncoder: không mã hóa mật khẩu
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
