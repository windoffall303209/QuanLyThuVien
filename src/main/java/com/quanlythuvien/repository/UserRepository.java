package com.quanlythuvien.repository;

import com.quanlythuvien.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

// Truy cập dữ liệu tài khoản đăng nhập trong cơ sở dữ liệu.
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
