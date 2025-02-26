package com.lmt.ecom.common.domain;

import lombok.Data;
@Data
public class MailerLiteGroup {
    private String id;

    private String name;

    public MailerLiteGroup(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
