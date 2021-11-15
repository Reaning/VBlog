package cn.lu.vblog.controller.admin;

import cn.lu.vblog.pojo.AdminUser;
import cn.lu.vblog.service.AdminService;
import cn.lu.vblog.service.CaptchaService;
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
import java.util.HashMap;
import java.util.Map;
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
    public Map loginForm(String username,
                         String password,
                         String answer,
                         String uuid,
                         Model model,
                         HttpServletResponse response){
        AdminUser adminUser = new AdminUser();
        adminUser.setUsername("lkx");
        adminUser.setPassword("123456");
        HashMap<String, String> result = new HashMap<>();
        if(!captchaService.verifyCaptcha(uuid, answer)){
            result.put("code","fail");
            result.put("msg","验证码错误");
            return result;
        }
        if (!Objects.equals(password, adminUser.getPassword())){
            result.put("code","fail");
            result.put("msg","密码错误");
            return result;
        }
        adminService.saveToken(1,response);
        result.put("code","success");
        return result;
    }

    @GetMapping("/index")
    public String index(){
        return "/admin/index";
    }
}
