package com.incognia.login;

import android.app.Application;
import android.content.res.Configuration;

import com.google.common.hash.Hashing;
import com.incognia.Incognia;
import com.incognia.login.database.Database;

import java.nio.charset.StandardCharsets;

public class MyCustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Incognia.init(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
    
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
