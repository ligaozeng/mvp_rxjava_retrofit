package com.lgz.mvp_os_library.base;

import android.content.Context;

import com.lgz.mvp_os_library.baserx.RxManager;

/**
 * Name: LGZ
 * Time: 2017/4/25.
 * Dec:
 */
public abstract class BasePresenter<T,E>{
    public Context mContext;
    public E mModel;
    public T mView;
    public RxManager mRxManage = new RxManager();

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }
    public void onStart(){
    };
    public void onDestroy() {
        mRxManage.clear();
    }
}
