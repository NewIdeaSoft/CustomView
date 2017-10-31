package com.nisoft.youkumenu;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2017/10/30.
 */

public class AnimationTools {
    public static void hideView(ViewGroup view, int delayTime){
        RotateAnimation ra = new RotateAnimation(0,180,view.getWidth()/2,view.getHeight());
        ra.setDuration(1000);
        ra.setFillAfter(true);
        ra.setStartOffset(delayTime);
        view.startAnimation(ra);
        int count = view.getChildCount();
        for (int i = 0; i < count; i++) {
            View v = view.getChildAt(i);
            v.setEnabled(false);
        }

    }

    public static void showView(ViewGroup view, int delayTime) {
        RotateAnimation ra = new RotateAnimation(180,360,view.getWidth()/2,view.getHeight());
        ra.setDuration(1000);
        ra.setFillAfter(true);
        ra.setStartOffset(delayTime);
        view.startAnimation(ra);
        int count = view.getChildCount();
        for (int i = 0; i < count; i++) {
            View v = view.getChildAt(i);
            v.setEnabled(true);
        }
    }
}
