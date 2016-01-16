package com.horadrim.talrasha.site.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Victor
 * Date: 13-11-23
 * Time: 下午10:27
 * To change this template use File | Settings | File Templates.
 */
public class DefaultExceptionHandler implements HandlerExceptionResolver {
    private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        logger.error("Un catch exception:", e);
        return new ModelAndView("err_msg", "errMsg", "服务器内部错误，请稍后再试");
    }
}
