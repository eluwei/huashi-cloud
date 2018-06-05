package com.huashi.cloud.common.exception;


import com.huashi.cloud.common.exception.converter.ExceptionResultConverter;
import com.huashi.cloud.common.exception.result.BusinessExceptionResult;
import com.huashi.cloud.common.exception.result.ExceptionResult;
import com.huashi.cloud.common.result.ResultData;
import com.huashi.cloud.common.result.ResultState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.ResourceAccessException;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    protected static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 所有异常报错
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResultData allExceptionHandler(HttpServletRequest request,
                                          Exception exception) {
        StringBuilder exceptionInfo = new StringBuilder();
        String lineSeparator = System.getProperty("line.separator");
        exceptionInfo.append("程序异常：");
        // exceptionInfo.append(Sequence.get());
        exceptionInfo.append(lineSeparator);
        exceptionInfo.append(exception.getLocalizedMessage());
        exceptionInfo.append(lineSeparator);
        exceptionInfo.append(exception.getCause());
        exceptionInfo.append(lineSeparator);
        exceptionInfo.append(exception.getSuppressed());
        exceptionInfo.append(lineSeparator);
        exceptionInfo.append(exception.getMessage());
        exceptionInfo.append(lineSeparator);

        StackTraceElement[] stackTraceElements = exception.getStackTrace();
        for (StackTraceElement element : stackTraceElements) {
            exceptionInfo.append("    " + element.toString());
            exceptionInfo.append(lineSeparator);
        }
        logger.error(exceptionInfo.toString());
        return  new ExceptionResultConverter(new ExceptionResult<>(ResultState.INTERNAL_SERVER_ERROR,exceptionInfo.toString(),"服务器异常，请联系管理员！")).convert2ResultData();
    }

    /**
     * 业务异常
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BusinessException.class)
    public ResultData businessExceptionHandller(HttpServletRequest request,
                                                BusinessException exception) {
        ExceptionResult<BusinessExceptionResult> result =
                new ExceptionResult<>(ResultState.BUSINESS_ERROR, exception.getMessage(), exception.getBusinessExceptionResult());
        return new ExceptionResultConverter(result).convert2ResultData();
    }

    @ExceptionHandler(value = ResourceAccessException.class)
    public ResultData resourceAccessExceptionHandller(HttpServletRequest request,
                                                      ResourceAccessException exception) {
        return new ExceptionResultConverter(new ExceptionResult<>(ResultState.INTERNAL_SERVER_ERROR, exception.getMessage(),"服务器异常，请联系管理员！")).convert2ResultData();
    }

    /**
     * 数据校验异常
     *
     * @param request
     * @param exception
     * @return
     * @throws ValidationException
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ValidationException.class)
    public ResultData validationExceptionHandller(HttpServletRequest request,
                                                  ValidationException exception) {

        return new ExceptionResultConverter(new ExceptionResult<>(ResultState.PARAMETER_INVALID, exception.getMessage(), exception.getValidationResult())).convert2ResultData();
    }
}
