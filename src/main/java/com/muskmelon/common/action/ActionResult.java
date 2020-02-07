package com.muskmelon.common.action;

import com.muskmelon.common.enums.ResultCode;

/**
 * @author muskmelon
 * @description
 * @date 2020-2-6 14:56
 * @since 1.0
 */
public class ActionResult<T> {

    private int code;
    private String msg;
    private T data;

    public ActionResult() {
        this.code = ResultCode.success().getCode();
        this.msg = ResultCode.success().getMsg();
    }

    public void success(){

    }

    public void error(T data,ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

    public void error(T data,ResultCode resultCode, String msg) {
        this.code = resultCode.getCode();
        this.msg = msg;
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
