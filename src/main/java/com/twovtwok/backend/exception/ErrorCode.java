package com.twovtwok.backend.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode implements ServiceErrorCode {

    USER_ALREADY_EXIST(HttpStatus.BAD_REQUEST),
    PHONE_NUMBER_ALREADY_IN_USE(HttpStatus.BAD_REQUEST),
    ACCOUNT_ALREADY_VERIFIED(HttpStatus.BAD_REQUEST),
    ACCOUNT_VERIFICATION_FAILED(HttpStatus.UNAUTHORIZED),
    AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED),
    ILLEGAL_ACCOUNT_STATE(HttpStatus.CONFLICT);

    private final HttpStatus httpStatus;
}
