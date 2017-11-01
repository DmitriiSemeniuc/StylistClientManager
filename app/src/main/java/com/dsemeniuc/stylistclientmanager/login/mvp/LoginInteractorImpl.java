package com.dsemeniuc.stylistclientmanager.login.mvp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.dsemeniuc.stylistclientmanager.auth.GoogleAuthenticator;
import com.dsemeniuc.stylistclientmanager.base.Api;
import com.dsemeniuc.stylistclientmanager.entity.AppUser;
import com.dsemeniuc.stylistclientmanager.entity.Me;
import com.dsemeniuc.stylistclientmanager.utils.SharedPreferencesWrapper;
import com.dsemeniuc.stylistclientmanager.utils.Utils;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;

import java.util.concurrent.Callable;

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
    private Utils utils;

    public LoginInteractorImpl(Api api, AppUser appUser, GoogleAuthenticator authenticator, SharedPreferencesWrapper sp, Utils utils) {
        this.api = api;
        this.appUser = appUser;
        this.googleAuthenticator = authenticator;
        this.sp = sp;
        this.utils = utils;
    }

    @Override
    public Single<Intent> loginWithGoogle() {
        return Single.just(getGoogleApiClient());
    }

    private Intent getGoogleApiClient(){
        GoogleApiClient client = googleAuthenticator.getApiClient();
        return Auth.GoogleSignInApi.getSignInIntent(client);
    }

    @Override
    public void  onLoginWithGoogle(GoogleSignInResult result, OnLoginListener listener) {
        if(result.isSuccess()){
            setAppUser(result.getSignInAccount());
            sp.saveUser(appUser.getMe());
            sp.setUserLogged();
            listener.onLoginSuccess();
        } else {
            if(utils.isNetworkAvailable()){
                listener.onLoginError();
            } else {
                listener.onNoInternetAccess();
            }
        }
    }

    @Override
    public void silentSignInWithGoogle(OptionalPendingResult<GoogleSignInResult> opr, LoginPresenter presenter) {
        if (opr.isDone()) {
            GoogleSignInResult result = opr.get();
            handleGoogleSignInResult(result, presenter);
        } else {
            presenter.onShowProgressDialog();
            opr.setResultCallback(googleSignInResult -> {
                presenter.onHideProgressDialog();
                handleGoogleSignInResult(googleSignInResult, presenter);
            });
        }
    }

    private void setAppUser(GoogleSignInAccount account){
        Me me = new Me();
        me.setId(account.getId());
        me.setName(account.getDisplayName());
        me.setEmail(account.getEmail());
        if(account.getPhotoUrl() != null){
            me.setPhotoUrl(account.getPhotoUrl().toString());
        }
        appUser.setMe(me);
    }

    @Override
    public void auth(Context context, FragmentActivity activity, LoginPresenter presenter) {
        setGoogleApiClient(context, activity);
        silentSignInWithGoogle(googleAuthenticator.getOptionalPendingResult(), presenter);
    }

    @Override
    public void setGoogleApiClient(Context context, FragmentActivity activity){
        googleAuthenticator.setGoogleApiClient(context, activity);
    }

    /**
     * Handle Sign In result.
     * If result success -> go to Sign In Activity and finish this
     */
    private void handleGoogleSignInResult(GoogleSignInResult result, final LoginPresenter presenter) {
        if (result.isSuccess()){
            setAppUser(result.getSignInAccount());
            presenter.goToMainScreen();
        }
    }

    @Override
    public Single<Me> login() {
        return null;
    }
}
