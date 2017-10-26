package com.lgz.mvp_os_library.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.lgz.mvp_os_library.baseapp.AppManager;
import com.lgz.mvp_os_library.baserx.RxManager;
import com.lgz.mvp_os_library.commonutils.TUtil;
import com.lgz.mvp_os_library.commonwidget.LoadingDialog;
import com.lgz.mvp_os_library.commonwidget.StatusBarCompat;
import com.lgz.mvp_os_library.daynightmodeutils.ChangeModeController;

import butterknife.ButterKnife;
import mvp_os_library.R;

/**
 * Name: LGZ
 * Time: 2017/4/21.
 * Dec:
 * Activity 的基类
 * 实现 MVP模式 配合多模块Activty+多Fragment
 */

public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity {
    public T mPresenter;
    public E mModel;
    public Context mContext;
    public RxManager mRxManager;
    private boolean isConfigChange=false;
    private static BaseActivity mForegroundActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isConfigChange=false;
        mRxManager=new RxManager();
        doBeforeSetcontentView();
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        mModel= TUtil.getT(this,1);
        if(mPresenter!=null){
            mPresenter.mContext=this;
        }
        this.initPresenter();
        this.initView(savedInstanceState);
    }

    /**
     * 设置layout前配置
     */
    private void doBeforeSetcontentView() {
        //设置昼夜主题
        initTheme();
        // 把actvity放到application栈中管理
        AppManager.getAppManager().addActivity(this);
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 默认着色状态栏
        SetStatusBarColor();

    }
    /*********************子类实现*****************************/
    //获取布局文件
    public abstract int getLayoutId();
    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();
    //初始化view
    public abstract void initView(Bundle savedInstanceState);


    /**
     * 设置主题
     */
    private void initTheme() {
        ChangeModeController.setTheme(this, R.style.DayTheme, R.style.NightTheme);
    }
    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor(){
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.black));
    }
    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor(int color){
        StatusBarCompat.setStatusBarColor(this,color);
    }
    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar(){
        StatusBarCompat.translucentStatusBar(this);
    }



    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     *
     * @param cls
     * @param requestCode*/
    public void startActivityForResult(Intent cls, String requestCode) {
        startActivityForResult(cls, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 开启浮动加载进度条
     */
    public void startProgressDialog() {
        LoadingDialog.showDialogForLoading(this);
    }

    /**
     * 开启浮动加载进度条
     *
     * @param msg
     */
    public void startProgressDialog(String msg) {
        LoadingDialog.showDialogForLoading(this, msg, true);
    }

    /**
     * 停止浮动加载进度条
     */
    public void stopProgressDialog() {
        LoadingDialog.cancelDialogForLoading();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.mForegroundActivity = this;

//        //debug版本不统计crash
//        if(!BuildConfig.DEBUG) {
//            //友盟统计
//            MobclickAgent.onResume(this);
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //debug版本不统计crash
//        if(!BuildConfig.DEBUG) {
//            //友盟统计
//            MobclickAgent.onPause(this);
//        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        isConfigChange=true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDestroy();
        if(mRxManager!=null) {
            mRxManager.clear();
        }
        if(!isConfigChange){
            AppManager.getAppManager().finishActivity(this);
        }
        ButterKnife.unbind(this);

    }

    public static BaseActivity getForegroundActivity() {
        return mForegroundActivity;
    }

    public static void setForegroundActivity(BaseActivity mForegroundActivity) {
        BaseActivity.mForegroundActivity = mForegroundActivity;
    }
}
