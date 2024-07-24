package com.yennth.demo.btvn.B2_SanPham_LoaiSP.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class B2_SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Tắt CSRF protection
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/de2/san-pham/**").authenticated() // Bảo vệ API hiển thị danh sách Sách
                                .anyRequest().permitAll() // Cho phép tất cả các yêu cầu khác không cần xác thực
                )
                .httpBasic(Customizer.withDefaults()); // Sử dụng HTTP Basic Authentication với cấu hình mặc định
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("HangNT169") // Tên đăng nhập mặc định
                .password("123@123") // Mật khẩu mặc định
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
