package com.zzq.webserver.core.enumeration;

/**
 * Created by ZZQ on 2020/2/1.
 */
public enum HttpStatus {
    OK(200),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500),
    BAD_REQUEST(400),
    MOVED_TEMPORARILY(302);

    private int code;
    HttpStatus(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }
}
