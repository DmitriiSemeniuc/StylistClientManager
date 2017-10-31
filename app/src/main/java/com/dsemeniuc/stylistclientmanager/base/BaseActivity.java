package com.dsemeniuc.stylistclientmanager.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import javax.inject.Inject;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {

    @Inject
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onError(int resource) {
        runOnUiThread(() -> Toast.makeText(this, getResources().getString(resource), Toast.LENGTH_SHORT).show());
    }

    @Override
    public void showPermissionsDialog(String permission, int statusCode) {
        ActivityCompat.requestPermissions(this, new String[]{permission}, statusCode);
    }

    @Override
    public void showKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    @Override
    public void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public void startActivity(Class clazz) {
        Intent i = new Intent(this, clazz);
        startActivity(i);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.onRequestPermissionsResult(requestCode, grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*switch (requestCode) {
            case REQUEST_CHECK_LOCATION_SETTINGS:
                if (resultCode == RESULT_OK)
                    if (getLocationEnabledSubject().hasObservers())
                        AndroidSchedulers.mainThread().scheduleDirect(() -> getLocationEnabledSubject().onNext(new LocationEnabledEvent()));
                break;
            default:
                presenter.onActivityResult(requestCode, resultCode, data);
        }*/
    }

    public App getApp(){
        return (App) getApplication();
    }
}
