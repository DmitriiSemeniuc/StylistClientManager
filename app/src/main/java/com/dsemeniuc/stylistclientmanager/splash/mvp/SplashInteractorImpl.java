package com.dsemeniuc.stylistclientmanager.splash.mvp;

import com.dsemeniuc.stylistclientmanager.auth.GoogleAuthenticator;
import com.dsemeniuc.stylistclientmanager.entity.AppUser;
import com.dsemeniuc.stylistclientmanager.utils.SharedPreferencesWrapper;

public class SplashInteractorImpl implements SplashInteractor {

    private SharedPreferencesWrapper sp;

    public SplashInteractorImpl(SharedPreferencesWrapper sp) {
        this.sp = sp;
    }

    @Override
    public boolean isUserLogged() {
        return sp.isUserLogged();
    }
}
