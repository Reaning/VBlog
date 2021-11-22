package cn.lu.vblog.service.impl;

import cn.lu.vblog.dto.ContentDTO;
import cn.lu.vblog.entity.AdminUser;
import cn.lu.vblog.entity.Content;
import cn.lu.vblog.mapper.ContentMapper;
import cn.lu.vblog.service.ContentService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        content.setSubtitle(contentDTO.getSubTitle());
        content.setStatus(contentDTO.getStatus());
        content.setCategory(contentDTO.getCategories());
        content.setCreator(adminUser.getId());
        content.setTag(contentDTO.getTag());
        contentMapper.insert(content);
    }

    @Override
    public Content initContent(){
        Content content = new Content();
        content.setViewCount(0L);
        content.setLikeCount(0L);
        content.setCommentCount(0L);
        content.setVersion(1);
        return content;
    }

    @Override
    public Content getContentById(Long id){
        return contentMapper.selectById(id);
    }

    @Override
    public PageInfo<Content> selectPage(int page, int limit) {
        PageHelper.startPage(page,limit);
        List<Content> contents = contentMapper.selectList(null);
        PageInfo<Content> pageInfo = new PageInfo<>(contents);
        return pageInfo;
    }

    @Override
    public void deleteById(Long id) {
        contentMapper.deleteById(id);
    }

    @Override
    public void modifyContent(Content content, ContentDTO contentDTO) {
        content.setSubtitle(contentDTO.getSubTitle());
        content.setCategory(contentDTO.getCategories());
        content.setTitle(contentDTO.getTitle());
        content.setStatus(contentDTO.getStatus());
        content.setTag(contentDTO.getTag());
        content.setContent(contentDTO.getContent());
        contentMapper.updateById(content);
    }

}
