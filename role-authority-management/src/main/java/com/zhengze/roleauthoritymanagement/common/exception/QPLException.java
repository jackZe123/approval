package com.zhengze.roleauthoritymanagement.common.exception;


import com.zhengze.roleauthoritymanagement.common.enums.BizErrorEnum;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/4 14:33
 */
public class QPLException extends BizException {
    private static final long serialVersionUID = -7035459882737664256L;
    private BizErrorEnum errorCode;

    public QPLException() {
    }

    public QPLException(BizErrorEnum errorCode, String errorMessage) {
        super(errorCode.name(), errorMessage);
        this.errorCode = errorCode;
    }

    public BizErrorEnum getErrorCode() {
        return this.errorCode;
    }
}
