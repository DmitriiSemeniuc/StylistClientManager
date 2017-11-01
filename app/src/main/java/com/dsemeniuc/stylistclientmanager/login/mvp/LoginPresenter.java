package com.dsemeniuc.stylistclientmanager.login.mvp;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.dsemeniuc.stylistclientmanager.base.BasePresenter;

public interface LoginPresenter extends BasePresenter {

    void loginWithGoogle();

    void authenticate(Context context, FragmentActivity activity, boolean logged);

    void auth(Context context, FragmentActivity activity);

    void setGoogleApiClient(Context context, FragmentActivity activity);

    void goToMainScreen();

    void onShowProgressDialog();

    void onHideProgressDialog();
}
