package com.nisoft.youkumenu;

import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by Administrator on 2017/10/31.
 */

public class ObjectAnimationTools {
    public static void hideView(View view,int delayTime){
        ObjectAnimator oa = ObjectAnimator.ofFloat(view,"rotation",0,180);
        oa.setDuration(500);
        oa.setStartDelay(delayTime);
        oa.start();
        view.setPivotX(view.getWidth()/2);
        view.setPivotY(view.getHeight());
    }
    public static void showView(View view,int delayTime){
        ObjectAnimator oa = ObjectAnimator.ofFloat(view,"rotation",180,360);
        oa.setDuration(500);
        oa.setStartDelay(delayTime);
        oa.start();
        view.setPivotX(view.getWidth()/2);
        view.setPivotY(view.getHeight());
    }
}
