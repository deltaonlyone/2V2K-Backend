package com.twovtwok.backend.exception;


public class UserAlreadyExistException extends ServiceException {

    public UserAlreadyExistException(String message) {
        super(message);
    }


    @Override
    public ServiceErrorCode getErrorCode() {
        return ErrorCode.USER_ALREADY_EXIST;
    }
}
