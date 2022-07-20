package com.fakechat.practice.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fakechat.practice.request.HttpData;
import com.fakechat.practice.R;
import com.fakechat.practice.utils.ToastUtil;
import com.hjq.http.listener.OnHttpListener;

import okhttp3.Call;


public abstract class BaseActivity extends AppCompatActivity implements OnHttpListener<Object> {


    /** 加载对话框 */
    private ProgressDialog mDialog;
    /** 对话框数量 */
    private int mDialogTotal;

    /**
     * 当前加载对话框是否在显示中
     */
    public boolean isShowDialog() {
        return mDialog != null && mDialog.isShowing();
    }

    /**
     * 显示加载对话框
     */
    public void showDialog() {
        if (mDialog == null) {
            mDialog = new ProgressDialog(this);
            mDialog.setMessage(getResources().getString(R.string.http_loading));
            mDialog.setCancelable(false);
            mDialog.setCanceledOnTouchOutside(false);
        }
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
        mDialogTotal++;
    }

    /**
     * 隐藏加载对话框
     */
    public void hideDialog() {
        if (mDialogTotal == 1) {
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
            }
        }
        if (mDialogTotal > 0) {
            mDialogTotal--;
        }
    }

    @Override
    public void onStart(Call call) {
        showDialog();
    }

    @Override
    public void onSucceed(Object result) {
        if (result instanceof HttpData) {
            ToastUtil.showShort(((HttpData<?>) result).getMessage());
        }
    }

    @Override
    public void onFail(Exception e) {
        ToastUtil.showShort(e.getMessage());
    }

    @Override
    public void onEnd(Call call) {
        hideDialog();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        setView();
        initListener();
    }

    /**
     * 设置 Activity 的 Layout Id
     *
     * @return 返回 xml Id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 设置控件相关配置
     */
    protected abstract void setView();

    /**
     * 初始化各种监听器
     */
    protected abstract void initListener();

    /**
     * 简化关联控件
     *
     * @param id  view 的 id
     * @param <T> 泛型，当前 View 对应的 View 下面的子类
     * @return 返回已经转型的类型
     */
    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);

    }

    /**
     * 携带bundle转跳Activity
     *
     * @param bundle 参数
     * @param c      目标Activity类
     */
    protected void startActivity(Bundle bundle, Class c) {
        Intent intent = new Intent(this, c);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 转跳Activity
     *
     * @param c 目标Activity类
     */
    protected void startActivity(Class c) {
        startActivity(new Intent(this, c));
    }

    /**
     * 带动画地转跳Activity
     *
     * @param c
     * @param enterAnim
     * @param outAnim
     */
    protected void startActivityWithAnim(Class c, int enterAnim, int outAnim) {
        startActivity(c);
        overridePendingTransition(enterAnim, outAnim);
    }

    protected void finishWithAnim(int enterAnim, int outAnim) {
        this.finish();
        overridePendingTransition(enterAnim, outAnim);
    }

    /**
     * 刷新数据用
     */
    public void refreshData(Intent intent) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
