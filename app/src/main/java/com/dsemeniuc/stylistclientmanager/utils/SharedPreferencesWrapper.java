package com.dsemeniuc.stylistclientmanager.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.dsemeniuc.stylistclientmanager.base.Const;
import com.dsemeniuc.stylistclientmanager.entity.AppUser;
import com.dsemeniuc.stylistclientmanager.entity.Me;

import java.util.HashSet;
import java.util.Set;

public class SharedPreferencesWrapper {

    private SharedPreferences sp;

    public SharedPreferencesWrapper(Context context) {
        sp = context.getSharedPreferences(Const.SP_NAME, Context.MODE_PRIVATE);
    }

    public void saveUser(Me me){
        setUserId(me.getId());
        setUserName(me.getName());
        setUserEmail(me.getEmail());
    }

    public boolean isUserLogged(){
        return sp.getBoolean(Const.User.LOGGED, false);
    }

    public void setUserLogged(){
        sp.edit().putBoolean(Const.User.LOGGED, true).apply();
    }

    private void setUserId(String id){
        sp.edit().putString(Const.User.ID, id).apply();
    }

    private void setUserName(String name){
        sp.edit().putString(Const.User.NAME, name).apply();
    }

    private void setUserEmail(String email){
        sp.edit().putString(Const.User.EMAIL, email).apply();
    }

    public void resetUser(){
        sp.getAll().clear();
    }
}
