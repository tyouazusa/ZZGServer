package com.zzq.webserver.core.exception;

import com.zzq.webserver.core.enumeration.HttpStatus;
import com.zzq.webserver.core.exception.base.ServletException;

/**
 * Created by ZZQ on 2020/2/1.
 * 请求解析出国
 */
public class RequestParseException extends ServletException {
    private static final HttpStatus status = HttpStatus.BAD_REQUEST;
    public RequestParseException() {
        super(status);
    }
}
