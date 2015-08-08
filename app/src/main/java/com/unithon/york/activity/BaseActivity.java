package com.unithon.york.activity;

import java.util.ArrayList;

import android.app.Activity;

/**
 * Created by 현식 on 2015-08-09.
 */
public class BaseActivity {

    public static ArrayList<Activity> actList = new ArrayList<Activity>();

    public static void add(Activity activity) {
        if (actList == null) {
            actList = new ArrayList<Activity>();
        }
        actList.add(activity);
    }

    public static void finishedAllActivity() {
        if (actList == null) {

        } else {
            for (Activity activity : actList) {
                activity.finish();
            }
            actList.clear();
            actList = null;
        }
    }
}
