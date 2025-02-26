package com.lmt.ecom.common.domain;

/**
 * nodeJs压缩结果
 */
public class CompressResult {
    private String url;
    private Long length;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }
}
