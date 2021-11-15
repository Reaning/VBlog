package cn.lu.vblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * com.example.vblog.pojo
 *
 * @author lkxBruce
 * @date 2021/11/9 16:16
 * @email lkxbruce@gmail.com
 * @project VBlog
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUser implements Serializable{

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty(value = "主键")
//    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

//    @NotBlank(message = "用户名不能为空" , groups = {AddGroup.class, UpdateGroup.class})
//    @ApiModelProperty(value = "用户名")
    private String username;

//    @NotBlank(message = "密码不能为空" ,groups = AddGroup.class)
//    @ApiModelProperty(value = "密码")
    private String password;

//    @NotBlank(message="邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
//    @Email(message="邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
//    @ApiModelProperty(value = "邮箱")
    private String email;

//    @ApiModelProperty(value = "密码盐")
    private String salt;

//    @ApiModelProperty(value = "创建者Id")
    private Integer createUserId;

//    @ApiModelProperty(value = "创建时间")
    private Date createTime;

//    @ApiModelProperty(value = "0禁用，1正常")
    private Integer status;

//    @TableField(exist=false)
    private List<Integer> roleIdList;


}
