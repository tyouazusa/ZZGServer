package com.zzq.webserver.core.response;

import com.zzq.webserver.core.cookie.Cookie;
import com.zzq.webserver.core.enumeration.HttpStatus;
import com.zzq.webserver.core.network.handler.AbstractRequestHandler;
import com.zzq.webserver.core.constant.CharConstant;
import com.zzq.webserver.core.constant.CharsetProperties;
import com.zzq.webserver.core.constant.ContextConstant;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ZZQ on 2020/2/1.
 */
@Slf4j
public class Response {
    private StringBuilder headerAppender;
    private List<Cookie> cookies;
    private List<Header> headers;
    private HttpStatus status = HttpStatus.OK;
    private String contentType = ContextConstant.DEFAULT_CONTENT_TYPE;
    private byte[] body = new byte[0];
    private AbstractRequestHandler requestHandler;

    public Response() {
        this.headerAppender = new StringBuilder();
        this.cookies = new ArrayList<>();
        this.headers = new ArrayList<>();
    }

    /**
     * 设置HTTP Status
     * @param status
     */
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }


    public void addCookie(Cookie cookie) {
        cookies.add(cookie);
    }

    public void addHeader(Header header) {
        headers.add(header);
    }
    
    
    private void buildHeader() {
        //HTTP/1.1 200 OK
        headerAppender.append("HTTP/1.1").append(CharConstant.BLANK).append(status.getCode()).append(CharConstant.BLANK).append(status).append(CharConstant.CRLF);
        //Date: Sat, 31 Dec 2005 23:59:59 GMT
        headerAppender.append("Date:").append(CharConstant.BLANK).append(new Date()).append(CharConstant.CRLF);
        headerAppender.append("Content-Type:").append(CharConstant.BLANK).append(contentType).append(CharConstant.CRLF);
        if (headers != null) {
            for (Header header : headers) {
                headerAppender.append(header.getKey()).append(":").append(CharConstant.BLANK).append(header.getValue()).append(CharConstant.CRLF);
            }
        }
        if (cookies.size() > 0) {
            for (Cookie cookie : cookies) {
                headerAppender.append("Set-Cookie:").append(CharConstant.BLANK).append(cookie.getKey()).append("=").append(cookie.getValue()).append(CharConstant.CRLF);
            }
        }
        headerAppender.append("Content-Length:").append(CharConstant.BLANK);
    }

    //一次性传入响应体长度
    private void buildBody() {
        this.headerAppender.append(body.length).append(CharConstant.CRLF).append(CharConstant.CRLF);
    }

    /**
     * response构建的最后一步，将header和body转为字节数组
     */
    private void buildResponse() {
        buildHeader();
        buildBody();
    }
    
    /**
     * 返回Response构建后的数据，用于NIO/AIO
     * @return
     */
    public ByteBuffer[] getResponseByteBuffer() {
        buildResponse();
        byte[] header = this.headerAppender.toString().getBytes(CharsetProperties.UTF_8_CHARSET);
        ByteBuffer[] response = {ByteBuffer.wrap(header), ByteBuffer.wrap(body)};
        return response;
    }

    /**
     * 返回Response构建后的数据，用于BIO
     * @return
     */
    public byte[] getResponseBytes() {
        buildResponse();
        byte[] header = this.headerAppender.toString().getBytes(CharsetProperties.UTF_8_CHARSET);
        byte[] response = new byte[header.length + body.length];
        System.arraycopy(header, 0, response, 0, header.length);
        System.arraycopy(body, 0, response, header.length, body.length);
        return response;
    }

    /**
     * 重定向，注意重定向后会立即写数据至socket中
     * @param url
     */
    public void sendRedirect(String url) {
        log.info("重定向至{}", url);
        addHeader(new Header("Location", url));
        setStatus(HttpStatus.MOVED_TEMPORARILY);
        buildResponse();
        // 刷新至客户端
        requestHandler.flushResponse();
    }

    /**
     * 用于调用不同RequestHandler(BIO NIO AIO)的写刷新（将response写入到客户端）
     * @param requestHandler
     */
    public void setRequestHandler(AbstractRequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }
}
