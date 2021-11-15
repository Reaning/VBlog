package cn.lu.vblog.config.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 * cn.lu.vblog.config.mybatisplus
 *
 * @author lkxBruce
 * @date 2021/11/15 17:11
 * @email lkxbruce@gmail.com
 * @project VBlog
 */

@MapperScan("cn.lu.vblog.mapper")
@EnableTransactionManagement
@Configuration
public class MyBatisPlusConfig {

    @Bean
    public OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor(){
        return new OptimisticLockerInnerInterceptor();
    }
}
