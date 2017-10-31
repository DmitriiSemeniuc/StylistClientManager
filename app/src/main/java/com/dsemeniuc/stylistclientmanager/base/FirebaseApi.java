package com.dsemeniuc.stylistclientmanager.base;

import com.dsemeniuc.stylistclientmanager.entity.Me;

import io.reactivex.Single;

public class FirebaseApi implements Api {
    @Override
    public Single<Me> getMe() {
        return null;
    }
}
