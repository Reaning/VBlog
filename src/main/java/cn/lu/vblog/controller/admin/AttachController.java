package cn.lu.vblog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * cn.lu.vblog.controller.admin
 *
 * @author lkxBruce
 * @date 2021/11/15 19:52
 * @email lkxbruce@gmail.com
 * @project VBlog
 */

@Controller
@RequestMapping("/admin")
public class AttachController {
    @GetMapping("/attach")
    public String attach(){
        return "admin/attach";
    }
}
