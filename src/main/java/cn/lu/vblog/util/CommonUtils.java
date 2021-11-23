package cn.lu.vblog.util;

import cn.lu.vblog.entity.AdminUser;
import cn.lu.vblog.entity.Attach;
import cn.lu.vblog.entity.Content;
import cn.lu.vblog.mapper.ContentMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * cn.lu.vblog.util
 *
 * @author lkxBruce
 * @date 2021/11/21 21:11
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
@Component
public class CommonUtils {
    @Autowired
    private ContentMapper contentMapper;

    public Integer getArticleNum(){
        Subject subject = SecurityUtils.getSubject();
        AdminUser user = (AdminUser) subject.getPrincipal();
        Long selectCount = contentMapper.selectCount(new QueryWrapper<Content>().eq("id",user.getId()));
        return selectCount.intValue();
    }

//    public boolean is_empty(PageInfo<Attach> attachs){
//        if (attachs.get)
//    }

}
