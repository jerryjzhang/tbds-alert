package com.tencent.tbds.alert.dto;

/**
 * Created by jerryjzhang on 2016/3/22.
 */
public enum ResponseStatus {
    SUCCESS(0, "Success"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Object Not Found"),
    INTERNAL_ERROR(500, "Server Error");

    private int code;
    private String msg;

    ResponseStatus(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
