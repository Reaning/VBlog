package cn.lu.vblog.controller.admin;

import cn.lu.vblog.ApiResult;
import cn.lu.vblog.controller.ContentController;
import cn.lu.vblog.dto.ContentDTO;
import cn.lu.vblog.entity.Content;
import cn.lu.vblog.service.ContentService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

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
public class AdminArticleController {

    @Autowired
    private ContentService contentService;

    @GetMapping("publish")
    public String publish(){
        return "admin/article_edit";
    }


    @GetMapping("{id}")
    public String publish(
            @PathVariable("id") Long id,
            Model model
    ){
        Content content = contentService.getContentById(id);
        model.addAttribute("content",content);
        return "admin/article_edit";
    }

    @ResponseBody
    @PostMapping("/delete")
    public String delete(Long id){
        Content content = contentService.getContentById(id);
        if (content == null){
            return ApiResult.fail("找不到对应的文章！");
        }
        contentService.deleteById(id);
        return ApiResult.success();
    }

    @ResponseBody
    @PostMapping("publish")
    public String publishSave(ContentDTO contentDTO){
        System.out.println(contentDTO);
        contentService.saveContent(contentDTO);
        return ApiResult.success();
    }

    @ResponseBody
    @PostMapping("modify")
    public String modify(ContentDTO contentDTO){
        Content dbContent = contentService.getContentById(contentDTO.getId());
        if(dbContent == null){
            return ApiResult.fail("找不到文章!");
        }
        contentService.modifyContent(dbContent,contentDTO);
        return ApiResult.success();
    }

    @GetMapping("")
    public String article(
            @RequestParam(name = "page",required = false,defaultValue = "1") int page,
            @RequestParam(name = "limit",required = false,defaultValue = "15") int limit,
            Model model
    ){
        PageInfo<Content> articles = contentService.selectPage(page,limit);
        model.addAttribute("articles",articles);
        return "/admin/article_list";
    }
}
