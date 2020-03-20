package com.zzq.webserver.core.request.dispatcher;

import com.zzq.webserver.core.exception.base.ServletException;
import com.zzq.webserver.core.request.Request;
import com.zzq.webserver.core.response.Response;

import java.io.IOException;

/**
 * Created by ZZQ on 2020/2/1.
 */
public interface RequestDispatcher {
    
    void forward(Request request, Response response) throws ServletException, IOException;
}
