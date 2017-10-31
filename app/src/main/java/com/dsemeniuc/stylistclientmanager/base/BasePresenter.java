package com.dsemeniuc.stylistclientmanager.base;

import android.content.Intent;

public interface BasePresenter {

    void onCreate();

    void onResume();

    void onStop();

    void onDestroy();

    void onRequestPermissionsResult(int requestCode, boolean isGranted);

    void onActivityResult(int requestCode, int resultCode, Intent data);
}
