package com.huashi.cloud.common.exception;

import com.huashi.cloud.common.exception.result.BusinessExceptionResult;

public class BusinessException extends RuntimeException {
    BusinessExceptionResult businessExceptionResult;

    public BusinessExceptionResult getBusinessExceptionResult() {
        return businessExceptionResult;
    }

    public void setBusinessExceptionResult(BusinessExceptionResult businessExceptionResult) {
        this.businessExceptionResult = businessExceptionResult;
    }

    public BusinessException(BusinessExceptionResult businessExceptionResult) {
        this.businessExceptionResult = businessExceptionResult;
    }

    public BusinessException(String message, BusinessExceptionResult businessExceptionResult) {
        super(message);
        this.businessExceptionResult = businessExceptionResult;
    }
}
