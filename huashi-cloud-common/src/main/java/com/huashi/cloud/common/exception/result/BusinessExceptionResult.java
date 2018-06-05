package com.huashi.cloud.common.exception.result;

public class BusinessExceptionResult {
    String code;
    String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BusinessExceptionResult(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
