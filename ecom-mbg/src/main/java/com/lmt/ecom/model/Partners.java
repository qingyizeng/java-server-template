package com.lmt.ecom.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class Partners implements Serializable {
    private Integer id;

    private String name;

    private String email;

    private String phone;

    private String companyName;

    private String availableTimes;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAvailableTimes() {
        return availableTimes;
    }

    public void setAvailableTimes(String availableTimes) {
        this.availableTimes = availableTimes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", email=").append(email);
        sb.append(", phone=").append(phone);
        sb.append(", companyName=").append(companyName);
        sb.append(", availableTimes=").append(availableTimes);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}