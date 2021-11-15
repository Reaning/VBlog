package cn.lu.vblog;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * com.example.vblog
 *
 * @author lkxBruce
 * @date 2021/11/10 10:16
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
public class MyToken implements AuthenticationToken {

    private String token;

    public MyToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
