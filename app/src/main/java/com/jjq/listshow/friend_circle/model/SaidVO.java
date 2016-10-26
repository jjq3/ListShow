package com.jjq.listshow.friend_circle.model;

import java.util.List;

/**
 * Created by jiangjieqiang on 2016/10/26.
 */

public class SaidVO {

    /**
     * 说说的内容
     */
    private String content;

    /**
     * 说说的图片
     */
    private List<SaidPictureVO> saidPictureVOs;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<SaidPictureVO> getSaidPictureVOs() {
        return saidPictureVOs;
    }

    public void setSaidPictureVOs(List<SaidPictureVO> saidPictureVOs) {
        this.saidPictureVOs = saidPictureVOs;
    }
}
