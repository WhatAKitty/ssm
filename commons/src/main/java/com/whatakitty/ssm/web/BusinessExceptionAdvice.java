package com.whatakitty.ssm.web;

import com.whatakitty.ssm.exception.BusinessException;
import com.whatakitty.ssm.response.BodyBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created with IntelliJ IDEA.
 *
 * @author xuqiang
 * @date 2018/04/09
 * @description
 **/
@ControllerAdvice
public class BusinessExceptionAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity businessException(final BusinessException e) {
        return new ResponseEntity(BodyBuilder.create().addError(e.getMessage()).build(), HttpStatus.valueOf(e.getCode()));
    }

}
