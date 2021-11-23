package cn.lu.vblog.service.impl;

import cn.lu.vblog.entity.AdminUser;
import cn.lu.vblog.entity.Attach;
import cn.lu.vblog.mapper.AttachMapper;
import cn.lu.vblog.service.AdminService;
import cn.lu.vblog.service.AttachService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * cn.lu.vblog.service.impl
 *
 * @author lkxBruce
 * @date 2021/11/22 18:57
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
@Service
public class AttachServiceImpl implements AttachService {

    @Autowired
    private AttachMapper attachMapper;

    @Autowired
    private AdminService adminService;

    @Override
    public void saveAttach(Attach attach) {
        attachMapper.insert(attach);
    }

    @Override
    public PageInfo<Attach> selectPage(Integer page,Integer limit) {
        AdminUser user = adminService.getCurrentUser();
        QueryWrapper<Attach>wrapper = new QueryWrapper<>();
        wrapper.eq("creator",user.getId());
        PageHelper.startPage(page,limit);
        List<Attach> attachList = attachMapper.selectList(wrapper);
        PageInfo<Attach> attachPageInfo = new PageInfo<>(attachList);
        return attachPageInfo;
    }
}
