package com.nisoft.androidlib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/11/6.
 * 用来作为联系人列表的索引
 */

public class ContactsIndexView extends View {
    private String[] mIndexWords = {"A", "B", "C", "D", "E", "F", "G"
            , "H", "I", "J", "K", "L", "M", "N"
            , "O", "P", "Q", "R", "S", "T"
            , "U", "V", "W", "X", "Y", "Z"};
    private int mTouchedIndex = -1;
    private OnIndexChangedListener mOnIndexChangedListener;
    private Paint mTextPaint;
    private int mItemHeight;
    private int mItemWidth;
    private int mTextHeight;
    private int mTextWidth;

    public ContactsIndexView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mItemHeight = getHeight() / mIndexWords.length;
        mTextPaint.setTextSize(mItemHeight/2);
        mItemWidth = getWidth();
        Rect rect = new Rect();
        mTextPaint.getTextBounds(mIndexWords[0], 0, 1, rect);
        mTextHeight = rect.height();
        mTextWidth = rect.width();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mIndexWords.length; i++) {
            String word = mIndexWords[i];
            Rect rect = new Rect();
            mTextPaint.getTextBounds(word, 0, 1, rect);
            float wordX = mItemWidth / 2 - mTextWidth / 2;
            float wordY = mItemHeight * (i + 0.5f) + mTextHeight / 2;
            canvas.drawText(word, wordX, wordY, mTextPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                int touchingIndex = (int) (event.getY() / mItemHeight);
                if(touchingIndex>=mIndexWords.length) {
                    touchingIndex = mIndexWords.length-1;
                }else if(touchingIndex<0) {
                    touchingIndex = 0;
                }
                if (touchingIndex != mTouchedIndex) {
                    mTouchedIndex = touchingIndex;
                    mOnIndexChangedListener.onIndexChanged(mIndexWords[mTouchedIndex]);
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }

    public void setOnIndexChangedListener(OnIndexChangedListener onIndexChangedListener) {
        mOnIndexChangedListener = onIndexChangedListener;
    }

    public interface OnIndexChangedListener {
        /**
         * @param touchedIndexWord 触摸到的索引
         */
        void onIndexChanged(String touchedIndexWord);
    }
}
