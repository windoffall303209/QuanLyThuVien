package com.quanlythuvien.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
// Ánh xạ các cấu hình nghiệp vụ từ application.yml.
public record AppProperties(int defaultBorrowDays) {
}
