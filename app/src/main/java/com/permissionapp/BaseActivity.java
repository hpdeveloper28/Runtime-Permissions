package com.permissionapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by hiren.patel on 08-12-2016.
 */
public class BaseActivity extends AppCompatActivity {

    private BaseActivity baseActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseActivity = this;
    }

}
