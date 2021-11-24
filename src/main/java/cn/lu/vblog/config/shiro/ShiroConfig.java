package cn.lu.vblog.config.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;

/**
 * com.example.vblog.config
 *
 * @author lkxBruce
 * @date 2021/11/9 10:33
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
@Configuration
public class ShiroConfig {

    @Bean
    public SessionManager sessionManager(){
        DefaultWebSessionManager defaultSessionManager = new DefaultWebSessionManager();
        defaultSessionManager.setSessionValidationSchedulerEnabled(false);
        defaultSessionManager.setSessionIdUrlRewritingEnabled(false);
        return defaultSessionManager;
    }

    @Bean
    public SecurityManager securityManager(UserRealm userRealm,SessionManager sessionManager){
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(userRealm);
        defaultSecurityManager.setSessionManager(sessionManager);
        return defaultSecurityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        LinkedHashMap<String, Filter> filters = new LinkedHashMap<>();
        filters.put("myauth",new MyFilter());
        shiroFilterFactoryBean.setFilters(filters);
        // 添加shiro的内置过滤器
        /*
            anon： 无需认证就可以访问
            authc： 必须认证了才能访问
            user： 必须拥有记住我功能才能用
            perms： 拥有对某个资源的权限才能访问
            role： 拥有某个角色权限
         */
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/admin/login","anon");
        filterMap.put("/admin/images/**","anon");
        filterMap.put("/admin/editormd/**","anon");
        filterMap.put("/admin/plugins/**","anon");
        filterMap.put("/admin/js/**","anon");
        filterMap.put("/admin/css/**","anon");
        filterMap.put("/admin/captcha.jpg","anon");
        filterMap.put("/admin/**","myauth");
        filterMap.put("/**","anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        shiroFilterFactoryBean.setLoginUrl("/admin/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/admin/login");
        return shiroFilterFactoryBean;
    }


}
