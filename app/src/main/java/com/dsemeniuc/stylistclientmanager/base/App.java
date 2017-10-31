package com.dsemeniuc.stylistclientmanager.base;

import android.app.Application;

import com.dsemeniuc.stylistclientmanager.di.AppComponent;
import com.dsemeniuc.stylistclientmanager.di.AppModule;
import com.dsemeniuc.stylistclientmanager.di.DaggerAppComponent;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = createAppComponent();
    }

    protected AppComponent createAppComponent() {
        return DaggerAppComponent.builder()
            .appModule(new AppModule(this))
            .build();
    }

    public AppComponent getComponent() {
        return appComponent;
    }
}
