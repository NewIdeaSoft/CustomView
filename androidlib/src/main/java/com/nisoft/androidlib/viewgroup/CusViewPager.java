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

    private CusScroller mCusScroller;
//    private Scroller mScroller;

    private onPageChangedListener mOnPageChangedListener;
    private float mDownX;
    private float mDownY;

    public CusViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
//        mScroller = new Scroller(context);
        mCusScroller = new CusScroller();
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
        if(mOnPageChangedListener!=null&&templateIndex!=mCurrentIndex) {
            mOnPageChangedListener.onPageChanged(templateIndex);
        }
        mCurrentIndex = templateIndex;
//        mScroller.startScroll(getScrollX(),0,mCurrentIndex*getWidth()-getScrollX(),0);
        mCusScroller.startScroll(getScrollX(),0,mCurrentIndex*getWidth()-getScrollX(),0,200);
        invalidate();
    }

    @Override
    public void computeScroll() {
//        if(mScroller.computeScrollOffset()) {


        if(mCusScroller.computeScrollOffset()) {
            scrollTo((int) mCusScroller.getCurrentX(), (int) mCusScroller.getCurrentY());
            invalidate();
        }
    }

    /**
     * 用与监听页面变化的接口
     */
    public interface onPageChangedListener{
        /**
         * 当页面变化时调用该方法
         * @param position
         */
        void onPageChanged(int position);
    }

    public void setOnPageChangedListener(onPageChangedListener onPageChangedListener) {
        mOnPageChangedListener = onPageChangedListener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i= 0 ;i<getChildCount();i++){
            getChildAt(i).measure(widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        mDetector.onTouchEvent(ev);
        boolean result = false;
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDownX = ev.getX();
                mDownY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = ev.getX();
                float endY = ev.getY();
                float distanceX = Math.abs(endX- mDownX);
                float distanceY = Math.abs(endY- mDownY);
                if(distanceX>distanceY&&distanceX>3) {
                    result = true;
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return result;
    }
}
