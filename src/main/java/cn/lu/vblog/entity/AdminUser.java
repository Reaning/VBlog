package cn.lu.vblog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author lkxBruce
 * @since 2021-11-15
 */
@Getter
@Setter
@TableName("admin_user")
@ApiModel(value = "AdminUser对象", description = "")
public class AdminUser implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String email;

    private String name;

    private String avatarUrl;

    private String homeUrl;

    private Integer active;

      @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;

    @Version
    private Integer version;

    @TableLogic
    private Integer deleted;


}
