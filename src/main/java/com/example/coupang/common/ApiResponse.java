package com.example.coupang.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.example.coupang.common.ResponseStatus.SUCCESS;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ApiResponse<T> {
    private Integer code;
    private String message;
    private ResponseStatus status;
    private T data;

    public ApiResponse(Integer code, ResponseStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public ApiResponse(Integer code, ResponseStatus status, String message, T data) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
    }
    public static <T> ApiResponse<T> SUCCESS (Integer code, String message) {
        return new ApiResponse<>(code, SUCCESS, message);
    }
    public static <T> ApiResponse<T> SUCCESS (Integer code, String message, T data) {
        return new ApiResponse<>(code, SUCCESS, message, data);
    }

    public static <T> ApiResponse<T> FAILURE (Integer code, String message) {
        return new ApiResponse<>(code, ResponseStatus.FAIL, message);
    }

    public static <T> ApiResponse<T> ERROR (Integer code, String message) {
        return new ApiResponse<>(code, ResponseStatus.ERROR, message);
    }
}
