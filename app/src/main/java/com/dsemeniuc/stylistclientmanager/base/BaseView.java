package com.dsemeniuc.stylistclientmanager.base;

import android.view.View;

public interface BaseView {

    void onError(int resource);

    void showPermissionsDialog(String permission, int statusCode);

    void hideKeyboard(View v);

    void showKeyBoard();
}
