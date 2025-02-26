package com.lmt.ecom.common.enums;

/**
 * 登录类型枚举
 */
public enum LoginType {
    SYSTEM(1, "系统"),
    GOOGLE(2, "Google"),
    FACEBOOK(3, "FaceBook");

    private Integer key;
    private String value;

    LoginType(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
