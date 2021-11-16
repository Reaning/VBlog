package cn.lu.vblog.controller.admin;

import cn.lu.vblog.ApiResult;
import cn.lu.vblog.controller.ContentController;
import cn.lu.vblog.dto.ContentDTO;
import cn.lu.vblog.service.ContentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * cn.lu.vblog.controller.admin
 *
 * @author lkxBruce
 * @date 2021/11/15 13:54
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
@Controller
@RequestMapping("/admin/article")
public class ArticleController {

    @Autowired
    private ContentService contentService;

    @GetMapping("publish")
    public String publish(){
        return "admin/article_edit";
    }

    @ResponseBody
    @PostMapping("publish")
    public String publishSave(ContentDTO contentDTO){
        System.out.println(contentDTO);
        contentService.saveContent(contentDTO);
        return ApiResult.success();
    }
}
