package com.zzq.webserver.sample.web.servlet;

import com.zzq.webserver.core.exception.base.ServletException;
import com.zzq.webserver.core.request.Request;
import com.zzq.webserver.core.response.Response;
import com.zzq.webserver.core.servlet.impl.HttpServlet;
import com.zzq.webserver.sample.domain.User;
import com.zzq.webserver.sample.service.UserService;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author ZZQ
 * @date 2020/2/1
 */
@Slf4j
public class UserEditServlet extends HttpServlet {
    private UserService userService;

    public UserEditServlet() {
        userService = UserService.getInstance();
    }

    
    @Override
    public void doGet(Request request, Response response) throws ServletException, IOException {
        User user = userService.findByUsername((String) request.getSession().getAttribute("username"));
        request.setAttribute("user",user);
        request.getRequestDispatcher("/views/userEdit.html").forward(request,response);
    }

    @Override
    public void doPost(Request request, Response response) throws ServletException, IOException {
        log.info("{}",request.getParams());
        User user = new User();
        user.setUsername((String) request.getSession(false).getAttribute("username"));
        user.setRealName(request.getParameter("realName"));
        user.setAge(Integer.valueOf(request.getParameter("age")));
        userService.update(user);
        
        request.setAttribute("user",user);
        request.getRequestDispatcher("/views/user.html").forward(request, response);
    }
}
