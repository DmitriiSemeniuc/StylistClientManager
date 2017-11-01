package com.dsemeniuc.stylistclientmanager.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;

import com.dsemeniuc.stylistclientmanager.R;
import com.dsemeniuc.stylistclientmanager.base.BaseActivity;
import com.dsemeniuc.stylistclientmanager.login.di.DaggerLoginComponent;
import com.dsemeniuc.stylistclientmanager.login.di.LoginModule;
import com.dsemeniuc.stylistclientmanager.login.mvp.LoginPresenter;
import com.dsemeniuc.stylistclientmanager.login.mvp.LoginView;
import com.dsemeniuc.stylistclientmanager.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    public static final String EXTRA_LOGGED = "user logged";
    private ProgressDialog progressDialog;
    private boolean logged = false;

    @Inject
    LoginPresenter presenter;

    @BindView(R.id.layout_main) ViewGroup mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetchExtras();
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        DaggerLoginComponent.builder()
            .appComponent(getApp().getComponent())
            .loginModule(new LoginModule(this))
            .build()
            .inject(this);
        presenter.authenticate(this, this, logged);
    }

    private void fetchExtras(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(checkNotNull(bundle)){
            logged = bundle.getBoolean(EXTRA_LOGGED, false);
        }
    }

    @OnClick(R.id.btnLoginWithGoogle)
    void onBtnLoginWithGoogleClicked(){
        presenter.loginWithGoogle();
    }

    @Override
    public void startLoginIntent(Intent intent, int requestCode){
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void goToMainScreen() {
        MainActivity.start(this);
        finish();
    }

    @Override
    public void showProgress() {
        if (null == progressDialog) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(getString(R.string.please_wait));
            progressDialog.setIndeterminate(true);
        }
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        if (null != progressDialog && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    public static void start(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void startAsLogged(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(EXTRA_LOGGED, true);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
