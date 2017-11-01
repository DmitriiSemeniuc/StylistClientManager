package com.dsemeniuc.stylistclientmanager.splash.mvp;

import android.content.Intent;

import com.dsemeniuc.stylistclientmanager.base.AbstractPresenter;

public class SplashPresenterImpl extends AbstractPresenter<SplashView> implements SplashPresenter {

    private SplashInteractor interactor;

    public SplashPresenterImpl(SplashView view, SplashInteractor interactor) {
        super(view);
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        // wait for 3 seconds
    }

    @Override
    public void onResume() {
        verifyIsUserLoggedIn();
    }

    @Override
    public void onStop() {
        // dismiss dialogs.
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, boolean isGranted) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void verifyIsUserLoggedIn() {
        if(interactor.isUserLogged()){
            view.onUserLogged();
        } else {
            view.onUserNotLogged();
        }
    }
}
