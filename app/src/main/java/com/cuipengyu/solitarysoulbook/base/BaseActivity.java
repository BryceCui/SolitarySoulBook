package com.cuipengyu.solitarysoulbook.base;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.widget.CommonToolbar;
import com.cuipengyu.solitarysoulbook.widget.CustomDialog;

public abstract class BaseActivity extends AppCompatActivity {
    private final String TAG = getClass().getName();
    private CustomDialog dialog = null;
    private CommonToolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindViewLayout());
        initHeader();
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialog = null;
    }

    //绑定布局文件
    public abstract int bindViewLayout();

    private void initHeader() {
        toolbar = findViewById(R.id.toolbar_common);
    }

    //初始化控件
    public abstract void initView();

    public abstract void initData();

    public void setLeftIamge(@DrawableRes int idRes) {
        toolbar.setLeftIamge(idRes);
    }

    public void setLeftText(String text) {
        toolbar.setLeftText(text);
    }

    public void setRightImage(int id) {
        toolbar.setRightImage(id);
    }

    public void setRightText(String text) {
        toolbar.setRightText(text);
    }

    public void setTitleText(String text) {
        toolbar.setTitle(text);
    }

    public void setToolbarBackGround(int id) {
        toolbar.setToolbarBackground(id);
    }

    public void setLeftsetVisibility(int visibility) {
        toolbar.setLeftVisibility(visibility);
    }

    public void setRightVisibility(int visibility) {
        toolbar.setRightVisibility(visibility);
    }

    public void ToolbarVisibility(int visibility) {
        toolbar.setVisibility(visibility);
    }

    public void setLeftListener(View.OnClickListener listener) {
        toolbar.setLeftListener(listener);
    }

    public void setRightListener(View.OnClickListener listener) {
        toolbar.setRightListener(listener);
    }

    //设置显示搜索并且隐藏标题
    public void setShowSearchView() {
        toolbar.setTitleVisibility(View.GONE);
    }

    //显示正在加载dialog
    public void LoadingShow() {
        dialog = new CustomDialog.Builder(getFragmentManager()).setLayoutRes(R.layout.dialog_baseloding_layout).setGravity(Gravity.CENTER).setCancelable(true).setDimount(0.1f).create().show();
    }

    //取消dialog
    public void LoadingDismiss() {
        if (dialog != null) dialog.dismiss();
        dialog = null;
    }

    protected void addFragemnt(@NonNull BaseFragment baseFragment, @NonNull @IdRes int fragmentId) {
        if (baseFragment != null) {
            getFragmentManager().beginTransaction().replace(fragmentId, baseFragment, baseFragment.getClass().getSimpleName()).addToBackStack(baseFragment.getClass().getSimpleName()).commitAllowingStateLoss();
        }
    }
}
