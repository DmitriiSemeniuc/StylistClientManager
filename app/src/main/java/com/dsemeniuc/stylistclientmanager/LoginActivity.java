package com.dsemeniuc.stylistclientmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.ViewGroup;

import com.dsemeniuc.stylistclientmanager.base.BaseActivity;
import com.dsemeniuc.stylistclientmanager.login.di.DaggerLoginComponent;
import com.dsemeniuc.stylistclientmanager.login.di.LoginModule;
import com.dsemeniuc.stylistclientmanager.login.mvp.LoginPresenter;
import com.dsemeniuc.stylistclientmanager.login.mvp.LoginView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @Inject
    LoginPresenter presenter;

    @BindView(R.id.etEmail) AppCompatEditText etEmail;
    @BindView(R.id.etPassword) AppCompatEditText etPassword;
    @BindView(R.id.layout_main) ViewGroup mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        DaggerLoginComponent.builder()
            .appComponent(getApp().getComponent())
            .loginModule(new LoginModule(this))
            .build()
            .inject(this);
        presenter.verifyUserType(this, this);
    }

    /*@OnClick(R.id.btnLogin)
    void onBtnLoginClicked(){
        presenter.loginWithEmail(etEmail.getText().toString(), etPassword.getText().toString());
    }*/

    @OnClick(R.id.btnLoginWithGoogle)
    void onBtnLoginWithGoogleClicked(){
        presenter.loginWithGoogle();
    }

    @Override
    public void startLoginIntent(Intent intent, int requestCode){
        startActivityForResult(intent, requestCode);
    }
}
