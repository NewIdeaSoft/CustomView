package com.nisoft.customview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_main_switch;
    private Button btn_main_cus_property;
    private Button btn_main_cus_viewpager;
    private Button btn_main_cus_contacts_index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        btn_main_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SwitchButtonActivity.class);
                startActivity(intent);
            }
        });
        btn_main_cus_property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CusPropertyActivity.class);
                startActivity(intent);
            }
        });
        btn_main_cus_viewpager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CusViewPagerActivity.class);
                startActivity(intent);
            }
        });
        btn_main_cus_contacts_index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ContactsIndexViewActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        btn_main_switch = (Button)findViewById(R.id.btn_main_switch);
        btn_main_cus_property = (Button)findViewById(R.id.btn_main_cus_property);
        btn_main_cus_viewpager = (Button)findViewById(R.id.btn_main_cus_viewpager);
        btn_main_cus_contacts_index = (Button)findViewById(R.id.btn_main_cus_contacts_index);
    }
}
