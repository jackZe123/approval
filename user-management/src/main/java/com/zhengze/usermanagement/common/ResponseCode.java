package com.zhengze.usermanagement.common;

/**
 * @author zhengze
 * @version 1.0
 * @date :2019/9/5 20:39
 */
public enum ResponseCode {


    LOGIN_REEOR(500, "登录失败"),
    USER_ACCOUNT_IS_NULL(501, "用户账号不能为空"),
    USER_ACCOUNT_IS_LONG(502, "用户账号过长"),
    USER_ACCOUNT_IS_NOT_EXIST(503, "用户账号不存在"),
    USER_HAS_BEEN_LOGIN(504, "用户已登录"),
    PASSWORD_IS_NULL(505, "密码不能为空"),
    PASSWORD_LENGTH_IS_ERROR(506, "密码长度必须在6-12位"),
    PASSWORD_IS_NOT_STANDARD(507, "密码必须包含数字和字母"),
    NAME_IS_NULL(508, "昵称不能为空"),
    NAME_IS_LONG(509, "昵称的长度不能大于8位"),
    PHONE_IS_NULL(510, "手机号不能为空"),
    PHONE_LENGTH_IS_NOT_STANDARD(511, "手机长度必须为11位"),
    PHONE_FORMAT_IS_ERROR(512, "手机号格式不正确"),
    PHONE_HAS_REGISTER(513, "手机号已经被注册"),
    EMAIL_IS_NULL(514, "邮箱不能为空"),
    EMAIL_FORMAT_IS_ERROR(515, "邮箱格式不正确"),
    EMAIL_HAS_REGISTER(516, "该邮箱已经被注册"),
    NO_PRIVILEGES(517, "没有权限"),
    PARAM_IS_ERROR(518, "参数错误"),
    INSERT_ERROR(519, "插入错误"),
    DELETE_ERROR(520, "删除错误"),
    PLEASE_LOGIN(521, "请先登录，登录后才可收藏");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
