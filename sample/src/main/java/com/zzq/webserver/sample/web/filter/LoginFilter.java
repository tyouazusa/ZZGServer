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
public class LoginFilter implements Filter {

    @Override
    public void init() {
        log.info("LoginFilter init...");
    }

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        log.info("当前访问的servletPath:{}", request.getServletPath());
        // login直接放行，其他页面访问均需要登录
        if (request.getServletPath().equals("/login") || request.getServletPath().startsWith("/views/errors")) {
            log.info("直接放行");
            filterChain.doFilter(request, response);
        } else {
            log.info("检查是否登录...");
            if (request.getSession(false) != null && request.getSession().getAttribute("username") != null) {
                log.info("已登录，通过检查...");
                filterChain.doFilter(request, response);
            } else {
                log.info("未登录,401");
                // 未登录。重定向至登录页面
                response.sendRedirect("/login");
            }
        }
    }

    @Override
    public void destroy() {
        log.info("LoginFilter destroy...");
    }
}
