package com.quanlythuvien.exception;

// Biểu diễn lỗi nghiệp vụ cần báo lại cho người dùng.
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
