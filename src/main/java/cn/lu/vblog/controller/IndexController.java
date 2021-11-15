package cn.lu.vblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * cn.lu.vblog.controller
 *
 * @author lkxBruce
 * @date 2021/11/11 11:03
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/lkx")
    public String lkx(){
        return "/views/level1/2";
    }
}