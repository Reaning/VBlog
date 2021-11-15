package cn.lu.vblog.service;

import cn.lu.vblog.dto.ContentDTO;
import cn.lu.vblog.entity.Content;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
