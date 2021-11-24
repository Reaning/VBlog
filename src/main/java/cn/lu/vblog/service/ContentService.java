package cn.lu.vblog.service;

import cn.lu.vblog.dto.ArticleDTO;
import cn.lu.vblog.dto.ContentDTO;
import cn.lu.vblog.entity.Content;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lkxBruce
 * @since 2021-11-15
 */
public interface ContentService{

    void saveContent(ContentDTO contentDTO);

    Content initContent();

    Content getContentById(Long id);

    PageInfo<Content> selectPage(int page, int limit);

    void deleteById(Long id);

    void modifyContent(Content content,ContentDTO contentDTO);

    PageInfo<ArticleDTO> getArticlePages(Integer page, Integer limit);
}
