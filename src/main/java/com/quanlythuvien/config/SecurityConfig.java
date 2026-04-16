package com.quanlythuvien.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
// Cấu hình xác thực và phân quyền cho ứng dụng.
public class SecurityConfig {

    @Bean
    // Xử lý chức năng securityFilterChain của lớp hiện tại.
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/login").permitAll()
                        .requestMatchers("/", "/dashboard",
                                "/sach/**", "/thanh-vien/**", "/muon-tra/**",
                                "/chi-nhanh/**", "/ban-sao/**", "/thu-thu/**",
                                "/ca-lam/**", "/cham-cong/**",
                                "/nha-cung-cap/**", "/cung-cap/**",
                                "/giao-trinh/**", "/tieu-thuyet/**", "/tap-chi/**")
                        .hasAnyRole("ADMIN", "LIBRARIAN")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/login?error")
                        .permitAll())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll())
                .rememberMe(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    @Primary
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        return new InMemoryUserDetailsManager(
                User.withUsername("admin")
                        .password(passwordEncoder.encode("admin123"))
                        .roles("ADMIN")
                        .build(),
                User.withUsername("librarian")
                        .password(passwordEncoder.encode("lib123"))
                        .roles("LIBRARIAN")
                        .build()
        );
    }

    @Bean
    // Xử lý chức năng passwordEncoder của lớp hiện tại.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
