package com.zzq.webserver.sample.web.filter;

import com.zzq.webserver.core.filter.Filter;
import com.zzq.webserver.core.filter.FilterChain;
import com.zzq.webserver.core.request.Request;
import com.zzq.webserver.core.response.Response;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ZZQ
 * @date 2020/2/1
 */
@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init() {
        log.info("LogFilter init...");
    }

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        log.info("{} before accessed, method is {}", request.getUrl(), request.getMethod());
        filterChain.doFilter(request, response);
        log.info("{} after accessed, method is {}", request.getUrl(), request.getMethod());
    }

    @Override
    public void destroy() {
        log.info("LogFilter destroy...");
    }
}
