package com.vm.ec.starter.exception;

public enum ErrorCode {

    // 4xx: Client Errors
    BAD_REQUEST("ERR_400", "Bad Request"),
    UNAUTHORIZED("ERR_401", "Unauthorized"),
    FORBIDDEN("ERR_403", "Forbidden"),
    NOT_FOUND("ERR_404", "Resource Not Found"),
    VALIDATION_ERROR("ERR_422", "Validation Failed"),

    // 5xx: Server Errors
    INTERNAL_SERVER_ERROR("ERR_500", "Internal Server Error"),
    SERVICE_UNAVAILABLE("ERR_503", "Service Unavailable"),
    TIMEOUT("ERR_504", "Request Timeout"),

    // Custom Domain Errors
    THIRD_PARTY_API_ERROR("ERR_510", "Third-Party API Failure"),
    DATABASE_ERROR("ERR_520", "Database Operation Failed"),
    CONFIGURATION_ERROR("ERR_530", "Configuration Error"),

    GENERIC_ERROR("ERR_999", "Generic Error");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }
}
