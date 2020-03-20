
package com.zzq.webserver.core.listener.event;


import com.zzq.webserver.core.session.HttpSession;

/**
 * session相关的事件
 */
public class HttpSessionEvent extends java.util.EventObject {

    private static final long serialVersionUID = -7622791603672342895L;

   
    public HttpSessionEvent(HttpSession source) {
        super(source);
    }
    
    public HttpSession getSession () { 
        return (HttpSession) super.getSource();
    }
}

