package com.nisoft.popupwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.nisoft.androidlib.utils.ScreenFitUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText et_input;
    private ImageView iv_popup_window_icon;
    private PopupWindow mPopupWindow;

    private ArrayList<String> mMessageList = new ArrayList<>();
    private PopupWindowListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initListener();
    }

    private void initListener() {
        iv_popup_window_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow == null) {
                    mPopupWindow = new PopupWindow(MainActivity.this);
                    mPopupWindow.setWidth(et_input.getWidth());
                    mPopupWindow.setHeight(ScreenFitUtil.dpToPx(MainActivity.this, 200));
                    ListView listView = new ListView(MainActivity.this);
                    listView.setAdapter(mAdapter);
                    mPopupWindow.setContentView(listView);
                    mPopupWindow.showAsDropDown(et_input);
                } else {
                    mPopupWindow.dismiss();
                    mPopupWindow = null;
                }
            }
        });
    }


    private void initData() {
        mMessageList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mMessageList.add("--第 " + i + " 个用户--");
        }
        mAdapter = new PopupWindowListAdapter(this, mMessageList, new PopupWindowListAdapter.OnItemViewClickedListener() {
            @Override
            public void onMessageClicked(String message) {
                et_input.setText(message);
                mPopupWindow.dismiss();
                mPopupWindow = null;
            }

            @Override
            public void onDeleteClicked(String message) {
                mMessageList.remove(message);
            }
        });
    }

    private void initView() {
        et_input = (EditText) findViewById(R.id.et_input);
        iv_popup_window_icon = (ImageView) findViewById(R.id.iv_popup_window_icon);
    }
}
