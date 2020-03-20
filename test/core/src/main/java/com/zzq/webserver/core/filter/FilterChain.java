package com.zzq.webserver.core.filter;

import com.zzq.webserver.core.request.Request;
import com.zzq.webserver.core.response.Response;

/**
 * @author ZZQ
 * @date 2020/2/1
 * 拦截器链
 */
public interface FilterChain {
    /**
     * 当前filter放行，由后续的filter继续进行过滤
     * @param request
     * @param response
     */
    void doFilter(Request request, Response response) ;
}
