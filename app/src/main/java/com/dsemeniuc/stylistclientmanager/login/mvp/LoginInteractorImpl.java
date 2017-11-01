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
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import io.reactivex.Single;

public class LoginInteractorImpl implements LoginInteractor, GoogleApiClient.OnConnectionFailedListener {

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
            firebaseAuthWithGoogle(result.getSignInAccount(), listener);
        } else {
            if(utils.isNetworkAvailable()){
                listener.onLoginError();
            } else {
                listener.onNoInternetAccess();
            }
        }
    }

    @Override
    public void firebaseAuthWithGoogle(GoogleSignInAccount acct, OnLoginListener listener){
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        googleAuthenticator.getFirebaseAuth().signInWithCredential(credential)
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    FirebaseUser user = googleAuthenticator.getFirebaseAuth().getCurrentUser();
                    setAppUser(user);
                    sp.saveUser(appUser.getMe());
                    sp.setUserLogged();
                    listener.onLoginSuccess();
                } else {
                    // If sign in fails, display a message to the user.
                    if(utils.isNetworkAvailable()){
                        listener.onLoginError();
                    } else {
                        listener.onNoInternetAccess();
                    }
                }
            });
    }

    @Override
    public void silentSignInWithGoogle(OptionalPendingResult<GoogleSignInResult> opr, OnLoginListener listener) {
        if (opr.isDone()) {
            GoogleSignInResult result = opr.get();
            handleGoogleSignInResult(result, listener);
        } else {
            //presenter.onShowProgressDialog();
            opr.setResultCallback(googleSignInResult -> {
                //presenter.onHideProgressDialog();
                handleGoogleSignInResult(googleSignInResult, listener);
            });
        }
    }

    private void setAppUser(FirebaseUser user){
        Me me = new Me();
        me.setId(user.getUid());
        me.setName(user.getDisplayName());
        me.setEmail(user.getEmail());
        if(user.getPhotoUrl() != null){
            me.setPhotoUrl(user.getPhotoUrl().toString());
        }
        appUser.setMe(me);
    }

    @Override
    public void auth(Context context, FragmentActivity activity, OnLoginListener listener) {
        setGoogleApiClient(context, activity);
        setFirebaseAuth();
        silentSignInWithGoogle(googleAuthenticator.getOptionalPendingResult(), listener);
    }

    @Override
    public void setGoogleApiClient(Context context, FragmentActivity activity){
        googleAuthenticator.setGoogleApiClient(context, activity);
    }

    public void setFirebaseAuth(){
        googleAuthenticator.setFirebaseAuth();
    }

    /**
     * Handle Sign In result.
     * If result success -> go to Sign In Activity and finish this
     */
    private void handleGoogleSignInResult(GoogleSignInResult result, final OnLoginListener listener) {
        if (result.isSuccess()){
            firebaseAuthWithGoogle(result.getSignInAccount(), listener);
        } else {
            listener.onLoginError();
        }
    }

    @Override
    public void onConnectionFailed(@android.support.annotation.NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }
}
