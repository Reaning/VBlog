package cn.lu.vblog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * cn.lu.vblog.controller.admin
 *
 * @author lkxBruce
 * @date 2021/11/11 11:40
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/index")
    public String index(){
        return "/admin/index";
    }
}
