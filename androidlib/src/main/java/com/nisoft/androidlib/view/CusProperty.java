package com.nisoft.androidlib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/11/3.
 */

public class CusProperty extends View {
    private int mCusAge;
    private String mCusName;
    private Bitmap mCusBackground;
    public CusProperty(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if(attrs!=null) {
            for(int i=0;i<attrs.getAttributeCount();i++){
                switch (attrs.getAttributeName(i)){
                    case "cus_age":
                        mCusAge = attrs.getAttributeIntValue(i,0);
                        break;
                    case "cus_name":
                        mCusName = attrs.getAttributeValue(i);
                        break;
                    case "cus_background":
                        int resourceValue = attrs.getAttributeResourceValue(i, 0);
                        mCusBackground = BitmapFactory.decodeResource(getResources(),resourceValue);
                        break;
                }
            }
        }


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        canvas.drawText(mCusName+mCusAge,100,100,paint);
        canvas.drawBitmap(mCusBackground,200,200,paint);
    }
}
