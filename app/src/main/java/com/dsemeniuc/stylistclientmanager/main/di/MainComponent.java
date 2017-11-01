package com.dsemeniuc.stylistclientmanager.main.di;

import com.dsemeniuc.stylistclientmanager.di.AppComponent;
import com.dsemeniuc.stylistclientmanager.login.LoginActivity;
import com.dsemeniuc.stylistclientmanager.login.di.LoginModule;
import com.dsemeniuc.stylistclientmanager.main.MainActivity;

import dagger.Component;

@MainScope
@Component(dependencies = AppComponent.class, modules = {MainModule.class})
public interface MainComponent {
    void inject(MainActivity activity);
}
