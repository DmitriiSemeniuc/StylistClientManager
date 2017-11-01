package com.dsemeniuc.stylistclientmanager.login.di;

import com.dsemeniuc.stylistclientmanager.login.LoginActivity;
import com.dsemeniuc.stylistclientmanager.di.AppComponent;

import dagger.Component;

@LoginScope
@Component(dependencies = AppComponent.class, modules = {LoginModule.class})
public interface LoginComponent {
    void inject(LoginActivity activity);
}
