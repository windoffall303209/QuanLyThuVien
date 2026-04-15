package com.quanlythuvien.exception;

// Biểu diễn lỗi không tìm thấy dữ liệu yêu cầu.
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
