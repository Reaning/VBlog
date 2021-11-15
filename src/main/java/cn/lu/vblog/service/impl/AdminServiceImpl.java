package cn.lu.vblog.service.impl;

import cn.lu.vblog.constant.RedisKeyConstants;
import cn.lu.vblog.provider.TokenProvider;
import cn.lu.vblog.service.AdminService;
import cn.lu.vblog.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * com.example.vblog.service.impl
 *
 * @author lkxBruce
 * @date 2021/11/10 17:30
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private RedisUtils redisUtils;

    private final long EXPIRE = 60 * 30;

    @Override
    public void saveToken(Integer userId, HttpServletResponse response) {
        String token = TokenProvider.getToken();
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/admin");
        String tokenKey = RedisKeyConstants.MANAGE_SYS_USER_TOKEN + token;
        response.addCookie(cookie);
        redisUtils.set(tokenKey,userId,EXPIRE);
    }

    @Override
    public Integer getUserIdByToken(String token) {
        String tokenKey = RedisKeyConstants.MANAGE_SYS_USER_TOKEN + token;
        return redisUtils.getObj(tokenKey,Integer.class);
    }
}
