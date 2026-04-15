package com.quanlythuvien.config;

import com.quanlythuvien.domain.Role;
import com.quanlythuvien.domain.User;
import com.quanlythuvien.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
// Khởi tạo dữ liệu mẫu cho môi trường ứng dụng.
public class DataSeeder {

    @Bean
    CommandLineRunner seedUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setFullName("Quản trị viên");
                admin.setPasswordHash(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                admin.setEnabled(true);
                userRepository.save(admin);
            }

            if (userRepository.findByUsername("librarian").isEmpty()) {
                User librarian = new User();
                librarian.setUsername("librarian");
                librarian.setFullName("Thủ thư");
                librarian.setPasswordHash(passwordEncoder.encode("lib123"));
                librarian.setRole(Role.LIBRARIAN);
                librarian.setEnabled(true);
                userRepository.save(librarian);
            }
        };
    }
}
