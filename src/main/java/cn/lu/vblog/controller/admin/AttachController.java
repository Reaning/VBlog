package cn.lu.vblog.controller.admin;

import cn.lu.vblog.ApiResult;
import cn.lu.vblog.entity.AdminUser;
import cn.lu.vblog.entity.Attach;
import cn.lu.vblog.service.AttachService;
import cn.lu.vblog.util.FileUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * cn.lu.vblog.controller.admin
 *
 * @author lkxBruce
 * @date 2021/11/15 19:52
 * @email lkxbruce@gmail.com
 * @project VBlog
 */

@Controller
@RequestMapping("/admin/attach")
public class AttachController {

    @Autowired
    private AttachService attachService;

    @Autowired
    private FileUtils fileUtils;


    @GetMapping("")
    public String attach(
            @RequestParam(value = "page",required = false,defaultValue = "1")
            Integer page,
            @RequestParam(value = "limit",required = false,defaultValue = "15")
            Integer limit,
            Model model
    ){
        PageInfo<Attach> attachPageInfo = attachService.selectPage(page, limit);
        model.addAttribute("attachs",attachPageInfo);
        return "/admin/attach";
    }

    @ResponseBody
    @PostMapping("/upload")
    public String upload(
            @RequestParam(name = "file")
            MultipartFile[] files){
        if(files == null || files.length == 0){
            return ApiResult.fail("文件为空！");
        }
        for(MultipartFile file:files){
            Subject subject = SecurityUtils.getSubject();
            AdminUser adminUser = (AdminUser) subject.getPrincipal();
            fileUtils.upload(adminUser,file);
        }
        return ApiResult.success();
    }
}
