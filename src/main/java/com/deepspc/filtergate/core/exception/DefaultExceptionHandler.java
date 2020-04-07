package com.deepspc.filtergate.core.exception;

import com.deepspc.filtergate.core.reqres.response.ErrorResponseData;
import com.deepspc.filtergate.core.reqres.response.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static com.deepspc.filtergate.core.common.constant.AopSortConstants.DEFAULT_EXCEPTION_HANDLER_SORT;

@ControllerAdvice
@Order(DEFAULT_EXCEPTION_HANDLER_SORT)
public class DefaultExceptionHandler {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截运行时异常
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseData notFount(ServiceException e) {
        log.error("运行时异常:", e);
        return new ErrorResponseData(e.getCode(), e.getErrorMessage());
    }

    /**
     * 拦截未知异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseData serverError(Exception e) {
        log.error("系统异常:", e);
        return new ErrorResponseData(CoreExceptionEnum.SERVICE_ERROR.getCode(), CoreExceptionEnum.SERVICE_ERROR.getMessage());
    }

    /**
     * 拦截字段校验异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseData fieldValidError(BindException e) {
        log.error("字段校验异常:", e);
        List<ObjectError> errorList = e.getAllErrors();
        String message = "";
        int i = 0;
        for(ObjectError error : errorList){
            if (i > 0) {
                message += "；";
            }
            message += error.getDefaultMessage() + "<br/>";
            i++;
        }
        return new ErrorResponseData(CoreExceptionEnum.SERVICE_ERROR.getCode(), message);
    }
}
