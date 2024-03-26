package com.twovtwok.backend.exception;


public class UserAuthenticationException extends ServiceException {
    public UserAuthenticationException(String message) {
        super(message);
    }

    @Override
    public ServiceErrorCode getErrorCode() {
        return ErrorCode.AUTHENTICATION_FAILED;
    }
}
