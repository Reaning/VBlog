package cn.lu.vblog.util;

import cn.lu.vblog.entity.AdminUser;
import cn.lu.vblog.entity.Attach;
import cn.lu.vblog.service.AttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * cn.lu.vblog.util
 *
 * @author lkxBruce
 * @date 2021/11/22 19:02
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
@Component
public class FileUtils {

    @Value("${upload.path.static}")
    private String staticPath;

    @Value("${upload.path.real}")
    private String filePath;

    @Autowired
    private AttachService attachService;

    public boolean isImage(MultipartFile file){
        try{
            Image image = ImageIO.read(file.getInputStream());
            return image != null && image.getHeight(null) > 0 && image.getWidth(null) > 0;
        } catch (IOException e) {
            return false;
        }
    }

    public String upload(AdminUser adminUser, MultipartFile file){
        String name = file.getOriginalFilename();
        String suffixName = name.substring(name.lastIndexOf("."));
        String filename = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + filename);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try{
            file.transferTo(dest);
            Attach attach = new Attach();
            attach.setCreator(adminUser.getId());
            attach.setFname(name);
            attach.setFtype(isImage(file)?0:1);
            attach.setFkey(staticPath + filename);
            attachService.saveAttach(attach);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }

}
