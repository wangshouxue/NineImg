package com.example.my;


import com.example.nineimagelib.NineImageEntity;

import java.io.Serializable;

/**
 * @author:wangshouxue
 * @date:2019-10-23 16:36
 * @description:类作用
 */
public class DataEntity implements NineImageEntity, Serializable {
    private int width;
    private int height;
    private String url;

    public DataEntity(int height, int width, String url){
        this.width=width;
        this.height=height;
        this.url=url;
    }
    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String getUrl() {
        return url;
    }
}
