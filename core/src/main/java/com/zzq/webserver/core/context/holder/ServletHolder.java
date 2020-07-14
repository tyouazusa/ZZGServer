package com.zzq.webserver.core.context.holder;

import com.zzq.webserver.core.servlet.Servlet;
import lombok.Data;

/**
 * @author ZZQ
 * @date 2020/2/1
 */
@Data
public class ServletHolder {
    private Servlet servlet;
    private String servletClass;

    public ServletHolder(String servletClass) {
        this.servletClass = servletClass;
    }
}
