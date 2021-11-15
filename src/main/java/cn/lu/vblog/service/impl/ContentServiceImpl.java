package cn.lu.vblog.service.impl;

import cn.lu.vblog.dto.ContentDTO;
import cn.lu.vblog.entity.AdminUser;
import cn.lu.vblog.entity.Content;
import cn.lu.vblog.mapper.ContentMapper;
import cn.lu.vblog.service.ContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lkxBruce
 * @since 2021-11-15
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public void saveContent(ContentDTO contentDTO){
        Subject subject = SecurityUtils.getSubject();
        AdminUser adminUser = (AdminUser) subject.getPrincipal();
        Content content = initContent();
        content.setContent(contentDTO.getContent());
        content.setTitle(contentDTO.getTitle());
        content.setCategory(contentDTO.getCategories());
        content.setCreator(adminUser.getId());
        contentMapper.insert(content);
    }

    @Override
    public Content initContent(){
        Content content = new Content();
        content.setViewCount(0L);
        content.setLikeCount(0L);
        content.setCommentCount(0L);
        return content;
    }

}
