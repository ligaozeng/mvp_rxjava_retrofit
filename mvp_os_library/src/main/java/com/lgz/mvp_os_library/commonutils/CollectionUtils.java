package com.lgz.mvp_os_library.commonutils;

import java.util.Collection;

/**
 * Name: LGZ
 * Time: 2017/4/28.
 * Dec:
 * 集合操作工具类
 */

public class CollectionUtils {

    /**
     * 判断集合是否为null或者0个元素
     *
     * @param c
     * @return
     */
    public static boolean isNullOrEmpty(Collection c) {
        if (null == c || c.isEmpty()) {
            return true;
        }
        return false;
    }
}
