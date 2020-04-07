package com.deepspc.filtergate.core.exception;

public enum CoreExceptionEnum {

    NO_CURRENT_USER(700, "当前没有登录的用户"),

    ENCRYPT_ERROR(600, "加解密错误"),
    WRITE_ERROR(500, "渲染界面错误"),

    INIT_TABLE_EMPTY_PARAMS(701, "初始化数据库，存在为空的字段"),
    FIELD_VALIDATE_ERROR(700, "数据库字段与实体字段不一致!"),

    FILE_READING_ERROR(400, "FILE_READING_ERROR!"),
    FILE_NOT_FOUND(400, "FILE_NOT_FOUND!"),

    PAGE_NULL(404, "请求页面不存在"),
    SERVICE_ERROR(500, "服务器异常")
    ;

    private Integer code;

    private String message;

    CoreExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
