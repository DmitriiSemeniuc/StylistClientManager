package com.dsemeniuc.stylistclientmanager.splash.di;

import com.dsemeniuc.stylistclientmanager.di.AppComponent;
import com.dsemeniuc.stylistclientmanager.splash.SplashActivity;

import dagger.Component;

@SplashScope
@Component(dependencies = AppComponent.class, modules = {SplashModule.class})
public interface SplashComponent {
    void inject(SplashActivity activity);
}
