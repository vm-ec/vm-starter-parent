package com.vm.ec.starter.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * ErrorResponse class represents the structure of the error response returned to the client.
 * It contains a timestamp, an error message, and an error code.
 */

@Data
@AllArgsConstructor
public class ErrorResponse {

    private LocalDateTime timestamp;
    private String message;
    private String code;
    private String path;
}
