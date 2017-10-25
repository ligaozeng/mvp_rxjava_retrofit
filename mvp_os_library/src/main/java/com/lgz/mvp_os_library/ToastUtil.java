package com.lgz.mvp_os_library;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by : lgz
 * Time : 17/10/25.
 * Desc :
 */

public class ToastUtil {

    /**
     * 显示吐司
     *
     * @param context
     * @param str
     */
    public static void showToast(Context context, String str) {
        Toast.makeText(context, str, Toast.LENGTH_LONG).show();
    }

}
