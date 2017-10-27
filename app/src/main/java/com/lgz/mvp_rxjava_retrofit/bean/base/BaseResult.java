package com.lgz.mvp_rxjava_retrofit.bean.base;

/**
 * Name: LGZ
 * Time: 2017/5/4.
 * Dec:
 */

public class BaseResult {


    public static final int SUCCESS = 0;
    public static final int FAILED = -1;

    private Integer err_code;
    private String err_msg;

    public BaseResult() {
        super();
//        setCode(SUCCESS);
    }

//    public BaseResult(String errorInfo) {
//        setErrorInfo(errorInfo);
//    }

    public Integer getErr_code() {
        return err_code;
    }

    public void setErr_code(Integer err_code) {
        this.err_code = err_code;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }


//    protected final BaseResult setErrorInfo(String msg) {
//        this.code = FAILED;
//        this.msg = msg;
//        return this;
//    }
//
//    public final boolean failed() {
//        return code == FAILED;
//    }

}
