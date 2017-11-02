package com.nisoft.androidlib.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.nisoft.androidlib.R;

/**
 * Created by NewIdeaSoft on 2017/11/1.
 */

public class SwitchButton extends View {

    private Bitmap mBackgroundBitmap;
    private Bitmap mSlideBitmap;
    private int mMaxSlideDistance = 0;
    private boolean isOpen = false;
    private int mSlideBitmapPositionX;
    private boolean isClicked = false;
    private float eventStartX;
    private float lastMoveEventX;

    public SwitchButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mBackgroundBitmap.getWidth(),mBackgroundBitmap.getHeight());
    }

    private void initView() {
        mBackgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.switch_background);
        mSlideBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.slide_button);
        mMaxSlideDistance = mBackgroundBitmap.getWidth() - mSlideBitmap.getWidth();
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isClicked) {
                    isOpen = !isOpen;
                    flushView();
                }
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
                lastMoveEventX=eventStartX = event.getX();
                isClicked = true;
                break;
            case MotionEvent.ACTION_MOVE :
                float moveDistanceX = event.getX()- eventStartX;
                mSlideBitmapPositionX += moveDistanceX;
                if(mSlideBitmapPositionX>mMaxSlideDistance) {
                    mSlideBitmapPositionX = mMaxSlideDistance;
                }else if(mSlideBitmapPositionX<0) {
                    mSlideBitmapPositionX = 0;
                }
                invalidate();
                if(Math.abs(event.getX()-lastMoveEventX)>3) {
                    isClicked = false;
                }
                eventStartX = event.getX();
                break;
            case MotionEvent.ACTION_UP :
                if(!isClicked) {
                    if(mSlideBitmapPositionX>mMaxSlideDistance/2) {
                        isOpen = true;
                    }else {
                        isOpen = false;
                    }
                    flushView();
                }
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawBitmap(mBackgroundBitmap,0,0,paint);
        canvas.drawBitmap(mSlideBitmap, mSlideBitmapPositionX,0,paint);
    }

    private void flushView() {
        if(isOpen) {
            mSlideBitmapPositionX = mMaxSlideDistance;
        }else {
            mSlideBitmapPositionX = 0;
        }
        invalidate();
    }
}
