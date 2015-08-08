package com.unithon.york.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.unithon.york.MainActivity;
import com.unithon.york.R;

/**
 * Created by 현식 on 2015-08-09.
 */
public class SendDoneActivity extends Activity {

    Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);
        mContext = this;
        BaseActivity.add(this);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(mContext, MainActivity.class);
                intent.putExtra("isDone", true);
                startActivity(intent);
            }
        }, 3000);
    }
}
