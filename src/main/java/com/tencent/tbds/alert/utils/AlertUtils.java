package com.tencent.tbds.alert.utils;

import java.sql.Timestamp;

/**
 * Created by jerryjzhang on 2016/3/18.
 */
public class AlertUtils {
    public static Timestamp now(){
        return new Timestamp(System.currentTimeMillis());
    }
}
