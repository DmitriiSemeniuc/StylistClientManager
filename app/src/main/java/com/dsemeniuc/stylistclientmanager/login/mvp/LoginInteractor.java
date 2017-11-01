package com.dsemeniuc.stylistclientmanager.login.mvp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.dsemeniuc.stylistclientmanager.entity.Me;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.OptionalPendingResult;

import io.reactivex.Single;

public interface LoginInteractor {

    Single<Intent> loginWithGoogle();

    void onLoginWithGoogle(GoogleSignInResult result, OnLoginListener listener);

    void silentSignInWithGoogle(final OptionalPendingResult<GoogleSignInResult> opr,
                                final LoginPresenter presenter);

    void auth(Context context, FragmentActivity activity, LoginPresenter presenter);

    void setGoogleApiClient(Context context, FragmentActivity activity);

    Single<Me> login();

    interface OnLoginListener{

        void onLoginSuccess();

        void onLoginError();

        void onNoInternetAccess();
    }
}
