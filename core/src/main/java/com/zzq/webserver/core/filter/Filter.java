package com.zzq.webserver.core.filter;

import com.zzq.webserver.core.request.Request;
import com.zzq.webserver.core.response.Response;

/**
 * @author ZZQ
 * @date 2020/2/1
 * 过滤器
 */
public interface Filter {
    /**
     * 过滤器初始化
     */
    void init();

    /**
     * 过滤
     * @param request
     * @param response
     * @param filterChain
     */
    void doFilter(Request request, Response response, FilterChain filterChain) ;

    /**
     * 过滤器销毁
     */
    void destroy();
}
