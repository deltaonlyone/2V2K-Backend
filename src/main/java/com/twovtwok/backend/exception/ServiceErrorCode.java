package com.twovtwok.backend.exception;

import org.springframework.http.HttpStatus;

public interface ServiceErrorCode {

    /**
     * Error code as {@link Object#toString()}
     * <p>
     * Expected that it will be in the snake case format, e.g.: VALIDATION_ERROR
     */
    default String getCode() {
        return this.toString();
    }

    HttpStatus getHttpStatus();
}
