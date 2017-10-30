package com.nisoft.youkumenu;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2017/10/30.
 */

public class AnimationTools {
    public static void hideView(View view,int delayTime){
        RotateAnimation ra = new RotateAnimation(0,180,view.getWidth()/2,view.getHeight());
        ra.setDuration(1000);
        ra.setFillAfter(true);
        ra.setStartOffset(delayTime);
        view.startAnimation(ra);
    }

    public static void showView(View view, int i) {
        RotateAnimation ra = new RotateAnimation(180,360,view.getWidth()/2,view.getHeight());
        ra.setDuration(1000);
        ra.setFillAfter(true);
        ra.setStartOffset(i);
        view.startAnimation(ra);
    }
}
