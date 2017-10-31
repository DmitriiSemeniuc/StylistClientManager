package com.dsemeniuc.stylistclientmanager.login.mvp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;

import com.dsemeniuc.stylistclientmanager.auth.GoogleAuthenticator;
import com.dsemeniuc.stylistclientmanager.base.Api;
import com.dsemeniuc.stylistclientmanager.base.Const;
import com.dsemeniuc.stylistclientmanager.entity.AppUser;
import com.dsemeniuc.stylistclientmanager.entity.Me;
import com.dsemeniuc.stylistclientmanager.utils.SharedPreferencesWrapper;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.OptionalPendingResult;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

public class LoginInteractorImpl implements LoginInteractor {

    private static final String TAG = LoginInteractorImpl.class.getSimpleName();
    private Api api;
    private AppUser appUser;
    private GoogleAuthenticator googleAuthenticator;
    private SharedPreferencesWrapper sp;
    private String email;

    public LoginInteractorImpl(Api api, AppUser appUser, GoogleAuthenticator authenticator, SharedPreferencesWrapper sp) {
        this.api = api;
        this.appUser = appUser;
        this.googleAuthenticator = authenticator;
        this.sp = sp;
    }

    @Override
    public Intent loginWithGoogle() {
         return Auth.GoogleSignInApi.getSignInIntent(googleAuthenticator.getApiClient());
    }

    @Override
    public void verifyUserType(Context ctx, FragmentActivity activity, OnVerifyUserTypeListener listener, LoginPresenter presenter) {
        String userType = sp.getUser();
        if (userType.equals(Const.User.GOOGLE)) {
            googleAuthenticator.setGoogleApiClient(ctx, activity);
            if(sp.isUserLogged())
                silentSignInWithGoogle(googleAuthenticator.getOptionalPendingResult(), presenter);
            return;
        }
        if (userType.equals(Const.User.REGISTERED)) {
            if (sp.isUserLogged()) {
                email = sp.getUserEmail();
                if (!TextUtils.isEmpty(email)) setGlobalUser(listener);
            }
            return;
        }
        if (userType.equals(Const.User.NEW))
            googleAuthenticator.setGoogleApiClient(ctx, activity);
    }

    /**
     * Set global user for silent sign in
     */
    private void setGlobalUser(OnVerifyUserTypeListener listener) {
        setGlobalUserObservable.subscribe(result -> {
            /*if (result == Const.Action.USER_SAVED) {
                User user = dbHelper.getUserByEmail(email);
                if (null != user) setEmailUserDetails(user);
                listener.onUserSavingSuccess();
            }
            if (result == Const.Action.USER_NOT_SAVED || result == Const.Action.NO_DB_RESULT)
                listener.onUserSavingFailed();*/
        }, throwable -> {
            Log.e(TAG, throwable.getMessage());
        });
    }

    private final Observable<Integer> setGlobalUserObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
        @Override
        public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
            //e.onNext(dbHelper.setGlobalUserWithEmail(email));
            e.onComplete();
        }
    })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());

    @Override
    public void silentSignInWithGoogle(final OptionalPendingResult<GoogleSignInResult> opr,
                                       final LoginPresenter presenter) {
        if (opr.isDone()) {
            GoogleSignInResult result = opr.get();
            handleGoogleSignInResult(result, presenter);
        } else {
            //presenter.onShowProgressDialog();
            opr.setResultCallback(googleSignInResult -> {
                //presenter.onHideProgressDialog();
                handleGoogleSignInResult(googleSignInResult, presenter);
            });
        }
    }

    /**
     * Handle Sign In result.
     * If result success -> go to Sign In Activity and finish this
     */
    private void handleGoogleSignInResult(GoogleSignInResult result,
                                          final LoginPresenter presenter) {
        if (!result.isSuccess()) return;
        GoogleSignInAccount account = result.getSignInAccount();
        /*if (account == null || dbHelper == null) return;
        User user = dbHelper.getUserByEmail(account.getEmail());
        Uri photoUrl = account.getPhotoUrl();
        if (null != user) {
            setEmailUserDetails(user);
            if (null != photoUrl) this.user.setPhotoUrl(photoUrl.toString());
            presenter.onUpdateUI();
        }*/
    }

    @Override
    public Single<Me> login() {
        return null;
    }
}
