package com.nisoft.youkumenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private ImageView iv_home;
    private ImageView iv_menu;
    private RelativeLayout rl_level3;
    private RelativeLayout rl_level2;
    private RelativeLayout rl_level1;

    private boolean isLevel1Visible = true;
    private boolean isLevel2Visible = true;
    private boolean isLevel3Visible = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();

    }

    private void initListener() {
        iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLevel2Visible) {
                    isLevel2Visible =false;
                    ObjectAnimationTools.hideView(rl_level2,0);
                    if(isLevel3Visible) {
                        isLevel3Visible=false;
                        ObjectAnimationTools.hideView(rl_level3,200);
                    }
                }else {
                    isLevel2Visible =true;
                    ObjectAnimationTools.showView(rl_level2,0);
                }
            }
        });
        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLevel3Visible) {
                    isLevel3Visible = false;
                    ObjectAnimationTools.hideView(rl_level3,0);
                }else{
                    isLevel3Visible = true;
                    ObjectAnimationTools.showView(rl_level3,0);
                }
            }
        });
    }

    private void initView(){
        iv_home = (ImageView)findViewById(R.id.iv_home);
        iv_menu = (ImageView)findViewById(R.id.iv_menu);
        rl_level3 = (RelativeLayout)findViewById(R.id.rl_level3);
        rl_level2 = (RelativeLayout)findViewById(R.id.rl_level2);
        rl_level1 = (RelativeLayout)findViewById(R.id.rl_level1);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_MENU&&event.getRepeatCount()==0) {
            if(isLevel1Visible) {
                isLevel1Visible = false;
                ObjectAnimationTools.hideView(rl_level1,0);
                if(isLevel2Visible) {
                    isLevel2Visible = false;
                    ObjectAnimationTools.hideView(rl_level2,200);
                    if(isLevel3Visible) {
                        isLevel3Visible = false;
                        ObjectAnimationTools.hideView(rl_level3,200);
                    }
                }
            }else{
                isLevel1Visible = true;
                ObjectAnimationTools.showView(rl_level1,0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
