package cn.lu.vblog;

import cn.lu.vblog.exception.CustomizeException;
import cn.lu.vblog.exception.CustomizeErrorCode;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * com.example.vblog
 *
 * @author lkxBruce
 * @date 2021/11/10 10:20
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
public class MyFilter extends AuthenticatingFilter {

    /**
     * 获得Token
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        String token = getRequestToken((HttpServletRequest) servletRequest);
        if(token == null || StringUtils.isBlank(token)){
            return null;
        }
        return new MyToken(token);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        String token = getRequestToken((HttpServletRequest) servletRequest);
        if(token == null || StringUtils.isBlank(token)){
            //抛出错误
            throw new CustomizeException(CustomizeErrorCode.UNAUTHORIZED);
        }
        boolean success = executeLogin(servletRequest, servletResponse);
        if (success){
            return true;
        }else{
            //报错
            throw new CustomizeException(CustomizeErrorCode.UNAUTHORIZED);
        }
    }

    private String getRequestToken(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("token")){
                return cookie.getValue();
            }
        }
        return null;
    }

}
