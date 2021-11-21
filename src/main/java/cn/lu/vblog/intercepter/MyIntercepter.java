package cn.lu.vblog.intercepter;

import cn.lu.vblog.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cn.lu.vblog.intercepter
 *
 * @author lkxBruce
 * @date 2021/11/21 21:19
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
@Component
public class MyIntercepter implements HandlerInterceptor {
    @Autowired
    private CommonUtils commonUtils;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        request.setAttribute("commons", commonUtils);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
