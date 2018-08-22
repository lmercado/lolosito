package com.ngti.leandro.lol;

import android.app.Application;

import timber.log.Timber;

public class LolApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
