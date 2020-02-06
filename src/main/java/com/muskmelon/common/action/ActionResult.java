package com.muskmelon.common.action;

import com.muskmelon.common.enums.ErrorCode;

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

    public ActionResult(){
        this.code = ErrorCode.success().getCode();
        this.msg = ErrorCode.success().getMsg();
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
