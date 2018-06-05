package com.huashi.cloud.common.exception;

import com.huashi.cloud.common.exception.result.FieldAccessExceptionResult;

public class FieldAccessException extends RuntimeException {

    FieldAccessExceptionResult fieldAccessExceptionResult;

    public FieldAccessException(FieldAccessExceptionResult fieldAccessExceptionResult) {
        this.fieldAccessExceptionResult = fieldAccessExceptionResult;
    }

    public FieldAccessException(String message, FieldAccessExceptionResult fieldAccessExceptionResult) {
        super(message);
        this.fieldAccessExceptionResult = fieldAccessExceptionResult;
    }


    public FieldAccessExceptionResult getFieldAccessExceptionResult() {
        return fieldAccessExceptionResult;
    }

    public void setFieldAccessExceptionResult(FieldAccessExceptionResult fieldAccessExceptionResult) {
        this.fieldAccessExceptionResult = fieldAccessExceptionResult;
    }
}
