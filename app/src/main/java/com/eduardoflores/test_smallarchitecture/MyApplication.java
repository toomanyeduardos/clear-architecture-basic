package com.eduardoflores.test_smallarchitecture;

import android.app.Application;

/**
 * Created by eflores on 11/10/16.
 */

public class MyApplication extends Application {

    private ServiceLocator serviceLocator;

    @Override
    public void onCreate() {
        super.onCreate();

        serviceLocator = new ServiceLocator(this, "rectangles-data.json");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        ServiceLocator.getInstance().destroyComponent();
    }
}
