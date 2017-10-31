package com.dsemeniuc.stylistclientmanager.login.mvp;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.dsemeniuc.stylistclientmanager.base.BasePresenter;

public interface LoginPresenter extends BasePresenter {

    void loginWithGoogle();

    void verifyUserType(Context context, FragmentActivity activity);
}
