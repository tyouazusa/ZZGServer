package com.zzq.webserver.core.listener.event;

import com.zzq.webserver.core.context.ServletContext;

/**
 * @author ZZQ
 * @date 2020/2/1
 * servletContext相关的事件
 */
public class ServletContextEvent extends java.util.EventObject { 

 
    public ServletContextEvent(ServletContext source) {
        super(source);
    }
    
    public ServletContext getServletContext () { 
        return (ServletContext) super.getSource();
    }
}

