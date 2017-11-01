package com.newideasoft.scrollpage;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
        ImageView imageView = new ImageView(mContext);
        final int realPosition = position%mPageDatas.size();
        imageView.setImageResource(mPageDatas.get(realPosition).getImageResouce());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageEventListener.onImageClick(realPosition);
            }
        });

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return mImageEventListener.onImageTouch(v, event);
            }
        });
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    public interface ImageEventListener {
        boolean onImageTouch(View v, MotionEvent event);

        void onImageClick(int position);
    }
}
