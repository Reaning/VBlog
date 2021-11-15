package cn.lu.vblog.service;

import javax.servlet.http.HttpServletResponse;

/**
 * com.example.vblog.service
 *
 * @author lkxBruce
 * @date 2021/11/10 17:29
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
public interface AdminService {
    void saveToken(Integer userId, HttpServletResponse response);

    Integer getUserIdByToken(String token);
}
