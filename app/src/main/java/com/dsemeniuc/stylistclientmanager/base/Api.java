package com.dsemeniuc.stylistclientmanager.base;

import com.dsemeniuc.stylistclientmanager.entity.Me;

import io.reactivex.Single;

public interface Api {

    Single<Me> getMe();
}
