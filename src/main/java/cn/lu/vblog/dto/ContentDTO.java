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
    private String content;
    private String type;
    private String status;
    private String tags;
    private String categories;
    private Long cid;

}
