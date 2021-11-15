package cn.lu.vblog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author lkxBruce
 * @since 2021-11-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Content对象", description = "")
public class Content implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    private String title;

    private String imgUrl;

    private String subtitle;

    private String content;

    private Long creator;

    private Integer status;

    private Long viewCount;

    private Long likeCount;

    private Long commentCount;

    @Version
    private Integer version;

      @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long gmtModified;

    private String category;

    private String tag;

    @TableLogic
    private Integer deleted;


}
