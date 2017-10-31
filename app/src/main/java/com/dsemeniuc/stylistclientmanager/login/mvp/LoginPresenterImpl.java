package com.dsemeniuc.stylistclientmanager.login.mvp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.dsemeniuc.stylistclientmanager.base.AbstractPresenter;
import com.dsemeniuc.stylistclientmanager.base.Const;

public class LoginPresenterImpl extends AbstractPresenter<LoginView> implements LoginPresenter {

    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView view, LoginInteractor interactor) {
        super(view);
        this.view = view;
        this.loginInteractor = interactor;
    }

    @Override
    public void loginWithGoogle() {
        Intent intent = loginInteractor.loginWithGoogle();
        view.startLoginIntent(intent, Const.Action.SIGN_IN);
    }

    @Override
    public void verifyUserType(Context context, FragmentActivity activity) {
        //loginInteractor.verifyUserType(context, activity, this, this);
    }
}
