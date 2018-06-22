package com.huashi.cloud.common.exception.converter;


import com.huashi.cloud.common.exception.ValidationException;
import com.huashi.cloud.common.exception.result.BusinessExceptionResult;
import com.huashi.cloud.common.exception.result.ExceptionResult;
import com.huashi.cloud.common.exception.result.ValidationExceptionResult;
import com.huashi.cloud.common.result.ResultData;
import com.huashi.cloud.common.result.ResultState;

public class ExceptionResultConverter implements ExceptionConverter<ResultData> {

    private ExceptionResult exceptionResult;

    public ExceptionResultConverter(ExceptionResult exceptionResult) {
        this.exceptionResult = exceptionResult;
    }

    @Override
    public ResultData convert2ResultData() {
        ResultData resultData = ResultData.FAIL();
        ResultState state = exceptionResult.getState();
        Object detail = exceptionResult.getDetail();
        if (ResultState.BUSINESS_ERROR.equals(state) && detail instanceof BusinessExceptionResult)
            resultData.setInfo(((BusinessExceptionResult) detail).getMessage());
        if (ResultState.PARAMETER_INVALID.equals(state) && detail instanceof ValidationExceptionResult)
            resultData.setInfo(((ValidationExceptionResult) detail).getMessage());
        return resultData;
    }
}