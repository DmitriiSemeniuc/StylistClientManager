package com.dsemeniuc.stylistclientmanager.main.mvp;

import android.content.Intent;

import com.dsemeniuc.stylistclientmanager.base.AbstractPresenter;

public class MainPresenterImpl extends AbstractPresenter<MainView> implements MainPresenter {

    private MainInteractor interactor;

    public MainPresenterImpl(MainView view, MainInteractor interactor) {
        super(view);
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, boolean isGranted) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
