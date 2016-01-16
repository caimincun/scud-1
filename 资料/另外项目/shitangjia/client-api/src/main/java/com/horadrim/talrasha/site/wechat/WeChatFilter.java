package com.horadrim.talrasha.site.wechat;


import com.horadrim.talrasha.site.wechat.util.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求拦截
 *
 * @author GodSon
 */
public class   WeChatFilter implements Filter {

    private final Logger LOGGER = LoggerFactory.getLogger(WeChatFilter.class);

    @Override
    public void destroy() {
        LOGGER.info("WeChatFilter已经销毁");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        Boolean isGet = request.getMethod().equals("GET");

        if (isGet) {
            doGet(request, response);
        } else {
            doPost(request, response);
        }
    }

    private void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml");
        ServletInputStream in = request.getInputStream();
        String xmlMsg = Tools.inputStream2String(in);
        LOGGER.debug("输入消息:[" + xmlMsg + "]");
        String xml = WeChat.processing(xmlMsg);
        response.getWriter().write(xml);
    }

    private void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getServletPath();
        String pathInfo = path.substring(path.lastIndexOf("/"));
        String _token;
        String outPut = "error";
        if (pathInfo != null) {
            _token = pathInfo.substring(1);
            String signature = request.getParameter("signature");// 微信加密签名
            String timestamp = request.getParameter("timestamp");// 时间戳
            String nonce = request.getParameter("nonce");// 随机数
            String echostr = request.getParameter("echostr");//
            // 验证
            if (WeChat.checkSignature(_token, signature, timestamp, nonce)) {
                outPut = echostr;
            }
        }
        response.getWriter().write(outPut);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        LOGGER.info("WeChatFilter已经启动！");
    }

}
