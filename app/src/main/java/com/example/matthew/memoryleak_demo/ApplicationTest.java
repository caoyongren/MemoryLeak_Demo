package com.example.matthew.memoryleak_demo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by matthew on 17-6-22.
 */

public class ApplicationTest extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
