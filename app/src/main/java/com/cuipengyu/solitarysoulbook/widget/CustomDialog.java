package com.cuipengyu.solitarysoulbook.widget;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import com.cuipengyu.solitarysoulbook.base.BaseCustomDialog;
import com.cuipengyu.solitarysoulbook.base.OnBindViewListener;
import com.cuipengyu.solitarysoulbook.base.OnViewClickListener;
import com.cuipengyu.solitarysoulbook.model.bean.CustomDialogController;
import com.cuipengyu.solitarysoulbook.utils.ApplicationContextUtil;
import com.squareup.leakcanary.RefWatcher;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/4/9
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class CustomDialog extends BaseCustomDialog {
    private static final String KEY_TCONTROLLER = "CustomDialogController";
    protected CustomDialogController mController;
    private View bindView = null;
    private SparseArray<View> views = null;

    public CustomDialog() {
        mController = new CustomDialogController();
    }

    @Override
    protected int getLayoutRes() {
        return mController.getLayoutRes();
    }

    @Override
    protected View getDialogView() {
        return mController.getDialogView();
    }

    @Override
    protected void bindView(View view) {
        this.bindView = view;
        this.views = new SparseArray<>();
        //控件点击事件处理
        BindViewHolder viewHolder = new BindViewHolder(view, this);
        if (mController.isCancelable() && mController.getIds() != null && mController.getIds().length > 0) {
            for (int id : mController.getIds()) {
                viewHolder.addOnClickListener(id);
            }
        }
        //回调方法获取到布局,进行处理
        if (mController.getOnBindViewListener() != null) {
            mController.getOnBindViewListener().bindView(viewHolder);
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = ApplicationContextUtil.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }

    @Override
    public float getDimAmount() {
        return mController.getDimAmount();
    }

    @Override
    public int getGravity() {
        return mController.getGravity();
    }

    public OnViewClickListener getOnViewClickListener() {
        return mController.getOnViewClickListener();
    }

    @Override
    public boolean isCancelable() {
        return mController.isCancelable();
    }

    //恢复
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mController = (CustomDialogController) savedInstanceState.getSerializable(KEY_TCONTROLLER);
        }
    }

    //弹窗消失监听
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        DialogInterface.OnDismissListener onDismissListener = mController.getOnDismissListener();
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }

    //保存
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(KEY_TCONTROLLER, mController);
        super.onSaveInstanceState(outState);
    }

    public CustomDialog show() {
        show(mController.getFragmentManager());
        return this;
    }

    public static class Builder {

        CustomDialogController.ControllerParams params;
        CustomDialog dialog;

        public Builder(FragmentManager fragmentManager) {
            params = new CustomDialogController.ControllerParams();
            params.mFragmentManager = fragmentManager;
        }

        //各种setXXX()方法设置数据
        public Builder setLayoutRes(@LayoutRes int layoutRes) {
            params.mLayoutRes = layoutRes;
            return this;
        }

        public Builder setDialogView(View view) {
            params.mDialogView = view;
            return this;
        }

        public Builder setGravity(int gravity) {
            params.mGravity = gravity;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            params.mCancelable = cancelable;
            return this;
        }

        public Builder setOnDismissListener(DialogInterface.OnDismissListener dismissListener) {
            params.mOnDismissListener = dismissListener;
            return this;
        }


        public Builder setTag(String tag) {
            params.mTag = tag;
            return this;
        }

        public Builder setOnBindViewListener(OnBindViewListener listener) {
            params.bindViewListener = listener;
            return this;
        }

        public Builder addOnClickListener(int... ids) {
            params.ids = ids;
            return this;
        }

        public Builder setOnViewClickListener(OnViewClickListener listener) {
            params.mOnViewClickListener = listener;
            return this;
        }

        public Builder setDimount(float dimount) {
            params.mDimAmount = dimount;
            return this;
        }

        public CustomDialog create() {
            if (dialog == null) {
                dialog = new CustomDialog();
                params.apply(dialog.mController);
                Log.e(TAG, "create");
            }
            //将数据从Buidler的DjParams中传递到DjDialog中
            return dialog;
        }
    }
}
