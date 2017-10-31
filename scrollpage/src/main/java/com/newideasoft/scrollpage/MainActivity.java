package com.newideasoft.scrollpage;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager vp_scroll_page;
    private TextView tv_scroll_page_title;
    private LinearLayout ll_scroll_page_points;

    private ScrollPageAdapter mAdapter;
    private ArrayList<PageData> mPageDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initListener();
    }

    private void initListener() {
        vp_scroll_page.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initData() {
        int[] srcs = {};
        String [] titles = {};
        mPageDatas = DataPrepareTools.prepareData(srcs,titles);
        mAdapter = new ScrollPageAdapter(this, mPageDatas, new ScrollPageAdapter.ImageEventListener() {
            @Override
            public void onImageTouch() {

            }

            @Override
            public void onImageClick() {

            }
        });
    }

    private void initView() {
        vp_scroll_page = (ViewPager)findViewById(R.id.vp_scroll_page);
        tv_scroll_page_title = (TextView)findViewById(R.id.tv_scroll_page_title);
        ll_scroll_page_points = (LinearLayout)findViewById(R.id.ll_scroll_page_points);
        vp_scroll_page.setAdapter(mAdapter);
    }

}
