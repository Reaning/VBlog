package cn.lu.vblog.service.impl;

import cn.lu.vblog.dto.ArticleDTO;
import cn.lu.vblog.dto.ContentDTO;
import cn.lu.vblog.entity.AdminUser;
import cn.lu.vblog.entity.Content;
import cn.lu.vblog.exception.CustomizeErrorCode;
import cn.lu.vblog.exception.CustomizeException;
import cn.lu.vblog.mapper.ContentMapper;
import cn.lu.vblog.service.AdminService;
import cn.lu.vblog.service.ContentService;
import cn.lu.vblog.util.CommonUtils;
import cn.lu.vblog.util.SysUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private AdminService adminService;


    @Override
    public void saveContent(ContentDTO contentDTO){
        Subject subject = SecurityUtils.getSubject();
        AdminUser adminUser = (AdminUser) subject.getPrincipal();
        Content content = initContent();
        content.setContent(contentDTO.getContent());
        content.setTitle(contentDTO.getTitle());
        content.setSubtitle(contentDTO.getSubtitle());
        content.setImgUrl(contentDTO.getImgUrl());
        content.setStatus(contentDTO.getStatus());
        content.setCategory(contentDTO.getCategory());
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
        AdminUser user = adminService.getCurrentUser();
        PageHelper.startPage(page,limit);
        QueryWrapper<Content> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("creator",user.getId());
        List<Content> contents = contentMapper.selectList(queryWrapper);
        PageInfo<Content> pageInfo = new PageInfo<>(contents);
        return pageInfo;
    }

    @Override
    public void deleteById(Long id) {
        contentMapper.deleteById(id);
    }

    @Override
    public void modifyContent(Content content, ContentDTO contentDTO) {
        BeanUtils.copyProperties(contentDTO,content);
        contentMapper.updateById(content);
    }

    @Override
    public PageInfo<ArticleDTO> getArticlePages(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Content> contents = contentMapper.selectList(null);
        List<ArticleDTO> articleDTOS = contents.stream().map(content -> {
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setId(content.getId());
            articleDTO.setSubtitle(content.getSubtitle());
            articleDTO.setTitle(content.getTitle());
            articleDTO.setGmtCreate(content.getGmtCreate());
            AdminUser user = adminService.getUserById(content.getCreator());
            articleDTO.setName(user.getName());
            return articleDTO;
        }).collect(Collectors.toList());
        PageInfo<ArticleDTO> pageInfo = new PageInfo<>(articleDTOS);
        return pageInfo;
    }

}
