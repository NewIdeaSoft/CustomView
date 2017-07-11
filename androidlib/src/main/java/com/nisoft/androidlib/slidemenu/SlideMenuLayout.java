package com.nisoft.androidlib.slidemenu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.nisoft.androidlib.R;
import com.nisoft.androidlib.utils.ScreenFitUtil;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

/**
 * Created by Administrator on 2017/7/11.
 */

public class SlideMenuLayout extends FrameLayout {

    private static final String TAG = "SlideMenuLayout";
    private TextView mContentView;
    private TextView mMenuView;
    private int mContentWidth;
    private int mMenuWidth;
    private int mLayoutHeight;
    private float mStartX;
    private float mStartY;
    private Scroller mScroller;

    public SlideMenuLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mContentWidth = mContentView.getMeasuredWidth();
        Log.e(TAG,"mContentWidth"+mContentWidth);
        mMenuWidth = mMenuView.getMeasuredWidth();
        Log.e(TAG,"mMenuWidth"+mMenuWidth);
        mLayoutHeight = getMeasuredHeight();
        Log.e(TAG,"mLayoutHeight"+mLayoutHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mMenuView.layout(mContentWidth, 0, mContentWidth + mMenuWidth, mLayoutHeight);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case ACTION_DOWN:
                mStartX = event.getX();
                mStartY = event.getY();
                break;
            case ACTION_MOVE:
                int distanceX = (int) (event.getX() - mStartX);
                int distanceY = (int) (event.getY() - mStartY);
                int targetX = getScrollX() -distanceX;
                if(targetX>mMenuWidth) {
                    targetX = mMenuWidth;
                }else if(targetX<0) {
                    targetX = 0;
                }
                if(Math.abs(distanceX)>Math.abs(distanceY)&&Math.abs(distanceX)>5) {
//                    requestDisallowInterceptTouchEvent(true);
                }
                scrollTo(targetX,getScrollY());
                break;
            case ACTION_UP:
                int totalScrollX = getScrollX();
                if(totalScrollX>mMenuWidth/2&&totalScrollX>5) {
                    openMenu();
                }else {
                    closeMenu();
                }
                break;
        }
        return true;
    }

    private void closeMenu() {
        mScroller.startScroll(getScrollX(),getScrollY(),0-getScrollX(),getScrollY());
        if(mScroller.computeScrollOffset()) {
            invalidate();
        }
    }

    private void openMenu() {
        mScroller.startScroll(getScrollX(),getScrollY(),mMenuWidth-getScrollX(),getScrollY());
        if(mScroller.computeScrollOffset()) {
            invalidate();
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContentView = (TextView) getChildAt(0);
        mMenuView = (TextView) getChildAt(1);
    }

    public TextView getContentView() {
        return mContentView;
    }

    public TextView getMenuView() {
        return mMenuView;
    }
}
