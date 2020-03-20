package com.zzq.webserver.core.servlet;

import com.zzq.webserver.core.exception.base.ServletException;
import com.zzq.webserver.core.request.Request;
import com.zzq.webserver.core.response.Response;

import java.io.IOException;

/**
 * @author ZZQ
 * @date 2020/2/1
 */
public interface Servlet {
    void init();

    void destroy();

    void service(Request request, Response response) throws ServletException, IOException;
}
