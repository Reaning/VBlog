package cn.lu.vblog.config;

import cn.lu.vblog.config.intercepter.MyIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * cn.lu.vblog.config.intercepter
 *
 * @author lkxBruce
 * @date 2021/11/21 21:39
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${upload.path.static}")
    private String staticPath;

    @Value("${upload.path.real}")
    private String filePath;

    @Autowired
    private MyIntercepter myIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myIntercepter);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(staticPath + "**").addResourceLocations("file:" + filePath);
    }
}
