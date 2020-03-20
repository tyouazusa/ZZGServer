package com.zzq.webserver.core.request.dispatcher.impl;

import com.zzq.webserver.core.constant.CharsetProperties;
import com.zzq.webserver.core.exception.ResourceNotFoundException;
import com.zzq.webserver.core.exception.base.ServletException;
import com.zzq.webserver.core.request.Request;
import com.zzq.webserver.core.request.dispatcher.RequestDispatcher;
import com.zzq.webserver.core.resource.ResourceHandler;
import com.zzq.webserver.core.response.Response;
import com.zzq.webserver.core.template.TemplateResolver;
import com.zzq.webserver.core.util.IOUtil;
import com.zzq.webserver.core.util.MimeTypeUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Created by ZZQ on 2020/2/1.
 * 请求转发器
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class ApplicationRequestDispatcher implements RequestDispatcher {
    private String url;
    
    @Override
    public void forward(Request request, Response response) throws ServletException, IOException {
        if (ResourceHandler.class.getResource(url) == null) {
            throw new ResourceNotFoundException();
        }
        log.info("forward至 {} 页面",url);
        String body = TemplateResolver.resolve(new String(IOUtil.getBytesFromFile(url), CharsetProperties.UTF_8_CHARSET),request);
        response.setContentType(MimeTypeUtil.getTypes(url));
        response.setBody(body.getBytes(CharsetProperties.UTF_8_CHARSET));
    }
}
