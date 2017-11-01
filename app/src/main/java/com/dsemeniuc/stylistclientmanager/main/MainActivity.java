package com.dsemeniuc.stylistclientmanager.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dsemeniuc.stylistclientmanager.R;
import com.dsemeniuc.stylistclientmanager.base.BaseActivity;
import com.dsemeniuc.stylistclientmanager.main.di.DaggerMainComponent;
import com.dsemeniuc.stylistclientmanager.main.di.MainModule;
import com.dsemeniuc.stylistclientmanager.main.mvp.MainPresenter;
import com.dsemeniuc.stylistclientmanager.main.mvp.MainView;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerMainComponent.builder()
            .appComponent(getApp().getComponent())
            .mainModule(new MainModule(this))
            .build()
            .inject(this);
    }

    public static void start(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
