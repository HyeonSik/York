package com.unithon.york.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.unithon.york.R;

/**
 * Created by 현식 on 2015-08-08.
 */
public class SplashActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2000);
    }
}
