package com.ssh.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ssh.utils.enums.ResultStatus;

/**
 * Created by lihang on 2017/4/4.
 */
public class ResultModel {
    /**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;

    /**
     * 返回内容
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object content;

    public ResultModel(ResultStatus status, Object content) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.content = content;
    }

    public ResultModel(ResultStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public ResultModel(int code, String message, Object content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public ResultModel() {

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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ResultModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", content=" + content +
                '}';
    }
}
