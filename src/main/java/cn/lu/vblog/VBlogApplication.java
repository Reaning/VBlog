package cn.lu.vblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.lu.vblog.mapper")
public class VBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(VBlogApplication.class, args);
    }

}
