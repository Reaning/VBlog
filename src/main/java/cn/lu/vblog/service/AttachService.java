package cn.lu.vblog.service;

import cn.lu.vblog.entity.Attach;
import com.github.pagehelper.PageInfo;

/**
 * cn.lu.vblog.service
 *
 * @author lkxBruce
 * @date 2021/11/22 18:56
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
public interface AttachService {
    void saveAttach(Attach attach);

    PageInfo<Attach> selectPage(Integer page, Integer limit);
}
