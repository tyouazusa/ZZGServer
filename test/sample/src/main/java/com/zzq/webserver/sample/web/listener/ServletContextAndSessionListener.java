package com.zzq.webserver.sample.web.listener;

import com.zzq.webserver.core.listener.HttpSessionListener;
import com.zzq.webserver.core.listener.ServletContextListener;
import com.zzq.webserver.core.listener.event.HttpSessionEvent;
import com.zzq.webserver.core.listener.event.ServletContextEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author ZZQ
 * @date 2020/2/1
 */
@Slf4j
public class ServletContextAndSessionListener implements ServletContextListener, HttpSessionListener {
    private AtomicInteger sessionCount = new AtomicInteger();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("servlet context init...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("servlet context destroy...");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("session created, count = {}", this.sessionCount.incrementAndGet());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("session destroyed, count = {}", sessionCount.decrementAndGet());
    }
}
