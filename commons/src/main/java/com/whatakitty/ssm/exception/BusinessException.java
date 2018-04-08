package com.whatakitty.ssm.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yuhailun
 * @description 服务异常
 * @date 2018/1/9
 **/
@Getter
@Setter
public class BusinessException extends RuntimeException {

    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
