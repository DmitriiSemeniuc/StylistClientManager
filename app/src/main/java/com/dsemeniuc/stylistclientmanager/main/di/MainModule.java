package com.dsemeniuc.stylistclientmanager.main.di;

import com.dsemeniuc.stylistclientmanager.entity.AppUser;
import com.dsemeniuc.stylistclientmanager.main.mvp.MainInteractor;
import com.dsemeniuc.stylistclientmanager.main.mvp.MainInteractorImpl;
import com.dsemeniuc.stylistclientmanager.main.mvp.MainPresenter;
import com.dsemeniuc.stylistclientmanager.main.mvp.MainPresenterImpl;
import com.dsemeniuc.stylistclientmanager.main.mvp.MainView;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

    @Provides
    @MainScope
    MainPresenter providesPresenter(MainInteractor interactor){
        return new MainPresenterImpl(view, interactor);
    }

    @Provides
    @MainScope
    MainInteractor providesInteractor(AppUser appUser) {
        return new MainInteractorImpl(appUser);
    }
}
