package com.nisoft.androidlib.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.nisoft.androidlib.utils.CusScroller;

/**
 * Created by Administrator on 2017/11/3.
 */

public class CusViewPager extends ViewGroup {
    /**
     * 手势识别器
     * 使用步骤：
     * 1.定义手势识别器
     * 2.实例化：实现OnGestureListener接口
     * 3.将触摸事件传递给手势识别器
     */
    private GestureDetector mDetector;
    /**
     * 触摸事件起始X坐标
     */
    private float mSlideStartX;
    /**
     * 当前显示页面的位置索引,0为起始页面
     */
    private int mCurrentIndex = 0;

    private CusScroller mScroller;

    public CusViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mScroller = new CusScroller();
        //实例化手势识别器
        mDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return super.onSingleTapUp(e);
            }

            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                scrollBy((int) distanceX,0);
                return true;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                return super.onDoubleTap(e);
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0;i<getChildCount();i++){
            View view = getChildAt(i);
            view.layout(i*getWidth(),0,(i+1)*getWidth(),getHeight());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        //将触摸事件传递给手势识别器
        mDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                mSlideStartX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE :

                break;
            case MotionEvent.ACTION_UP :
                float slideEndX = event.getX();
                float distanceX = slideEndX - mSlideStartX;
                int templateIndex = mCurrentIndex;
                if(Math.abs(distanceX)>getWidth()/2) {
                    if(distanceX>getWidth()/2) {
                        templateIndex-- ;
                    }else{
                        templateIndex++;
                    }
                }
                scrollToPage(templateIndex);
                break;
        }
        return true;
    }

    /**
     * 作用:滚动到指定页面
     * @param templateIndex 目标页面的位置索引
     */
    private void scrollToPage(int templateIndex) {
        if(templateIndex <0) {
            templateIndex = 0;
        }else if(templateIndex>getChildCount()-1) {
            templateIndex = getChildCount()-1;
        }
//        scrollTo(templateIndex*getWidth(),0);
//        mScroller.startScroll();
        mCurrentIndex = templateIndex;
    }
}
