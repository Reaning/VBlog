package cn.lu.vblog.controller.admin;

import cn.lu.vblog.ApiResult;
import cn.lu.vblog.entity.AdminUser;
import cn.lu.vblog.service.AdminService;
import cn.lu.vblog.service.CaptchaService;
import cn.lu.vblog.util.CommonUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * com.example.vblog.controller
 *
 * @author lkxBruce
 * @date 2021/11/9 16:44
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
@Controller
@RequestMapping("/admin")
public class LoginController {


    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private CommonUtils commonUtils;

    @GetMapping("/login")
    public String loginPage(Model model){
        String captchaID = UUID.randomUUID().toString();
        model.addAttribute("uuid",captchaID);
        return "/admin/login";
    }

    @GetMapping("/captcha.jpg")
    public void captcha(@RequestParam("uuid")String uuid,HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        BufferedImage captcha = captchaService.getCaptcha(uuid);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(captcha,"jpg",out);
        IOUtils.closeQuietly(out);
    }

    @ResponseBody
    @PostMapping("/login")
    public String loginForm(String username,
                         String password,
                         String answer,
                         String uuid,
                         HttpServletResponse response,
                            Model model){
        model.addAttribute("commons",commonUtils);
        if(!captchaService.verifyCaptcha(uuid, answer)){
            return ApiResult.fail("验证码错误");
        }
        AdminUser adminUser = adminService.getUserByName(username);
        if (adminUser == null){
            return ApiResult.fail("找不到用户名");
        }
        if (!Objects.equals(password, adminUser.getPassword())){
            return ApiResult.fail("密码错误");
        }
        adminService.saveToken(adminUser.getId(),response);
        return ApiResult.success();
    }
}
