package com.dsemeniuc.stylistclientmanager.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.dsemeniuc.stylistclientmanager.auth.GoogleAuthenticator;
import com.dsemeniuc.stylistclientmanager.base.Api;
import com.dsemeniuc.stylistclientmanager.base.App;
import com.dsemeniuc.stylistclientmanager.base.Const;
import com.dsemeniuc.stylistclientmanager.base.FirebaseApi;
import com.dsemeniuc.stylistclientmanager.entity.AppUser;
import com.dsemeniuc.stylistclientmanager.utils.SharedPreferencesWrapper;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    SharedPreferences providesPreferences() {
        return app.getSharedPreferences(Const.SP_NAME, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    App providesApplication() {
        return app;
    }

    @Provides
    @Singleton
    AppUser providesAppUser() {
        return new AppUser();
    }

    @Provides
    @Singleton
    Api providesApi(){
        return new FirebaseApi();
    }

    @Provides
    @Singleton
    GoogleAuthenticator providesGoogleAuthenticator(){
        return new GoogleAuthenticator();
    }

    @Provides
    @Singleton SharedPreferencesWrapper providesSharedPreferencesWrapper(Context context){
        return new SharedPreferencesWrapper(context);
    }
}
