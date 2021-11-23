package cn.lu.vblog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * cn.lu.vblog.entity
 *
 * @author lkxBruce
 * @date 2021/11/22 17:23
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attach implements Serializable {

    @TableId(value = "ID",type = IdType.AUTO)
    private Long id;

    private String fname;

    private Integer ftype;

    private String fkey;

    private Long creator;

    @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

}
