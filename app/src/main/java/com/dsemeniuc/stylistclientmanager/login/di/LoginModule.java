package com.dsemeniuc.stylistclientmanager.login.di;

import com.dsemeniuc.stylistclientmanager.auth.GoogleAuthenticator;
import com.dsemeniuc.stylistclientmanager.base.Api;
import com.dsemeniuc.stylistclientmanager.entity.AppUser;
import com.dsemeniuc.stylistclientmanager.login.mvp.LoginInteractor;
import com.dsemeniuc.stylistclientmanager.login.mvp.LoginInteractorImpl;
import com.dsemeniuc.stylistclientmanager.login.mvp.LoginPresenter;
import com.dsemeniuc.stylistclientmanager.login.mvp.LoginPresenterImpl;
import com.dsemeniuc.stylistclientmanager.login.mvp.LoginView;
import com.dsemeniuc.stylistclientmanager.utils.SharedPreferencesWrapper;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    private LoginView view;

    public LoginModule(LoginView view) {
        this.view = view;
    }

    @Provides
    @LoginScope
    LoginPresenter providesPresenter(LoginInteractor interactor) {
        return new LoginPresenterImpl(view, interactor);
    }

    @Provides
    @LoginScope
    LoginInteractor providesInteractor(Api api, AppUser appUser, GoogleAuthenticator authenticator, SharedPreferencesWrapper sp) {
        return new LoginInteractorImpl(api, appUser, authenticator, sp);
    }
}
