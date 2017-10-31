package com.newideasoft.scrollpage;

/**
 * Created by NewIdeaSoft on 2017/10/31.
 */

public class PageData {
    private int mImageResouce;
    private String mImageTitle;

    public PageData() {
    }

    public PageData(int imageResouce, String imageTitle) {
        mImageResouce = imageResouce;
        mImageTitle = imageTitle;
    }

    public int getImageResouce() {
        return mImageResouce;
    }

    public void setImageResouce(int imageResouce) {
        mImageResouce = imageResouce;
    }

    public String getImageTitle() {
        return mImageTitle;
    }

    public void setImageTitle(String imageTitle) {
        mImageTitle = imageTitle;
    }
}
