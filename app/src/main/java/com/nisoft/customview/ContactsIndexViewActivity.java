package com.nisoft.customview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.nisoft.androidlib.view.ContactsIndexView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ContactsIndexViewActivity extends AppCompatActivity {
    private ContactsIndexView civ_index;
    private ListView lv_contacts_index;
    private TextView tv_contacts_index_word;
    private Handler mHandler = new Handler();
    private String[] mContacts = {"老鼠", "牛", "老虎", "兔子", "龙", "蛇", "马", "羊", "候", "鸡", "狗", "猪"};
    private ContactsListAdapter mAdapter;
    private ArrayList<Contact> mContactList;

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
                updateListView(touchedIndexWord);
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

    private void updateListView(String touchedIndexWord) {
        for (int i = 0; i < mContactList.size(); i++) {
            if (mContactList.get(i).getNameENG().substring(0, 1).equals(touchedIndexWord)) {
                lv_contacts_index.setSelection(i);
                return;
            }
        }
    }

    private void updateIndexWord(String touchedIndexWord) {
        tv_contacts_index_word.setVisibility(View.VISIBLE);
        tv_contacts_index_word.setText(touchedIndexWord);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_contacts_index_word.setVisibility(View.GONE);
            }
        }, 3000);
    }

    private void initView() {
        civ_index = (ContactsIndexView) findViewById(R.id.civ_index);
        lv_contacts_index = (ListView) findViewById(R.id.lv_contacts_index);
        lv_contacts_index.setAdapter(mAdapter);
        tv_contacts_index_word = (TextView) findViewById(R.id.tv_contacts_index_word);
        tv_contacts_index_word.setVisibility(View.GONE);
    }

    private void initData() {
        mContactList = new ArrayList<>();
        for (String name : mContacts) {
            mContactList.add(new Contact(name));
        }
        Collections.sort(mContactList, new Comparator<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                return o1.getNameENG().compareTo(o2.getNameENG());
            }
        });
        mAdapter = new ContactsListAdapter();
    }

    private class ContactsListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mContactList.size();
        }

        @Override
        public Object getItem(int position) {
            return mContactList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(ContactsIndexViewActivity.this, R.layout.item_contacts, null);
            }
            TextView tv_item_contacts_index = (TextView) convertView.findViewById(R.id.tv_item_contacts_index);
            TextView tv_item_contacts_name = (TextView) convertView.findViewById(R.id.tv_item_contacts_name);

            tv_item_contacts_name.setText(mContactList.get(position).getNameCHI());
            String indexWord = mContactList.get(position).getNameENG().substring(0, 1);
            if (position == 0) {
                tv_item_contacts_index.setText(indexWord);
                tv_item_contacts_index.setVisibility(View.VISIBLE);
            } else {
                String lastIndexWord = mContactList.get(position - 1).getNameENG().substring(0, 1);
                if (lastIndexWord.equals(indexWord)) {
                    tv_item_contacts_index.setVisibility(View.GONE);
                } else {
                    tv_item_contacts_index.setVisibility(View.VISIBLE);
                    tv_item_contacts_index.setText(indexWord);
                }
            }
            return convertView;
        }
    }
}
