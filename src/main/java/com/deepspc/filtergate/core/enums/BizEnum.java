package com.deepspc.filtergate.core.enums;

public enum BizEnum {

    BUSSINESS("BIZ", "业务日志"),
    EXCEPTION("EXC", "异常日志"),
    OPERATION("OPA", "操作日志"),

    FAIL("F", "失败"),
    SUCCESS("S", "成功"),

    YES("Y", "是"),
    NO("N", "否"),

    ENABLE("ENABLE", "可用"),
    DISABLE("DISABLE", "不可用"),
    FREEZED("LOCKED", "冻结"),
    DELETED("DELETED", "被删除")
    ;

    String code;

    String message;

    BizEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String getMessage(String code) {
        if (code == null) {
            return null;
        } else {
            for (BizEnum bizEnum : BizEnum.values()) {
                if (bizEnum.getCode().equals(code)) {
                    return bizEnum.getMessage();
                }
            }
            return null;
        }
    }
}
