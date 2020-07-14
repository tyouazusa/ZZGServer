package com.zzq.webserver.core.network.handler.aio;


import com.zzq.webserver.core.context.ServletContext;
import com.zzq.webserver.core.context.WebApplication;
import com.zzq.webserver.core.exception.FilterNotFoundException;
import com.zzq.webserver.core.exception.ServletNotFoundException;
import com.zzq.webserver.core.exception.handler.ExceptionHandler;
import com.zzq.webserver.core.network.handler.AbstractRequestHandler;
import com.zzq.webserver.core.request.Request;
import com.zzq.webserver.core.resource.ResourceHandler;
import com.zzq.webserver.core.response.Response;
import com.zzq.webserver.core.network.wrapper.SocketWrapper;
import com.zzq.webserver.core.network.wrapper.aio.AioSocketWrapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZZQ on 2020/2/1.
 * Servlet运行容器
 */
@Setter
@Getter
@Slf4j
public class AioRequestHandler extends AbstractRequestHandler {
    private CompletionHandler readHandler;
    
    public AioRequestHandler(SocketWrapper socketWrapper, ServletContext servletContext, ExceptionHandler exceptionHandler, ResourceHandler resourceHandler, CompletionHandler readHandler, Request request, Response response) throws ServletNotFoundException, FilterNotFoundException {
        super(socketWrapper, servletContext, exceptionHandler, resourceHandler,request,response);
        this.readHandler = readHandler;
    }

    /**
     * 写回后重新调用readHandler，进行读取（猜测AIO也是保活的）
     */
    @Override
    public void flushResponse() {
        isFinished = true;
        ByteBuffer[] responseData = response.getResponseByteBuffer();
        AioSocketWrapper aioSocketWrapper = (AioSocketWrapper) socketWrapper;
        AsynchronousSocketChannel socketChannel = aioSocketWrapper.getSocketChannel();
        socketChannel.write(responseData, 0, 2, 0L, TimeUnit.MILLISECONDS, null, new CompletionHandler<Long, Object>() {

            @Override
            public void completed(Long result, Object attachment) {
                log.info("写入完毕...");
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                socketChannel.read(byteBuffer, byteBuffer, readHandler);
            }

            @Override
            public void failed(Throwable e, Object attachment) {
                log.info("写入失败...");
                e.printStackTrace();
            }
        });
        WebApplication.getServletContext().afterRequestDestroyed(request);
    }
}
