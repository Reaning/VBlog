package cn.lu.vblog;

import cn.lu.vblog.constant.RedisKeyConstants;
import cn.lu.vblog.entity.AdminUser;
import cn.lu.vblog.exception.CustomizeException;
import cn.lu.vblog.service.AdminService;
import cn.lu.vblog.exception.CustomizeErrorCode;
import cn.lu.vblog.util.RedisUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * com.example.vblog
 *
 * @author lkxBruce
 * @date 2021/11/9 11:10
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof MyToken;
    }

    /**
     * 授权(涉及到权限划分的时候会用到)
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权中");
        return null;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return Shiro验证
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证中");
        String token = (String) authenticationToken.getPrincipal();
        Integer userIdByToken = adminService.getUserIdByToken(token);

        if(userIdByToken == null){
            throw new CustomizeException(CustomizeErrorCode.OVERTIME);
        }

        //数据库查询

        String tokenKey = RedisKeyConstants.MANAGE_SYS_USER_TOKEN + token;
        Long userId = redisUtils.getObj(tokenKey, Long.class);
        AdminUser adminUser = adminService.getUserById(userId);

        //由Shiro验证
        //猜测：Shiro会验证AuthenticationToken和输入的token是否一致
        return new SimpleAuthenticationInfo(adminUser,token,"user");
    }
}
