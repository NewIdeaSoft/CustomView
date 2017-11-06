package com.nisoft.customview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.nisoft.androidlib.view.ContactsIndexView;

public class ContactsIndexViewActivity extends AppCompatActivity {
    private ContactsIndexView civ_index;
    private ListView lv_contacts_index;
    private TextView tv_contacts_index_word;
    private Handler mHandler = new Handler();
    private String[] mContacts = {"老鼠","牛","老虎","兔子","龙","蛇","马","羊","候","鸡","狗","猪"};
    private ContactsListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_index_view);
        initData();
        initView();
        initListener();
    }

    private void initListener() {
        civ_index.setOnIndexChangedListener(new ContactsIndexView.OnIndexChangedListener() {
            @Override
            public void onIndexChanged(String touchedIndexWord) {
                updateIndexWord(touchedIndexWord);
            }
        });
        lv_contacts_index.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    private void updateIndexWord(String touchedIndexWord) {
        tv_contacts_index_word.setVisibility(View.VISIBLE);
        tv_contacts_index_word.setText(touchedIndexWord);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_contacts_index_word.setVisibility(View.GONE);
            }
        },3000);
    }

    private void initView() {
        civ_index = (ContactsIndexView)findViewById(R.id.civ_index);
        lv_contacts_index = (ListView)findViewById(R.id.lv_contacts_index);
        lv_contacts_index.setAdapter(mAdapter);
        tv_contacts_index_word = (TextView)findViewById(R.id.tv_contacts_index_word);
        tv_contacts_index_word.setVisibility(View.GONE);
    }

    private void initData() {
        mAdapter = new ContactsListAdapter();
    }

    private class ContactsListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mContacts.length;
        }

        @Override
        public Object getItem(int position) {
            return mContacts[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null) {
                convertView = View.inflate(ContactsIndexViewActivity.this,R.layout.item_contacts,null);
            }
            TextView tv_item_contacts_index = (TextView) convertView.findViewById(R.id.tv_item_contacts_index);
            TextView tv_item_contacts_name = (TextView) convertView.findViewById(R.id.tv_item_contacts_name);

            tv_item_contacts_name.setText(mContacts[position]);
            return convertView;
        }
    }
}
