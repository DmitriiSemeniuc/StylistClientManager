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
import com.dsemeniuc.stylistclientmanager.utils.Utils;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private App app;
    private FirebaseAuth firebaseAuth;

    public AppModule(App app) {
        this.app = app;
        FirebaseApp.initializeApp(app.getApplicationContext());
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
    @Singleton
    Context providesContext(){
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    SharedPreferencesWrapper providesSharedPreferencesWrapper(Context context) {
        return new SharedPreferencesWrapper(context);
    }

    @Provides
    @Singleton
    Utils providesUtils(Context context){
        return new Utils(context);
    }
}
