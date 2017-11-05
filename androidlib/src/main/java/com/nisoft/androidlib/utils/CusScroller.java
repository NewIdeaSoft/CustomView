package com.nisoft.androidlib.utils;

import android.os.SystemClock;
import android.util.Log;

/**
 * Created by Administrator on 2017/11/5.
 * 用来控制视图平滑滚动
 */

public class CusScroller {
    private static final String TAG = "CusScroller";
    private float mStartX;
    private float mStartY;
    private float mDistanceX;
    private float mDistanceY;
    /**
     * 是否滚动完成
     */
    private boolean isFinished = true;
    private long mStartTime;
    private long mDuring = 200;
    private float mVelocityX;
    private float mVelocityY;
    private float mCurrentX;
    private float mCurrentY;

    public CusScroller() {
    }


    /**
     * 初始化
     * @param startX 起始x坐标
     * @param startY 起始y坐标
     * @param distanceX x轴移动距离
     * @param distanceY y轴移动距离
     * @param during 持续时间
     */
    public void startScroll(float startX, float startY, float distanceX, float distanceY,long during) {
        mStartX = startX;
        mStartY = startY;
        mDistanceX = distanceX;
        mDistanceY = distanceY;
        mStartTime = SystemClock.uptimeMillis();
        mVelocityX = mDistanceX / mDuring;
        mVelocityY = mDistanceY / mDuring;
        isFinished = false;
        if(during>0) {
            mDuring = during;
        }
    }

    /**
     * 用于计算每次滚动的目标坐标
     * @return true 继续滚动 false 滚动结束
     */
    public boolean computeScrollOffset() {
        if (isFinished) {
            return false;
        }
        long endTime = SystemClock.uptimeMillis();
        long passTime = endTime - mStartTime;
        if (passTime < mDuring) {
            float passDistanceX = passTime * mVelocityX;
            float passDistanceY = passTime * mVelocityY;
            mCurrentX = mStartX + passDistanceX;
            mCurrentY = mStartY + passDistanceY;
        } else {
            mCurrentX = mStartX+mDistanceX;
            mCurrentY = mStartY+mDistanceY;
            isFinished = true;
        }
        return true;
    }

    /**
     *
     * @return 目标坐标x
     */
    public float getCurrentX() {
        return mCurrentX;
    }

    /**
     *
     * @return 目标坐标y
     */
    public float getCurrentY() {
        return mCurrentY;
    }
}
