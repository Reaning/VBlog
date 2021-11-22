package cn.lu.vblog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * cn.lu.vblog.controller.admin
 *
 * @author lkxBruce
 * @date 2021/11/22 11:35
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
@Controller
@RequestMapping("/admin/comments")
public class CommentsController {
    @GetMapping("")
    public String comments(){
        return "/admin/comment_list";
    }
}
