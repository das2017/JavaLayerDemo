package org.zzj.common.custom;

import org.zzj.common.constants.ApiCode;
import org.zzj.dto.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.StringJoiner;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public ApiResponse handleAppException(ApiException ex) {
        String msg = String.format("AppException消息:%s", ex.getMessage());
        log.error(msg, ex);
        return new ApiResponse(ex.getCode(), ex.getMessage(), ex.getData());
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public ApiResponse handleBindException(BindException ex) {
        String msg = String.format("BindException消息:%s", ex.getMessage());
        log.error(msg, ex);
        return new ApiResponse(ApiCode.ERROR, ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ApiResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String msg = String.format("MethodArgumentNotValidException消息:%s", ex.getMessage());
        log.error(msg, ex);

        //返回非法字段名称error.getField()，字段值error.getRejectedValue()，错误消息error.getDefaultMessage()
        StringJoiner sj = new StringJoiner(";");
        ex.getBindingResult().getFieldErrors().forEach(x -> sj.add(x.getDefaultMessage()));
        return new ApiResponse(ApiCode.ERROR, sj.toString(), ex.getBindingResult().getFieldErrors());
    }

    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ApiResponse handleConstraintViolationException(ConstraintViolationException ex) {
        String msg = String.format("ConstraintViolationException消息:%s", ex.getMessage());
        log.error(msg, ex);

        StringJoiner sj = new StringJoiner(";");
        ex.getConstraintViolations().forEach(x -> sj.add(x.getMessageTemplate()));
        return new ApiResponse(ApiCode.ERROR, sj.toString());
    }

    @ResponseBody
    @ExceptionHandler(value = ValidationException.class)
    public ApiResponse handleValidationException(ValidationException ex) {
        String msg = String.format("ValidationException消息:%s", ex.getMessage());
        log.error(msg, ex);

        return new ApiResponse(ApiCode.ERROR, ex.getCause().getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = Throwable.class)
    public ApiResponse handleException(HttpServletRequest req, HttpServletResponse resp, Exception ex) {
        String logMsg = String.format("GlobalException消息:%s", ex.getMessage());
        log.error(logMsg, ex);
        //String stackTrace = getStackTrace(ex);
        String tip = String.format(ApiCode.ERRMSG_DETAIL, ex.getMessage());
        return new ApiResponse(ApiCode.ERROR, tip);
    }

    private static String getStackTrace(Throwable aThrowable) {
        StringWriter result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        result.flush();
        printWriter.flush();
        return result.toString();
    }
}
