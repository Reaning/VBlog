package cn.lu.vblog;

import cn.lu.vblog.dto.Result.MessageDTO;
import com.alibaba.fastjson.JSON;
import com.mysql.cj.protocol.Message;

/**
 * cn.lu.vblog
 *
 * @author lkxBruce
 * @date 2021/11/15 14:20
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
public class ApiResult {
    public static String success(){
        MessageDTO success = new MessageDTO("success", "");
        return JSON.toJSONString(success);
    }
    public static String success(String message){
        MessageDTO success = new MessageDTO("success", message);
        return JSON.toJSONString(success);
    }
    public static String fail(String message){
        MessageDTO fail = new MessageDTO("fail", message);
        return JSON.toJSONString(fail);
    }
}
