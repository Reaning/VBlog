package cn.lu.vblog.mapper;

import cn.lu.vblog.dto.ArticleDTO;
import cn.lu.vblog.entity.Content;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lkxBruce
 * @since 2021-11-15
 */
public interface ContentMapper extends BaseMapper<Content> {
    List<ArticleDTO> getAllArticle();
    Content getAllContent();
}
