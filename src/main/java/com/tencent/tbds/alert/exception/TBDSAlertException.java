package com.tencent.tbds.alert.exception;

/**
 * Created by jerryjzhang on 2016/3/22.
 */
public class TBDSAlertException extends RuntimeException{
    private int code;

    public TBDSAlertException(int code, String msg){
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
