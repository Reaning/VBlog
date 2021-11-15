package cn.lu.vblog.provider;

import java.util.UUID;

/**
 * com.example.vblog.provider
 *
 * @author lkxBruce
 * @date 2021/11/9 20:46
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
public class TokenProvider {
    public static String getToken(){
        return ValueProvider.stringMD5(UUID.randomUUID().toString());
    }
}
