package com.zzq.webserver.sample.web.servlet;

import com.zzq.webserver.core.exception.base.ServletException;
import com.zzq.webserver.core.request.Request;
import com.zzq.webserver.core.response.Response;
import com.zzq.webserver.core.servlet.impl.HttpServlet;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Created by ZZQ on 2020/2/1.
 */
@Slf4j
public class LogoutServlet extends HttpServlet {
    
    @Override
    public void doGet(Request request, Response response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/logout.html").forward(request,response);  
    }

    @Override
    public void doPost(Request request, Response response) throws ServletException, IOException {
        request.getSession().removeAttribute("username");
        request.getSession().invalidate();
        response.sendRedirect("/login");
    }
}
