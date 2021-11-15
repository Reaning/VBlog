package cn.lu.vblog.config.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * cn.lu.vblog.config
 *
 * @author lkxBruce
 * @date 2021/11/15 17:07
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("gmtCreate",System.currentTimeMillis(),metaObject);
        this.setFieldValByName("gmtModified",System.currentTimeMillis(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified",System.currentTimeMillis(),metaObject);
    }
}
