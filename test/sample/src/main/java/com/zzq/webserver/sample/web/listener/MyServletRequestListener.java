package com.zzq.webserver.sample.web.listener;

import com.zzq.webserver.core.listener.ServletRequestListener;
import com.zzq.webserver.core.listener.event.ServletRequestEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ZZQ
 * @date 2020/2/1
 */
@Slf4j
public class MyServletRequestListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        log.info("request destroy...");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        log.info("request init...");
    }
}
