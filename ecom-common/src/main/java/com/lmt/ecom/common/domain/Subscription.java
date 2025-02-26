package com.lmt.ecom.common.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel(value = "订阅")
@NoArgsConstructor
@Getter
@Setter
public class Subscription {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(accessMode = ApiModelProperty.AccessMode.READ_ONLY)
    private Long id;

    @ApiModelProperty("店铺")
    private String store;

    @ApiModelProperty("邮箱")
    @Email(message = "Please enter a valid email address.")
    @Size(max = 200)
    private String email;

    private Date createdDate;

    public Subscription(String email, String store) {
        this.email = email;
        this.store = store;
    }
}
