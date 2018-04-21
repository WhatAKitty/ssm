package com.whatakitty.ssm.web;

import com.alibaba.fastjson.JSON;
import com.whatakitty.ssm.exception.BusinessException;
import com.whatakitty.ssm.response.BodyBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

/**
 * Created with IntelliJ IDEA.
 *
 * @author xuqiang
 * @date 2018/04/09
 * @description
 **/
@Log4j2
@ControllerAdvice
public class BusinessExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity businessException(final BusinessException e) {
        return new ResponseEntity(BodyBuilder.create().addError(e.getMessage()).build(), HttpStatus.valueOf(e.getCode()));
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
        HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (logger.isErrorEnabled()) {
            logger.error(ex.getMessage(), ex);
        }

        return new ResponseEntity(BodyBuilder.create().addError(ex.getMessage()).build(), status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        // TODO do bad request customize
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }
}
