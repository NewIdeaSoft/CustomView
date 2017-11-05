package com.nisoft.androidlib.utils;

import android.content.Context;
import android.os.SystemClock;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/11/5.
 * 用来控制视图平滑滚动
 */

public class CusScroller {
    private float mStartX;
    private float mStartY;
    private float mDistanceX;
    private float mDistanceY;
    private boolean isFinished = false;
    private long mStartTime;
    private long mTotalTime;
    private float mVoleCityX;
    private float mVoleCityY;
    private float mCurrentX;
    private float mCurrentY;

    public CusScroller() {
        mTotalTime = 500;
    }

    public CusScroller(long totalTime) {
        if (totalTime <= 0) {
            mTotalTime = 500;
        } else {
            mTotalTime = totalTime;
        }
    }

    public void startScroll(float startX, float startY, float distanceX, float distanceY) {
        mStartX = startX;
        mStartY = startY;
        mDistanceX = distanceX;
        mDistanceY = distanceY;
        mStartTime = SystemClock.currentThreadTimeMillis();
        mVoleCityX = mDistanceX / mTotalTime;
        mVoleCityY = mDistanceY / mTotalTime;
    }

    public boolean aotuScroll() {
        if (isFinished) {
            return false;
        }
        long endTime = SystemClock.currentThreadTimeMillis();
        long passTime = endTime - mStartTime;
        if (passTime < mTotalTime) {
            float passDistanceX = passTime * mVoleCityX;
            float passDistanceY = passTime * mVoleCityY;
            mCurrentX = mStartX + passDistanceX;
            mCurrentY = mStartY + passDistanceY;
            return false;
        } else {
            mCurrentX = mStartX+mDistanceX;
            mCurrentY = mStartY+mDistanceY;
            return true;
        }
    }
}
