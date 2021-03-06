package com.dsemeniuc.stylistclientmanager.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class AbstractPresenter<V extends BaseView> implements BasePresenter {

    protected V view;
    private CompositeDisposable compositeDisposable;

    public AbstractPresenter(V view) {
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        view = null;
        disposeDisposable(compositeDisposable);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, boolean isGranted) {

    }

    protected void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    private static void disposeDisposable(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }
}
