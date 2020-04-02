package com.zhengze.usermanagement.common.exception;

import java.io.Serializable;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/4 14:32
 */
public class BizException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -5647312120172729825L;
    private String code;

    public BizException() {
    }

    public BizException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(Throwable cause) {
        super("", cause);
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}