package com.vm.ec.starter.response;

import com.vm.ec.starter.exception.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * ApiResponse is a generic class that represents the structure of API responses.
 * It includes fields for success status, HTTP status, message, data, timestamp,
 * errors, trace ID, and additional metadata.
 *
 * @param <T> The type of the data returned in the response
 * @author Gurpreet Singh
 *
 */
@Data
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean success;                // Indicates if the request was successful
    private HttpStatus httpStatus;          // HTTP status code for the response
    private String message;                 // Message describing the response
    private T data;                         // Data returned in the response
    private LocalDateTime timestamp;        // ISO timestamp when response was created
    private List<ErrorResponse> errors;     // List of field or validation errors
    private String traceId;                 // Unique identifier for the request trace
    private Map<String, Object> meta;       // Extra details (pagination, count, etc.)

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(true, HttpStatus.OK, "OK", data,
                LocalDateTime.now(), null, null, null);
    }

    public static <T> ApiResponse<T> ok(String message, T data) {
        return new ApiResponse<>(true, HttpStatus.OK, message, data,
                LocalDateTime.now(), null, null, null);
    }

    public static <T> ApiResponse<T> ok(String message) {
        return new ApiResponse<>(true, HttpStatus.OK, message, null,
                LocalDateTime.now(), null, null, null);
    }

    public static <T> ApiResponse<T> fail(String message, List<ErrorResponse> errors) {
        return new ApiResponse<>(false, HttpStatus.BAD_REQUEST, message, null,
                LocalDateTime.now(), errors, null, null);
    }

    public static <T> ApiResponse<T> fail(HttpStatus status, String message) {
        return new ApiResponse<>(false, status, message, null,
                LocalDateTime.now(), null, null, null);
    }
}
