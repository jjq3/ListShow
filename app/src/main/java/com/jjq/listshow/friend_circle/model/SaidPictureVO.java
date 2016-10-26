package com.jjq.listshow.friend_circle.model;

/**
 * Created by jiangjieqiang on 2016/10/26.
 */

public class SaidPictureVO {

    /**
     * 图片链接
     */
    private String imgUrl;

    /**
     * 图片说明
     */
    private String desc;

    /**
     * 本地图片
     */
    private int localImg;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getLocalImg() {
        return localImg;
    }

    public void setLocalImg(int localImg) {
        this.localImg = localImg;
    }

    public SaidPictureVO(String imgUrl, String desc) {
        this.imgUrl = imgUrl;
        this.desc = desc;
    }

    public SaidPictureVO(int localImg, String desc) {
        this.localImg = localImg;
        this.desc = desc;
    }
}
