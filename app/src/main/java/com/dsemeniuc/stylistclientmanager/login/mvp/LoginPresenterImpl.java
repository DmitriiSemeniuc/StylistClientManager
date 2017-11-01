package com.dsemeniuc.stylistclientmanager.login.mvp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.dsemeniuc.stylistclientmanager.R;
import com.dsemeniuc.stylistclientmanager.base.AbstractPresenter;
import com.dsemeniuc.stylistclientmanager.base.Const;
import com.dsemeniuc.stylistclientmanager.main.MainActivity;
import com.google.android.gms.auth.api.Auth;

public class LoginPresenterImpl extends AbstractPresenter<LoginView> implements LoginPresenter, LoginInteractor.OnLoginListener {

    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView view, LoginInteractor interactor) {
        super(view);
        this.loginInteractor = interactor;
    }

    @Override
    public void loginWithGoogle() {
        loginInteractor.loginWithGoogle()
            .subscribe(intent -> view.startLoginIntent(intent, Const.Action.SIGN_IN));
    }

    @Override
    public void authenticate(Context context, FragmentActivity activity, boolean logged) {
        if(logged){
            auth(context, activity);
        } else {
            setGoogleApiClient(context, activity);
        }
    }

    @Override
    public void auth(Context context, FragmentActivity activity) {
        loginInteractor.auth(context, activity, this);
    }

    @Override
    public void setGoogleApiClient(Context context, FragmentActivity activity){
        loginInteractor.setGoogleApiClient(context, activity);
    }

    @Override
    public void goToMainScreen() {
        view.goToMainScreen();
    }

    @Override
    public void onShowProgressDialog() {
        view.showProgress();
    }

    @Override
    public void onHideProgressDialog() {
        view.hideProgress();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Const.Action.SIGN_IN) {
            loginInteractor.onLoginWithGoogle(Auth.GoogleSignInApi.getSignInResultFromIntent(data), this);
        }
    }

    @Override
    public void onLoginSuccess() {
        view.goToMainScreen();
    }

    @Override
    public void onLoginError() {
        view.onError(R.string.error_cannot_login);
    }

    @Override
    public void onNoInternetAccess() {
        view.onError(R.string.error_no_internet_connection);
    }
}
