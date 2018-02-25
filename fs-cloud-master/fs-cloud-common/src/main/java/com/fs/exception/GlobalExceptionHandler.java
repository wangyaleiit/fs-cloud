package com.fs.exception;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseBody
	@ExceptionHandler(value = Exception.class)
    public ErrorInfo<String> systemExceptionHandler(HttpServletRequest req, SystemException e) {
        ErrorInfo<String> er = new ErrorInfo<String>();
        er.setCode(ErrorInfo.ERROR);
        er.setMessage(e.getMessage());
        er.setUrl(req.getRequestURL().toString());
        er.setParams(req.getQueryString());
        er.setData("发生系统异常，无法继续进行！");
        return er;
    }

//	@ResponseBody
//    @ExceptionHandler(value = Exception.class)
//    public ErrorInfo<String> defaultExceptionHandler(HttpServletRequest req, Exception e) {
//        ErrorInfo<String> er = new ErrorInfo<String>();
//        er.setCode(ErrorInfo.ERROR);
//        er.setMessage(e.getLocalizedMessage());
//        er.setUrl(req.getRequestURL().toString());
//        er.setParams(req.getQueryString());
//        if(UnauthorizedException.class.getName().equals(e.getClass().getName())){
//            er.setData("发生异常，无此权限处理！");
//        } else {
//            er.setData("发生异常，无法继续进行！");
//        }
//        return er;
//    }
}