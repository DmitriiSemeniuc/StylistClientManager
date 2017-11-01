package com.dsemeniuc.stylistclientmanager.main.mvp;

import com.dsemeniuc.stylistclientmanager.entity.AppUser;

public class MainInteractorImpl implements MainInteractor {

    private AppUser appUser;

    public MainInteractorImpl(AppUser appUser) {
        this.appUser = appUser;
    }
}
