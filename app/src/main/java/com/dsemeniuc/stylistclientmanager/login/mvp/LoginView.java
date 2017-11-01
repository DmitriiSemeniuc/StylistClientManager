package com.dsemeniuc.stylistclientmanager.login.mvp;

import android.content.Intent;

import com.dsemeniuc.stylistclientmanager.base.BaseView;

public interface LoginView extends BaseView {

    void startLoginIntent(Intent intent, int requestCode);

    void goToMainScreen();

    void showProgress();

    void hideProgress();
}
