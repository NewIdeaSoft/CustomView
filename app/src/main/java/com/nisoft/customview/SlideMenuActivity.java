package com.nisoft.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nisoft.androidlib.slidemenu.SlideMenuLayout;

import java.util.ArrayList;


public class SlideMenuActivity extends AppCompatActivity {
    private ListView lv_main;
    private SlideMenuAdapter mAdapter;
    private ArrayList<String> mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_menu);
        lv_main = (ListView)findViewById(R.id.lv_main);
        mAdapter = new SlideMenuAdapter();
        mData = new ArrayList<>();
        for (int i = 0;i<100;i++){
            mData.add("Content"+i);
        }
        lv_main.setAdapter(mAdapter);
    }
    private class SlideMenuAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null) {
                holder = new ViewHolder();
                convertView = View.inflate(SlideMenuActivity.this,R.layout.item_slide_menu,null);
                holder.contentView = (TextView) convertView.findViewById(R.id.tv_item_content);
                holder.menuView = (TextView) convertView.findViewById(R.id.tv_item_menu);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }
            SlideMenuLayout menuItem = (SlideMenuLayout) convertView;
            holder.contentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SlideMenuActivity.this, mData.get(position), Toast.LENGTH_SHORT).show();
                }
            });
            holder.menuView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mData.remove(position);
                    notifyDataSetChanged();
                }
            });
            String content = mData.get(position);
            holder.contentView.setText(content);
            return convertView;
        }
        private class ViewHolder{
            TextView contentView;
            TextView menuView;
        }
    }
}
