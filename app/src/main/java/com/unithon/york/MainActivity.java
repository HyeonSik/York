package com.unithon.york;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.unithon.york.activity.BaseActivity;
import com.unithon.york.activity.PillActivity;
import com.unithon.york.activity.SplashActivity;
import com.unithon.york.util.CConfig;

/**
 * Created by 현식 on 2015-08-08.
 */  // 여기가 지도가 나오는곳
public class MainActivity extends Activity implements View.OnClickListener {

    Context mContext;
    boolean isDone = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        BaseActivity.add(this);
        isDone = getIntent().getBooleanExtra("isDone", false);

        if (!isDone)
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
                Toast.makeText(mContext, "솔직히 이 정돈 직접 할 수 있잖아?", Toast.LENGTH_SHORT).show();
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
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private long backKeyPressedTime = 0;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            super.onBackPressed();
            BaseActivity.finishedAllActivity();
            finish();
        } else {
            backKeyPressedTime = System.currentTimeMillis();
            Toast.makeText(mContext, "뒤로 버튼을 한 번 더 누르면 앱이 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
