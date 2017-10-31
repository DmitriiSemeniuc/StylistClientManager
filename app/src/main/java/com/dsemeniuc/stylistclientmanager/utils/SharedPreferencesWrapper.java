package com.dsemeniuc.stylistclientmanager.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.dsemeniuc.stylistclientmanager.base.Const;

public class SharedPreferencesWrapper {

    private SharedPreferences sp;

    public SharedPreferencesWrapper(Context context) {
        sp = context.getSharedPreferences(Const.SP_NAME, Context.MODE_PRIVATE);
    }

    public String getUser(){
        return sp.getString(Const.User.EXISTED, Const.User.NEW);
    }

    public String getUserEmail(){
        return sp.getString(Const.User.EMAIL, "");
    }

    public boolean isUserLogged(){
        return sp.getBoolean(Const.User.LOGGED, false);
    }
}
