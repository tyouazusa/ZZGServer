package com.zzq.webserver.core.exception;

import com.zzq.webserver.core.enumeration.HttpStatus;
import com.zzq.webserver.core.exception.base.ServletException;

/**
 * Created by ZZQ on 2020/2/1.
 * 模板引擎解析错误（html文件编写错误）
 */
public class TemplateResolveException extends ServletException {
    private static final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    public TemplateResolveException() {
        super(status);
    }
}
   
