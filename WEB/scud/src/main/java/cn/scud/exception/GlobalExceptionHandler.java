package cn.scud.exception;

import cn.scud.commoms.CodeDefined;
import cn.scud.commoms.response.ErrorJsonRes;
import cn.scud.commoms.response.OperatorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by Administrator on 2015/7/10.
 * 使用注解，自定义返回 json 数据的全局异常拦截器
 */
//@ControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(value = { Exception.class, RuntimeException.class })
public OperatorResponse defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception ex){
        ErrorJsonRes errorJsonRes = null;
        if(ex instanceof NumberFormatException){
            errorJsonRes = new ErrorJsonRes(CodeDefined.EXCEPTION_CODE_NUM,CodeDefined.getMessage(CodeDefined.EXCEPTION_CODE_NUM)); // 30001number 转换异常
        }
        else if(ex instanceof NullPointerException){
            errorJsonRes = new ErrorJsonRes(CodeDefined.EXCEPTION_CODE_NULL,CodeDefined.getMessage(CodeDefined.EXCEPTION_CODE_NULL));//30002空指针异常
        }
        else
        {
            errorJsonRes = new ErrorJsonRes(CodeDefined.EXCEPTION_CODE_ERROR,CodeDefined.getMessage(CodeDefined.EXCEPTION_CODE_ERROR));// 30003程序出现异常
        }
        return  errorJsonRes;
    }

}
