package com.nisoft.androidlib.utils;

import android.content.Context;

/**
 * Created by Administrator on 2017/11/1.
 */

public class ScreenFitUtil {
    public static int dpToPx(Context context,float dp){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dp*scale+0.5f);
    }
    public static int pxTodp(Context context,float px){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px/scale+05f);
    }
}
