package cn.lu.vblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * cn.lu.vblog.dto
 *
 * @author lkxBruce
 * @date 2021/11/15 14:41
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentDTO {
    private String title;
    private String subtitle;
    private String imgUrl;
    private String content;
    private Integer status;
    private String tag;
    private String category;
    private Long id;
}
