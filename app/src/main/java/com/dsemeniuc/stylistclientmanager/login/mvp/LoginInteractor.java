package com.dsemeniuc.stylistclientmanager.login.mvp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.OptionalPendingResult;

import io.reactivex.Single;

public interface LoginInteractor {

    Single<Intent> loginWithGoogle();

    void onLoginWithGoogle(GoogleSignInResult result, OnLoginListener listener);

    void silentSignInWithGoogle(final OptionalPendingResult<GoogleSignInResult> opr, OnLoginListener presenter);

    void auth(Context context, FragmentActivity activity, OnLoginListener listener);

    void setGoogleApiClient(Context context, FragmentActivity activity);

    void firebaseAuthWithGoogle(GoogleSignInAccount acct, OnLoginListener listener);

    interface OnLoginListener{

        void onLoginSuccess();

        void onLoginError();

        void onNoInternetAccess();
    }
}
