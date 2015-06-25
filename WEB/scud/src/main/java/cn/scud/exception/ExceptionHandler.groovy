package cn.scud.exception

import org.springframework.web.servlet.HandlerExceptionResolver
import org.springframework.web.servlet.ModelAndView

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by cmc on 14-12-24.
 * 异常拦截处理
 */
class ExceptionHandler implements HandlerExceptionResolver{

    @Override
    ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex instanceof NumberFormatException) {
            //doSomething...
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("key","数据转换异常！！");
            modelAndView.setViewName("number");
            return modelAndView;
//            return new ModelAndView("number");/*HTTP Status 404 - /WEB-INF/jsp/number.jsp*/
        } else if (ex instanceof NullPointerException) {
            //doSomething...
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("key","空指针异常！！");
            modelAndView.setViewName("null");
            return modelAndView;
            /* return new ModelAndView("error", model);  */
        }

        System.out.println(ex.getMessage());
        return new ModelAndView("exception");
    }
}
