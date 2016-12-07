package com;

import android.app.Application;

import com.hpdeveloper.Dexter;

/**
 * Created by hiren.patel on 07-12-2016.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * Init dexter permission lib
         */
        Dexter.initialize(this);
    }
}
