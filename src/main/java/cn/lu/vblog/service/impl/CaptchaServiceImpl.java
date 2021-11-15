package cn.lu.vblog.service.impl;

import cn.lu.vblog.service.CaptchaService;
import cn.lu.vblog.util.RedisUtils;
import cn.lu.vblog.constant.RedisKeyConstants;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

/**
 * com.example.vblog.service.impl
 *
 * @author lkxBruce
 * @date 2021/11/10 20:22
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    private Producer producer;

    @Autowired
    private RedisUtils redisUtils;

    private final long CAPTCHA_EXIST = 3 * 60;

    /**
     * 生成验证码
     * @param captchaID
     * @return 验证码图片
     */
    public BufferedImage getCaptcha(String captchaID){
        String text = producer.createText();
        saveCaptcha(captchaID,text);
        BufferedImage image = producer.createImage(text);
        return image;
    }

    /**
     * 验证验证码
     * @param captchaID
     * @param answer
     * @return 验证码是否正确
     */
    public boolean verifyCaptcha(String captchaID,String answer){
        String text = redisUtils.get(RedisKeyConstants.MANAGE_CAPTCHA + captchaID);
        return text.equals(answer);
    }

    /**
     * 保存验证码的值
     * @param captchaID
     * @param text
     */
    public void saveCaptcha(String captchaID,String text){
        redisUtils.set(RedisKeyConstants.MANAGE_CAPTCHA + captchaID,text,CAPTCHA_EXIST);
    }

}
