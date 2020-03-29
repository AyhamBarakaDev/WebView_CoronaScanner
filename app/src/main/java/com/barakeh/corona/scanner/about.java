package com.barakeh.corona.scanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;

import com.google.android.gms.common.Feature;

public class about extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        supportRequestWindowFeature (Window.FEATURE_NO_TITLE);
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_about);
    }
}