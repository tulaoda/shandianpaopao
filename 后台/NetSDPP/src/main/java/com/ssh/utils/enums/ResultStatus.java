package com.ssh.utils.enums;

/**
 * Created by lihang on 2017/4/4.
 */
public enum ResultStatus {

    SUCCESS(200, "ok"),
    FAILURE(-1001,"failure"),
    NO_MORE_DATA(-1002,"没有更多数据"),
    //USERNAME_OR_PASSWORD_ERROR(-1001, "用户名或密码错误"),
    USER_NOT_FOUND(-1006, "用户不存在"),
    USER_NOT_LOGIN(-1003, "用户未登录"),
    REPEAT_LIKE(-1004,"重复点赞"),
    REPEAT_UNLIKE(-1005,"重复取消赞"),
    NO_PERMISSION(-1006,"当前用户没有操作权限");


    /**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
