//package com.whatakitty.ssm.exception;
//
//import com.gnet.commons.response.BodyBuilder;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
///**
// * 业务错误断点
// *
// * @author xuqiang
// * @date 2018/01/12
// * @description
// **/
//@RestControllerAdvice(basePackages = "com.gnet")
//public class BusinessExceptionAdvice {
//
//    @ExceptionHandler(BusinessException.class)
//    public ResponseEntity businessException(final BusinessException e) {
//        return ResponseEntity.status(e.getCode()).body(BodyBuilder.create().addError(e.getMessage()).build());
//    }
//
//}
