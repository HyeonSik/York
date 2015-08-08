package com.unithon.york;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.unithon.york.activity.PillActivity;
import com.unithon.york.activity.SplashActivity;
import com.unithon.york.activity.contact.ContactsListActivity;
import com.unithon.york.util.CConfig;

/**
 * Created by 현식 on 2015-08-08.
 */  // 여기가 지도가 나오는곳
public class MainActivity extends Activity implements View.OnClickListener {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        startActivity(new Intent(mContext, SplashActivity.class));


        findViewById(R.id.map1).setOnClickListener(this);
        findViewById(R.id.map2).setOnClickListener(this);
        findViewById(R.id.map3).setOnClickListener(this);
        findViewById(R.id.map4).setOnClickListener(this);
        findViewById(R.id.map5).setOnClickListener(this);
        findViewById(R.id.map6).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.map1:
                Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
                break;

            case R.id.map2:
                goIntent("KANGWON");
                break;

            case R.id.map3:
                goIntent("CHUNGCUNG");
                break;

            case R.id.map4:
                goIntent("KYUNGSANG");
                break;

            case R.id.map5:
                goIntent("JUNLLA");
                break;

            case R.id.map6:
                goIntent("JEJU");
                break;
        }
    }


    private void goIntent(String choose) {
        Intent intent = new Intent(mContext, PillActivity.class);
        intent.putExtra(CConfig.LOCATION, choose);
        startActivity(intent);
    }
}
