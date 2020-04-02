package com.zhengze.roleauthoritymanagement.common;

import com.zhengze.roleauthoritymanagement.common.enums.BizErrorEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author zhengze
 * @version 1.0
 * @date :2020/3/4 14:27
 */
    public class BaseResponse implements Serializable {
        private static final Logger logger = LoggerFactory.getLogger(BaseResponse.class);
        private static final long serialVersionUID = -2970539265124203353L;
        private boolean success;
        private int code;
        private String message;

        public BaseResponse() {
            this(BizErrorEnum.SUCCESS.getCode(), BizErrorEnum.SUCCESS.getDesc());
        }

        public BaseResponse(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public BaseResponse(BaseResponse response) {
            this.code = response.getCode();
            this.message = response.getMessage();
        }

        public boolean isSuccess() {
            return this.code == BizErrorEnum.SUCCESS.getCode();
        }

        public static <E extends BaseResponse> E buildBaseResp(boolean success, int code, String message, Class<E> cls) {
            BaseResponse e = null;

            try {
                e = (BaseResponse)cls.newInstance();
            } catch (InstantiationException var6) {
                logger.warn("");
            } catch (IllegalAccessException var7) {
                logger.warn("");
            }

            e.setSuccess(success);
            e.setCode(code);
            e.setMessage(message);
            return (E) e;
        }

        public static <E extends BaseResponse> E success(Class<E> cls) {
            return buildBaseResp(true, BizErrorEnum.SUCCESS.getCode(), BizErrorEnum.SUCCESS.getDesc(), cls);
        }

        public static <E extends BaseResponse> E failure(Class<E> cls) {
            return buildBaseResp(false, BizErrorEnum.FAILURE.getCode(), BizErrorEnum.FAILURE.getDesc(), cls);
        }

        public static <E extends BaseResponse> E failure(String message, Class<E> cls) {
            return buildBaseResp(false, BizErrorEnum.FAILURE.getCode(), message, cls);
        }

        public static <E extends BaseResponse> E failure(BizErrorEnum errorEnum, String message, Class<E> cls) {
            return buildBaseResp(false, errorEnum.getCode(), message, cls);
        }

        public static BaseResponse failure(String message) {
            return new BaseResponse(BizErrorEnum.FAILURE.getCode(), message);
        }

        public static BaseResponse failure(BizErrorEnum errorEnum, String message) {
            return new BaseResponse(errorEnum.getCode(), message);
        }

        public void setSuccess(final boolean success) {
            this.success = success;
        }

        public void setCode(final int code) {
            this.code = code;
        }

        public void setMessage(final String message) {
            this.message = message;
        }

        public int getCode() {
            return this.code;
        }

        public String getMessage() {
            return this.message;
        }

        @Override
        public String toString() {
            return "BaseResponse(success=" + this.isSuccess() + ", code=" + this.getCode() + ", message=" + this.getMessage() + ")";
        }
    }


