package com.sebix.dagger.ui.main;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.sebix.dagger.BaseActivity;
import com.sebix.dagger.R;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
