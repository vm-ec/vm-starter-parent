package com.vm.ec.starter.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private final ErrorCode errorCode;

    public ApiException(ErrorCode errorCode) {
        super(errorCode.message());
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode.code();
    }

    @Override
    public String getMessage() {
        return errorCode.message();
    }

}
