package com.zzq.webserver.core.network.connector.aio;

import com.zzq.webserver.core.network.endpoint.aio.AioEndpoint;
import com.zzq.webserver.core.network.wrapper.aio.AioSocketWrapper;
import lombok.extern.slf4j.Slf4j;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author ZZQ
 * @date 2020/2/1
 */
@Slf4j
//CompletionHandler:操作系统读写操作完成后的回调函数
public class AioAcceptor implements CompletionHandler<AsynchronousSocketChannel, Void> {
    private AioEndpoint aioEndpoint;

    public AioAcceptor(AioEndpoint aioEndpoint) {
        this.aioEndpoint = aioEndpoint;
    }

    @Override
    public void completed(AsynchronousSocketChannel client, Void attachment) {
        aioEndpoint.accept();
        aioEndpoint.execute(new AioSocketWrapper(aioEndpoint, client));
    }

    @Override
    public void failed(Throwable e, Void attachment) {
        log.info("accept failed...");
        e.printStackTrace();
    }
}
