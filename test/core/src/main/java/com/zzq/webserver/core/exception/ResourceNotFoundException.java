package com.zzq.webserver.core.exception;

import com.zzq.webserver.core.enumeration.HttpStatus;
import com.zzq.webserver.core.exception.base.ServletException;

/**
 * Created by ZZQ on 2020/2/1.
 * 静态资源未找到
 */
public class ResourceNotFoundException extends ServletException {
    private static final HttpStatus status = HttpStatus.NOT_FOUND;
    public ResourceNotFoundException() {
        super(status);
    }
}
