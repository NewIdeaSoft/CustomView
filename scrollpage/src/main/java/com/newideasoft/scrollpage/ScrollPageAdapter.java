package com.newideasoft.scrollpage;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by NewIdeaSoft on 2017/10/31.
 */

public class ScrollPageAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<PageData> mPageDatas;
    private ImageEventListener mImageEventListener;

    public ScrollPageAdapter(Context context, ArrayList<PageData> pageDatas, ImageEventListener imageEventListener) {
        mContext = context;
        mPageDatas = pageDatas;
        mImageEventListener = imageEventListener;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    public interface ImageEventListener {
        void onImageTouch();

        void onImageClick();
    }
}
