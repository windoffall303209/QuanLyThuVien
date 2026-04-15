package com.quanlythuvien;

import com.quanlythuvien.config.properties.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
// Khởi động ứng dụng quản lý thư viện.
public class QuanLyThuVienApplication {

    // Xử lý chức năng main của lớp hiện tại.
    public static void main(String[] args) {
        SpringApplication.run(QuanLyThuVienApplication.class, args);
    }
}
