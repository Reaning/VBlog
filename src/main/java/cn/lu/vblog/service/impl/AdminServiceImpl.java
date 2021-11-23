package cn.lu.vblog.service.impl;

import cn.lu.vblog.constant.RedisKeyConstants;
import cn.lu.vblog.entity.AdminUser;
import cn.lu.vblog.mapper.AdminUserMapper;
import cn.lu.vblog.provider.TokenProvider;
import cn.lu.vblog.service.AdminService;
import cn.lu.vblog.util.RedisUtils;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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

    @Autowired
    private AdminUserMapper adminUserMapper;

    private final long EXPIRE = 60 * 30;

    @Override
    public void saveToken(Long userId, HttpServletResponse response) {
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

    @Override
    public AdminUser getUserByName(String username) {
        QueryWrapper<AdminUser> adminUserWrapper = new QueryWrapper<>();
        adminUserWrapper.eq("username",username);
        return adminUserMapper.selectOne(adminUserWrapper);
    }

    @Override
    public AdminUser getUserById(Long id) {
        return adminUserMapper.selectById(id);
    }

    @Override
    public AdminUser getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        AdminUser user = (AdminUser) subject.getPrincipal();
        return user;
    }
}
