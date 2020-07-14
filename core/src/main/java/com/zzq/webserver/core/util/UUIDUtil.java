package com.zzq.webserver.core.util;

import java.util.UUID;

/**
 * Created by ZZQ on 2020/2/1.
 */
public class UUIDUtil {
    public static String uuid(){
        return UUID.randomUUID().toString().replace("-","").toUpperCase();
    }
}
