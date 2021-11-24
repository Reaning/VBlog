package cn.lu.vblog.controller;

import cn.lu.vblog.dto.ArticleDTO;
import cn.lu.vblog.entity.Content;
import cn.lu.vblog.service.ContentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Autowired
    private ContentService contentService;

    @GetMapping("/")
    public String index(
            @RequestParam(value = "page",required = false,defaultValue = "1") Integer page,
            @RequestParam(value = "limit",required = false,defaultValue = "5") Integer limit,
            Model model
    ){
        PageInfo<ArticleDTO> pageInfo = contentService.getArticlePages(page,limit);
        model.addAttribute("articles",pageInfo);
        return "blog/index";
    }

    @GetMapping("/lkx")
    public String lkx(){
        return "/views/level1/2";
    }
}