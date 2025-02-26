package com.lmt.ecom.common.domain;

import lombok.Data;

@Data
public class GoodsParam {
    private Integer discount_type;
    private String itm_upc_code;
    private String brand;
    private String name;
    private String spec;
    private String cur_price;
    private String source_price;
    private String discount_price;
    private String endTime;
}
