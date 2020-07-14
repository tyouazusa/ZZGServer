<p align="center">
<img width="200" src="img/logo.jpg">
</p>
 
<p align="center"><span style="font-size:50px">ZZG服务器 - 轻量级Web应用服务器</span>
</p>

- 基于 `Java网络编程` 开发的一款 `Web应用服务器`
- 如果觉得不错 给个 `Star` 支持一下 🤓

## 技术栈
- Java BIO
- Java NIO
- Java AIO
- Java 多线程
- Socket网络编程
- XML解析
## 功能介绍
- HTTP Protocol:基于HTTP协议
- Servlet：运行Servlet应用程序
- ServletContext：全局应用程序共享对象
- Request & Response：封装HTTP请求 & 响应报文
- Static Resources：静态资源访问
- Error Notification：错误页面显示
- GET & POST & PUT & DELETE：支持各种HTTP方法
- XML Parse：基于dom4j的web.xml的解析
- Forward & Redirect：转发&重定向
- TemplateEngine：简易版模板引擎
- Session & Cookie：会话管理
- Filter：拦截器
- Listener：监听器 
- I/O：BIO NIO AIO的切换
- NIO Reactor：基于Tomcat的Reactor模型
## 本地部署
- 配置sample中的Servlet、Filter和Listener等
- 修改配置文件server.properties
- 启动sample项目
- 浏览器访问：http://localhost:8080
## 目录结构
<pre><code>├─core                   Web服务器
│  ├─constant            常量类
│  ├─context             Servlet上下文封装
│  ├─cookie              Cookie封装
│  ├─enumeration         枚举类
│  ├─exception           异常处理类
│  ├─filter              拦截器模块
│  ├─listener            监听器模块
│  ├─network             网络模块(BIO NIO AIO)
│  │  ├─connector        请求接收模块
│  │  ├─dispatcher       请求分发模块
│  │  ├─endpoint         入口封装模块
│  │  ├─handler          请求处理模块
│  │  ├─wrapper          Socket封装
│  ├─request             请求封装
│  ├─resource            静态资源处理模块
│  ├─servlet             动态资源处理模块
│  ├─session             Session封装
│  ├─template            模板引擎解析模块
│  ├─util                工具类
├─sample                 Web应用
│  ├─domain              实体类
│  ├─service             服务类
│  ├─web                 Web应用相关
│  │  ├─filter           拦截器类
│  │  ├─listener         监听器类
│__│__├─servlet          Servlet类
</code></pre>
