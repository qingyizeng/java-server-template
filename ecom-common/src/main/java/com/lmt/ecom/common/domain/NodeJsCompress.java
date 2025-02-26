package com.lmt.ecom.common.domain;

/**
 * nodeJs压缩参数
 */
public class NodeJsCompress {
    private String image;
    private Options options;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public static class Options {

        public Options(double size, double sharpen, double quality) {
            this.size = size;
            this.sharpen = sharpen;
            this.quality = quality;
        }

        public Options(double size, double sharpen, double quality, boolean watermark) {
            this.size = size;
            this.sharpen = sharpen;
            this.quality = quality;
            this.watermark = watermark;
        }

        public Options(double size, double sharpen, double quality, boolean price, GoodsParam goods) {
            this.size = size;
            this.sharpen = sharpen;
            this.quality = quality;
            this.price = price;
            this.goods = goods;
        }

        private double size;
        private double sharpen;
        private double quality;
        private boolean price;
        private GoodsParam goods;
        private boolean watermark = true;

        public double getSize() {
            return size;
        }

        public void setSize(double size) {
            this.size = size;
        }

        public double getSharpen() {
            return sharpen;
        }

        public void setSharpen(double sharpen) {
            this.sharpen = sharpen;
        }

        public double getQuality() {
            return quality;
        }

        public void setQuality(double quality) {
            this.quality = quality;
        }

        public boolean isPrice() {
            return price;
        }

        public void setPrice(boolean price) {
            this.price = price;
        }

        public GoodsParam getGoods() {
            return goods;
        }

        public void setGoods(GoodsParam goods) {
            this.goods = goods;
        }

        public boolean isWatermark() {
            return watermark;
        }

        public void setWatermark(boolean watermark) {
            this.watermark = watermark;
        }
    }
}
