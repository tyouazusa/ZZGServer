package com.zzq.webserver.core.exception;

import com.zzq.webserver.core.enumeration.HttpStatus;
import com.zzq.webserver.core.exception.base.ServletException;

/**
 * @author ZZQ
 * @date 2020/2/1
 * 未找到对应的Filter（web.xml配置错误）
 */
public class FilterNotFoundException extends ServletException {
    private static final HttpStatus status = HttpStatus.NOT_FOUND;
    public FilterNotFoundException() {
        super(status);
    }
}
