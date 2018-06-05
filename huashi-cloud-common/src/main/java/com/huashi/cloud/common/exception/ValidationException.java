package com.huashi.cloud.common.exception;

import com.huashi.cloud.common.exception.result.ValidationExceptionResult;

public class ValidationException extends RuntimeException {

    ValidationExceptionResult validationResult;

    public ValidationExceptionResult getValidationResult() {
        return validationResult;
    }

    public ValidationException(ValidationExceptionResult validationResult) {
        this.validationResult = validationResult;
    }

    public ValidationException(String message, ValidationExceptionResult validationResult) {
        super(message);
        this.validationResult = validationResult;
    }
}



