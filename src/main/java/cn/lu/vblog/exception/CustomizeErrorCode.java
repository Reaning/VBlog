package cn.lu.vblog.exception;

/**
 * com.example.vblog.exception
 *
 * @author lkxBruce
 * @date 2021/11/10 17:04
 * @email lkxbruce@gmail.com
 * @project VBlog
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode{
    UNAUTHORIZED(1001,"权限不足！请登录！"),
    OVERTIME(1002,"已超时！请重新登录！");
    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
