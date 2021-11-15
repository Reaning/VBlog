package cn.lu.vblog.service.impl;

import cn.lu.vblog.entity.Content;
import cn.lu.vblog.mapper.ContentMapper;
import cn.lu.vblog.service.IContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements IContentService {

}
