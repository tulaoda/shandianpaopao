package com.ssh.utils.enums;

/**
 * Created by lihang on 2017/4/4.
 */
public enum ErrorStatus {
    exist(0,""),
    SUCCESS(200, "yes"),
    appId_wrong(40001, "不合法的appId"),
    interface_not_exist(40002, "接口不存在"),
    interface_is_called(40003, "Http 接口被禁止调用"),
    Http_method_mismatch(40005, "Http Method不匹配"),
    interface_not_authorized(40006, "接口未授权"),
    frequency_overrun(40007, "频率超限"),
    user_identity_expired(40008, "用户身份已过期"),
    sessionid_wrong(40009, "sessionId为空"),
    not_select_upload_file (40010, "请选择上传文件"),
    upload_failed (40011, "上传失败"),
    insertuserinfo_wrong(40012,"插入用户信息失败"),
    updateuserinfo_wrong(40013,"更新用户信息失败"),
    no_userinfo(40014,"用户信息丢失"),
    communication_failure(50010, "与第三方通讯失败"),
    failed_get_WeChat_session_key(50020, "获取微信session_key失败"),
    user_sensitive_data_decryption_failed(50021, "用户敏感数据解密失败");

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;

    ErrorStatus(int code,String message) {
        this.code=code;
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
