package com.cuipengyu.solitarysoulbook.base;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.widget.CommonToolbar;
import com.cuipengyu.solitarysoulbook.widget.CustomDialog;

public abstract class BaseActivity extends AppCompatActivity {
    private final String TAG = getClass().getName();
    private CustomDialog dialog = null;
    private CommonToolbar toolbar = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindViewLayout());
        initStatus();
        initHeader();
        initView();
        initData();
    }

    //绑定布局文件
    public abstract int bindViewLayout();

    //设置沉浸式状态栏
    private void initStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup contentFrameLayout = findViewById(Window.ID_ANDROID_CONTENT);
            contentFrameLayout.getChildAt(0).setFitsSystemWindows(true);
        }
    }

    private void initHeader() {
        toolbar = findViewById(R.id.toolbar_common);
//        setSearchVisibility(View.GONE);
    }

    //初始化控件
    public abstract void initView();

    public abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dialog = null;
    }

    public void setLeftBar(String text) {
        toolbar.setLeftText(text);
    }

    public void setLeftImage(int Ides) {
        toolbar.setLeftIamge(Ides);
    }

    public void setLeftImage() {
        toolbar.setLeftIamge();
    }

    public void setTitleBar(String title) {
        toolbar.setTitleBar(title);
    }

    public void setRightImage(int ids) {
        toolbar.setRightImage(ids);
    }

    public void setRightText(String text) {
        toolbar.setRightText(text);
    }

    public void setLeftBarBack() {
        toolbar.setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setRightListener(View.OnClickListener listener) {
        toolbar.setRightListener(listener);
    }

    //初始化SearchView
    public void initSearch() {
        toolbar.searchInit();
        toolbar.setSearchUnderlineTransparency();

    }

    public void setSearchVisibility(int visibility) {
        toolbar.setSearchVisibility(visibility);
    }

    public void setSearchCloseListener(SearchView.OnCloseListener listener) {
        toolbar.setSearchCloseListener(listener);
    }

    public void setQueryTextListener(SearchView.OnQueryTextListener listener) {
        toolbar.setQueryTextListener(listener);
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

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public int getStatusBarHeight() {
        int statusLayoutHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusLayoutHeight = getResources().getDimensionPixelSize(resourceId);
        }
        return statusLayoutHeight;
    }

}
