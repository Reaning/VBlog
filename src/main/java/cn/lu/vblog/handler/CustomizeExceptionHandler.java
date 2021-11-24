package cn.lu.vblog.handler;

import cn.lu.vblog.ApiResult;
import cn.lu.vblog.constant.SysConstants;
import cn.lu.vblog.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    Object handle(HttpServletRequest request, Throwable ex, Model model) {
        String contentType = request.getContentType();
        if("application/json".equals(contentType)){
            if (ex instanceof CustomizeException) {
                return ApiResult.fail(ex.getMessage());
            } else {
                return ApiResult.fail(SysConstants.SYS_ERROR);
            }
        }
        else {
            if (ex instanceof CustomizeException) {
                model.addAttribute("message", ex.getMessage());
            } else {
                model.addAttribute("message", "服务器找不到你想要的画面了，请稍后再试！");
            }
            return new ModelAndView("error");
        }
    }
}
