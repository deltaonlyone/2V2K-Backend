package com.twovtwok.backend.exception;

import org.slf4j.helpers.MessageFormatter;

public abstract class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String messagePattern, Object... args) {
        super(MessageFormatter.arrayFormat(messagePattern, args).getMessage());
    }

    public abstract ServiceErrorCode getErrorCode();
}
