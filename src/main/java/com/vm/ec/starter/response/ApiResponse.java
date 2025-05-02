package com.vm.ec.starter.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {

    private boolean success;
    private String message;
    public Object data;

    public static ApiResponse ok(Object data, String message) {
        return new ApiResponse(true, message, data);
    }

    public static ApiResponse fail(String message) {
        return new ApiResponse(false, message, null);
    }
}
