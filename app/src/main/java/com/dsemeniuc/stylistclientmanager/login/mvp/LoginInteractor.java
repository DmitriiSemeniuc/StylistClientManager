package com.dsemeniuc.stylistclientmanager.login.mvp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.dsemeniuc.stylistclientmanager.entity.Me;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.OptionalPendingResult;

import io.reactivex.Single;

public interface LoginInteractor {

    Intent loginWithGoogle();

    void verifyUserType(Context ctx, FragmentActivity activity,
                        OnVerifyUserTypeListener listener, LoginPresenter presenter);

    void silentSignInWithGoogle(final OptionalPendingResult<GoogleSignInResult> opr,
                                final LoginPresenter presenter);

    Single<Me> login();

    interface OnVerifyUserTypeListener {

        void onUserSavingSuccess();

        void onUserSavingFailed();
    }
}
