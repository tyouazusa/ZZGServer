package com.zzq.webserver.core.network.handler.bio;

import com.zzq.webserver.core.context.ServletContext;
import com.zzq.webserver.core.context.WebApplication;
import com.zzq.webserver.core.exception.FilterNotFoundException;
import com.zzq.webserver.core.exception.ServletNotFoundException;
import com.zzq.webserver.core.exception.handler.ExceptionHandler;
import com.zzq.webserver.core.network.handler.AbstractRequestHandler;
import com.zzq.webserver.core.network.wrapper.SocketWrapper;
import com.zzq.webserver.core.network.wrapper.bio.BioSocketWrapper;
import com.zzq.webserver.core.request.Request;
import com.zzq.webserver.core.resource.ResourceHandler;
import com.zzq.webserver.core.response.Response;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by ZZQ on 2020/2/1.
 * Servlet运行容器
 */
@Setter
@Getter
@Slf4j
public class BioRequestHandler extends AbstractRequestHandler {

    public BioRequestHandler(SocketWrapper socketWrapper, ServletContext servletContext, ExceptionHandler exceptionHandler, ResourceHandler resourceHandler, Request request, Response response) throws ServletNotFoundException, FilterNotFoundException {
        super(socketWrapper, servletContext, exceptionHandler, resourceHandler, request, response);
    }

    /**
     * 写回后立即关闭socket
     */
    @Override
    public void flushResponse() {
        isFinished = true;
        BioSocketWrapper bioSocketWrapper = (BioSocketWrapper) socketWrapper;
        byte[] bytes = response.getResponseBytes();
        OutputStream os = null;
        try {
            os = bioSocketWrapper.getSocket().getOutputStream();
            os.write(bytes);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("socket closed");
        } finally {
            try {
                os.close();
                bioSocketWrapper.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        WebApplication.getServletContext().afterRequestDestroyed(request);
    }
}
