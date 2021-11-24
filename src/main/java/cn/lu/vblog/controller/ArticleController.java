package cn.lu.vblog.controller;

import cn.lu.vblog.entity.AdminUser;
import cn.lu.vblog.entity.Content;
import cn.lu.vblog.service.AdminService;
import cn.lu.vblog.service.ContentService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ArticleController {

    @Autowired
    private ContentService contentService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/id/{id}")
    public String article(
            @PathVariable("id")Long id,
            Model model
    ){
        Content content = contentService.getContentById(id);
        AdminUser user = adminService.getUserById(content.getCreator());
        model.addAttribute("article",content);
        model.addAttribute("user",user);
        return "blog/post";
    }
}
