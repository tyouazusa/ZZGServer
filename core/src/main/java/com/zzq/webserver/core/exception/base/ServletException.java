package com.zzq.webserver.core.exception.base;

import com.zzq.webserver.core.enumeration.HttpStatus;
import lombok.Getter;

/**
 * Created by ZZQ on 2020/2/1.
 * 根异常
 */
@Getter
public class ServletException extends Exception {
    private HttpStatus status;
    public ServletException(HttpStatus status){
        this.status = status;
    }
}
