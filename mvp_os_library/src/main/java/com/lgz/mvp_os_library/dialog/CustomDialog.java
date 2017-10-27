package com.lgz.mvp_os_library.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;

/**
 * Name: LGZ
 * Time: 2017/5/9.
 * Dec:
 * 自定义的dialog基类
 */
public class CustomDialog extends Dialog {

    public Context context;

    public CustomDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public CustomDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    protected CustomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void onCreate(Bundle savedInstanceState,int layoutId) {
        onCreate(savedInstanceState);
        this.setContentView(layoutId);
    }


}
