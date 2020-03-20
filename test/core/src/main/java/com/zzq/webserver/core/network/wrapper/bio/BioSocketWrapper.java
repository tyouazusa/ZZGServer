package com.zzq.webserver.core.network.wrapper.bio;

import com.zzq.webserver.core.network.wrapper.SocketWrapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.Socket;

/**
 * @author ZZQ
 * @date 2020/2/1
 */
@Slf4j
@Getter
public class BioSocketWrapper implements SocketWrapper {
    private Socket socket;
    public BioSocketWrapper(Socket socket) {
        this.socket = socket;
    }
    
    @Override
    public void close() throws IOException {
        socket.close();
    }

}
