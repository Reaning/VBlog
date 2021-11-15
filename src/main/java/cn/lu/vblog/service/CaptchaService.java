package cn.lu.vblog.service;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.image.BufferedImage;

/**
 * com.example.vblog.service
 *
 * @author lkxBruce
 * @date 2021/11/10 20:21
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
public interface CaptchaService {

    BufferedImage getCaptcha(String captchaID);

    boolean verifyCaptcha(String captchaID,String answer);

    void saveCaptcha(String captchaID,String text);
}
