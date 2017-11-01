package com.dsemeniuc.stylistclientmanager.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dsemeniuc.stylistclientmanager.R;
import com.dsemeniuc.stylistclientmanager.base.BaseActivity;
import com.dsemeniuc.stylistclientmanager.login.LoginActivity;
import com.dsemeniuc.stylistclientmanager.splash.di.DaggerSplashComponent;
import com.dsemeniuc.stylistclientmanager.splash.di.SplashModule;
import com.dsemeniuc.stylistclientmanager.splash.mvp.SplashPresenter;
import com.dsemeniuc.stylistclientmanager.splash.mvp.SplashView;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashView {

    @Inject
    SplashPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        DaggerSplashComponent.builder()
            .appComponent(getApp().getComponent())
            .splashModule(new SplashModule(this))
            .build()
            .inject(this);
    }

    @Override
    protected void onResume() {
        presenter.onResume();
        super.onResume();
    }

    @Override
    public void onUserLogged() {
        LoginActivity.startAsLogged(this);
        finish();
    }

    @Override
    public void onUserNotLogged() {
        LoginActivity.start(this);
        finish();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
