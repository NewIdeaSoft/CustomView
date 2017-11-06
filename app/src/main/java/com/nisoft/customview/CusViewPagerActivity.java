package com.nisoft.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.nisoft.androidlib.viewgroup.CusViewPager;

public class CusViewPagerActivity extends AppCompatActivity {
    private CusViewPager cus_view_pager;
    private static final int[] PHOTO_ID_S = {R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4,R.drawable.a5,R.drawable.a6};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_view_pager);
        initView();
    }

    private void initView() {
        cus_view_pager = (CusViewPager)findViewById(R.id.cus_view_pager);
        for (int i = 0;i<PHOTO_ID_S.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(PHOTO_ID_S[i]);
            cus_view_pager.addView(imageView);
        }
        View view  = View.inflate(this,R.layout.item_view_pager_test,null);
        cus_view_pager.addView(view,2);
    }
}
