package com.nisoft.androidlib.slidemenu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.Scroller;
import android.widget.TextView;

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
    private float mDownX;
    private float mStartY;
    private Scroller mScroller;
    private OnMenuStateChangedListener mMenuStateChangedListener;

    public SlideMenuLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mContentWidth = mContentView.getMeasuredWidth();
        Log.e(TAG, "mContentWidth" + mContentWidth);
        mMenuWidth = mMenuView.getMeasuredWidth();
        Log.e(TAG, "mMenuWidth" + mMenuWidth);
        mLayoutHeight = getMeasuredHeight();
        Log.e(TAG, "mLayoutHeight" + mLayoutHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mMenuView.layout(mContentWidth, 0, mContentWidth + mMenuWidth, mLayoutHeight);
    }


    private float mLastTouchX;
    private float mLastTouchY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case ACTION_DOWN:
                mLastTouchX = event.getX();
                mLastTouchY = event.getY();
                if (mMenuStateChangedListener != null) {
                    mMenuStateChangedListener.onDown(this);
                }
                break;
            case ACTION_MOVE:
                int distanceX = (int) (event.getX() - mLastTouchX);
                int distanceY = (int) (event.getY() - mLastTouchY);
                int targetX = getScrollX() - distanceX;
                if (targetX > mMenuWidth) {
                    targetX = mMenuWidth;
                } else if (targetX < 0) {
                    targetX = 0;
                }
                if (Math.abs(distanceX) > Math.abs(distanceY) ) {
                    //当在水平位置移动时，阻止父视图拦截事件
                    scrollTo(targetX, getScrollY());
                    mLastTouchX = event.getX();
                    if( Math.abs(distanceX) > 3) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                }
                break;
            case ACTION_UP:
                int totalScrollX = getScrollX();
                if (totalScrollX > mMenuWidth / 2 && totalScrollX > 5) {
                    openMenu();

                } else {
                    closeMenu();
                }
                break;
        }
        return true;
    }

    public void closeMenu() {
        mScroller.startScroll(getScrollX(), getScrollY(), 0 - getScrollX(), getScrollY());
        if (mScroller.computeScrollOffset()) {
            invalidate();
        }
        if (mMenuStateChangedListener != null) {
            mMenuStateChangedListener.onMenuClosed(this);
        }
    }

    public void openMenu() {
        mScroller.startScroll(getScrollX(), getScrollY(), mMenuWidth - getScrollX(), getScrollY());
        if (mScroller.computeScrollOffset()) {
            invalidate();
        }
        if (mMenuStateChangedListener != null) {
            mMenuStateChangedListener.onMenuOpened(this);
        }
    }

    /**
     * invalidate()调用后会执行此方法，不断递归直到滚动完成
     */
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    /**
     * 当视图加载完成后执行
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContentView = (TextView) getChildAt(0);
        mMenuView = (TextView) getChildAt(1);
    }

    /**
     * 当水平滑动时拦截触摸事件
     *
     * @param ev 触摸事件
     * @return true 拦截事件，执行onTouchEvent(MotionEvent);false 不拦截，事件继续传递
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        switch (ev.getAction()) {
            case ACTION_DOWN:
                mDownX = ev.getX();
                if (mMenuStateChangedListener != null) {
                    mMenuStateChangedListener.onDown(this);
                }
                break;
            case ACTION_MOVE:
                float dx = Math.abs(ev.getX() - mDownX);
                if (dx > 5) {
                    intercept = true;
                }
                break;
            case ACTION_UP:

                break;
        }
        return intercept;
    }

    public void setMenuStateChangedListener(OnMenuStateChangedListener menuStateChangedListener) {
        mMenuStateChangedListener = menuStateChangedListener;
    }

    public interface OnMenuStateChangedListener {
        void onMenuOpened(SlideMenuLayout layout);

        void onMenuClosed(SlideMenuLayout layout);

        void onDown(SlideMenuLayout layout);
    }
}
