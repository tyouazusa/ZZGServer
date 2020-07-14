package com.zzq.webserver.core.network.dispatcher.aio;

import com.zzq.webserver.core.exception.base.ServletException;
import com.zzq.webserver.core.network.dispatcher.AbstractDispatcher;
import com.zzq.webserver.core.network.handler.aio.AioRequestHandler;
import com.zzq.webserver.core.network.wrapper.SocketWrapper;
import com.zzq.webserver.core.network.wrapper.aio.AioSocketWrapper;
import com.zzq.webserver.core.request.Request;
import com.zzq.webserver.core.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.CompletionHandler;

/**
 * @author ZZQ
 * @date 2020/2/1
 */
@Slf4j
public class AioDispatcher extends AbstractDispatcher {
    
    @Override
    public void doDispatch(SocketWrapper socketWrapper) {
        AioSocketWrapper aioSocketWrapper = (AioSocketWrapper) socketWrapper;
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        aioSocketWrapper.getSocketChannel().read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                Request request = null;
                Response response = null;
                try {
                    //解析请求
                    request = new Request(attachment.array());
                    response = new Response();
                    pool.execute(new AioRequestHandler(aioSocketWrapper, servletContext, exceptionHandler, resourceHandler, this, request, response));
                } catch (ServletException e) {
                    exceptionHandler.handle(e, response, aioSocketWrapper);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable e, ByteBuffer attachment) {
                log.error("read failed");
                e.printStackTrace();
            }
        });
    }
}
