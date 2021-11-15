package cn.lu.vblog.dto.Result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * cn.lu.vblog.dto.Result
 *
 * @author lkxBruce
 * @date 2021/11/15 14:22
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private String code;
    private String msg;
}
