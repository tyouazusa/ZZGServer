package com.zzq.webserver.core.network.wrapper.nio;

import com.zzq.webserver.core.network.endpoint.nio.NioEndpoint;
import com.zzq.webserver.core.network.connector.nio.NioPoller;
import com.zzq.webserver.core.network.wrapper.SocketWrapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.channels.SocketChannel;

/**
 * @author ZZQ
 * @date 2020/2/1
 */
@Slf4j
@Data
public class NioSocketWrapper implements SocketWrapper {
    private final NioEndpoint server;
    private final SocketChannel socketChannel;
    private final NioPoller nioPoller;
    private final boolean isNewSocket;
    private volatile long waitBegin;
    private volatile boolean isWorking;
    
    public NioSocketWrapper(NioEndpoint server, SocketChannel socketChannel, NioPoller nioPoller, boolean isNewSocket) {
        this.server = server;
        this.socketChannel = socketChannel;
        this.nioPoller = nioPoller;
        this.isNewSocket = isNewSocket;
        this.isWorking = false;
    }
    
    public void close() throws IOException {
        socketChannel.keyFor(nioPoller.getSelector()).cancel();
        socketChannel.close();
    }



    @Override
    public String toString() {
        return socketChannel.toString();
    }
}
