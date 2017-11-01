package com.newideasoft.scrollpage;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int MESSAGE_WHAT_AUTO_SCROLL = 0;
    private static final String TAG = MainActivity.class.getSimpleName();
    private ViewPager vp_scroll_page;
    private TextView tv_scroll_page_title;
    private LinearLayout ll_scroll_page_points;

    private ScrollPageAdapter mAdapter;
    private ArrayList<PageData> mPageDatas;
    private int prePositon =0;

    private int mScrollIntervals = 2000;
    private boolean isDragging = false;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            vp_scroll_page.setCurrentItem(vp_scroll_page.getCurrentItem()+1);
            sendEmptyMessageDelayed(MESSAGE_WHAT_AUTO_SCROLL,2000);
        }
    };
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
                int realPosition = position%mPageDatas.size();
                tv_scroll_page_title.setText(mPageDatas.get(realPosition).getImageTitle());
                ll_scroll_page_points.getChildAt(prePositon).setEnabled(false);
                ll_scroll_page_points.getChildAt(realPosition).setEnabled(true);
                prePositon = realPosition;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state){
                    case ViewPager.SCROLL_STATE_DRAGGING://拖拽
//                        Log.e(TAG, "SCROLL_STATE_DRAGGING");
                        isDragging = true;
                        mHandler.removeCallbacksAndMessages(null);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE://静止
//                        Log.e(TAG, "SCROLL_STATE_IDLE");
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING://滚动
//                        Log.e(TAG, "SCROLL_STATE_SETTLING");
                        if(isDragging) {//由拖拽状态进入滚动
                            mHandler.removeCallbacksAndMessages(null);
                            mHandler.sendEmptyMessageDelayed(MESSAGE_WHAT_AUTO_SCROLL,2000);
                            isDragging = false;
                        }
                        break;
                }
            }
        });

    }

    private void initData() {
        int[] srcs = {
                R.drawable.a,
                R.drawable.b,
                R.drawable.c,
                R.drawable.d,
                R.drawable.e };

        final String [] titles = {
                "巩俐不低俗，我就不能低俗",
                "扑树又回来啦！再唱经典老歌引万人大合唱",
                "揭秘北京电影如何升级",
                "乐视网TV版大派送",
                "热血屌丝的反杀" };
        mPageDatas = DataPrepareTools.prepareData(srcs,titles);
        mAdapter = new ScrollPageAdapter(this, mPageDatas, new ScrollPageAdapter.ImageEventListener() {
            @Override
            public boolean onImageTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        mHandler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_UP:
                        mHandler.sendEmptyMessageDelayed(MESSAGE_WHAT_AUTO_SCROLL,mScrollIntervals);
                        break;
                }
                return false;
            }

            @Override
            public void onImageClick(int position) {
                Toast.makeText(MainActivity.this, mPageDatas.get(position).getImageTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        vp_scroll_page = (ViewPager)findViewById(R.id.vp_scroll_page);
        tv_scroll_page_title = (TextView)findViewById(R.id.tv_scroll_page_title);
        ll_scroll_page_points = (LinearLayout)findViewById(R.id.ll_scroll_page_points);
        vp_scroll_page.setAdapter(mAdapter);
        initPoint();
        vp_scroll_page.setCurrentItem(Integer.MAX_VALUE/2-Integer.MAX_VALUE/2%mPageDatas.size());
        tv_scroll_page_title.setText(mPageDatas.get(prePositon).getImageTitle());
        mHandler.sendEmptyMessageDelayed(MESSAGE_WHAT_AUTO_SCROLL,mScrollIntervals);
    }

    private void initPoint() {
        for (int i = 0;i<mPageDatas.size();i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.shape_point);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(8,8);
            if(i==0) {
                imageView.setEnabled(true);
            }else {
                imageView.setEnabled(false);
                params.leftMargin = 8;
            }
            imageView.setLayoutParams(params);
            ll_scroll_page_points.addView(imageView);
        }
    }
}
