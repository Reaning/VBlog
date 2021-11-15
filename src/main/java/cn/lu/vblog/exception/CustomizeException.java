package cn.lu.vblog.exception;

/**
 * com.example.vblog.exception
 *
 * @author lkxBruce
 * @date 2021/11/10 17:17
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
public class CustomizeException extends RuntimeException {

    public CustomizeException(ICustomizeErrorCode customizeErrorCode){
        code = customizeErrorCode.getCode();
        message = customizeErrorCode.getMessage();
    }

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
