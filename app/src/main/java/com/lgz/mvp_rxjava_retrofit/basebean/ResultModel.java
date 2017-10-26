package com.lgz.mvp_rxjava_retrofit.basebean;

import java.io.Serializable;

/**
 * Name: LGZ
 * Time: 2017/5/4.
 * Dec:
 */
public class ResultModel<T> extends BaseResult implements Serializable {

    T data;

    public ResultModel() {
        super();
    }

//    public ResultModel(T data) {
//        setSuccData(data);
//    }

//    public ResultModel(String errorInfo) {
//        setErrorInfo(errorInfo);
//    }

    public T getData() {
        return data;
    }

    public ResultModel<T> setData(T data) {
        this.data = data;
        return this;
    }

}