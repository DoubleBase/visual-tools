package com.muskmelon.common.enums;

/**
 * @author muskmelon
 * @description
 * @date 2020-2-6 15:01
 * @since 1.0
 */
public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS(0,"成功"),

    /**
     * 系统错误
     */
    SYSTEM_ERROR(-1,"系统错误"),

    /**
     * zk连接失败
     */
    ZK_CONNECT_ERROR(10001,"zk连接失败");



    /**
     * 错误码
     */
    private int code;

    /**
     * 错误描述
     */
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultCode success(){
        return ResultCode.SUCCESS;
    }

    public static ResultCode error(){
        return ResultCode.SYSTEM_ERROR;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}