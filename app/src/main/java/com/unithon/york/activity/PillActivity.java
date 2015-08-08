package com.unithon.york.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.unithon.york.R;
import com.unithon.york.activity.contact.ContactsListActivity;
import com.unithon.york.util.CConfig;

/**
 * Created by 현식 on 2015-08-08.
 */
public class PillActivity extends Activity implements View.OnClickListener {

    Context mContext;
    String keyword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill);
        mContext = this;

        BaseActivity.add(this);
        findViewById(R.id.pill1).setOnClickListener(this);
        findViewById(R.id.pill2).setOnClickListener(this);
        findViewById(R.id.pill3).setOnClickListener(this);
        findViewById(R.id.pill4).setOnClickListener(this);
        findViewById(R.id.pill5).setOnClickListener(this);
        findViewById(R.id.pill6).setOnClickListener(this);

        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        keyword = getIntent().getStringExtra(CConfig.LOCATION);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pill1:
                goIntent(keyword + "_CANCER");
                break;

            case R.id.pill2:
                goIntent(keyword + "_HYPER");
                break;

            case R.id.pill3:
                goIntent(keyword + "_RES");
                break;

            case R.id.pill4:
                goIntent(keyword + "_BREAK");
                break;

            case R.id.pill5:
                goIntent(keyword + "_HEART");
                break;

            case R.id.pill6:
                goIntent(keyword + "_VIBRIO");
                break;
        }
    }


    private void goIntent(String choose) {
        Intent intent = new Intent(mContext, ContactsListActivity.class);
        intent.putExtra(CConfig.PILL_TYPE, choose);
        startActivity(intent);
    }
}
