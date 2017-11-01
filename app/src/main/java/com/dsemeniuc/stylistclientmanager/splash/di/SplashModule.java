package com.dsemeniuc.stylistclientmanager.splash.di;

import com.dsemeniuc.stylistclientmanager.auth.GoogleAuthenticator;
import com.dsemeniuc.stylistclientmanager.entity.AppUser;
import com.dsemeniuc.stylistclientmanager.splash.mvp.SplashInteractor;
import com.dsemeniuc.stylistclientmanager.splash.mvp.SplashInteractorImpl;
import com.dsemeniuc.stylistclientmanager.splash.mvp.SplashPresenter;
import com.dsemeniuc.stylistclientmanager.splash.mvp.SplashPresenterImpl;
import com.dsemeniuc.stylistclientmanager.splash.mvp.SplashView;
import com.dsemeniuc.stylistclientmanager.utils.SharedPreferencesWrapper;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashModule {

    private SplashView view;

    public SplashModule(SplashView view) {
        this.view = view;
    }

    @Provides
    @SplashScope
    SplashPresenter providesPresenter(SplashInteractor interactor){
        return new SplashPresenterImpl(view, interactor);
    }

    @Provides
    @SplashScope
    SplashInteractor providesInteractor(SharedPreferencesWrapper sp) {
        return new SplashInteractorImpl(sp);
    }
}
