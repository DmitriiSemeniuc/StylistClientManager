package com.dsemeniuc.stylistclientmanager.di;

import com.dsemeniuc.stylistclientmanager.auth.GoogleAuthenticator;
import com.dsemeniuc.stylistclientmanager.base.Api;
import com.dsemeniuc.stylistclientmanager.base.App;
import com.dsemeniuc.stylistclientmanager.entity.AppUser;
import com.dsemeniuc.stylistclientmanager.utils.SharedPreferencesWrapper;
import com.dsemeniuc.stylistclientmanager.utils.Utils;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    Api getApi();

    App getApp();

    AppUser getAppUser();

    GoogleAuthenticator getGoogleAuthenticator();

    SharedPreferencesWrapper getSharedPreferencesWrapper();

    Utils getUtils();
}
