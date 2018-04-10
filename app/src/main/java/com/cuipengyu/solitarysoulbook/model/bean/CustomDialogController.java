package com.cuipengyu.solitarysoulbook.model.bean;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;

import com.cuipengyu.solitarysoulbook.base.OnBindViewListener;
import com.cuipengyu.solitarysoulbook.base.OnViewClickListener;

//dialog默认的控制信息
public class CustomDialogController implements Parcelable {
    public static final Creator<CustomDialogController> CREATOR = new Creator<CustomDialogController>() {
        @Override
        public CustomDialogController createFromParcel(Parcel in) {
            return new CustomDialogController(in);
        }

        @Override
        public CustomDialogController[] newArray(int size) {
            return new CustomDialogController[size];
        }
    };
    private OnViewClickListener onViewClickListener;
    private OnBindViewListener onBindViewListener;
    private DialogInterface.OnDismissListener onDismissListener;
    private FragmentManager fragmentManager;
    private View dialogView;
    //id
    private int layoutRes;
    //位置
    private int gravity;
    private String tag;
    private int[] ids;
    private int orientation;
    private boolean cancelable;//弹窗是否可以取消
    //透明度
    private float dimAmount;

    public CustomDialogController() {
    }

    public CustomDialogController(Parcel in) {
        layoutRes = in.readInt();
        gravity = in.readInt();
        tag = in.readString();
        ids = in.createIntArray();
        orientation = in.readInt();
        cancelable = in.readByte() != 0;
        dimAmount = in.readFloat();
    }

    public DialogInterface.OnDismissListener getOnDismissListener() {
        return onDismissListener;
    }

    public OnBindViewListener getOnBindViewListener() {
        return onBindViewListener;
    }

    public OnViewClickListener getOnViewClickListener() {
        return onViewClickListener;
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public View getDialogView() {
        return dialogView;
    }

    public int getLayoutRes() {
        return layoutRes;
    }

    public int getGravity() {
        return gravity;
    }

    public String getTag() {
        return tag == null ? "" : tag;
    }

    public int[] getIds() {
        return ids;
    }


    public int getOrientation() {
        return orientation;
    }

    public boolean isCancelable() {
        return cancelable;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(dimAmount);
        dest.writeInt(layoutRes);
        dest.writeInt(gravity);
        dest.writeString(tag);
        dest.writeIntArray(ids);
        dest.writeInt(orientation);
        dest.writeByte((byte) (cancelable ? 1 : 0));

    }

    public float getDimAmount() {
        return dimAmount;
    }

    public static class ControllerParams {
        public FragmentManager mFragmentManager;
        public int mLayoutRes;
        public int mGravity = Gravity.CENTER;
        public String mTag = "TDialog";
        public int[] ids;
        public float mDimAmount = 0.2f;
        public int orientation = LinearLayoutManager.VERTICAL;//默认RecyclerView的列表方向为垂直方向
        public boolean mCancelable = true;//弹窗是否可以取消
        public View mDialogView;//直接使用传入进来的View,而不需要通过解析Xml
        public DialogInterface.OnDismissListener mOnDismissListener;
        public OnViewClickListener mOnViewClickListener;
        public OnBindViewListener bindViewListener;

        public void apply(CustomDialogController tController) {
            tController.fragmentManager = mFragmentManager;
            if (mLayoutRes > 0) {
                tController.layoutRes = mLayoutRes;
            }
            if (mDialogView != null) {
                tController.dialogView = mDialogView;
            }
            tController.dimAmount = mDimAmount;
            tController.gravity = mGravity;
            tController.tag = mTag;
            if (ids != null) {
                tController.ids = ids;
            }
            tController.onViewClickListener = mOnViewClickListener;
            tController.onBindViewListener = bindViewListener;
            tController.onDismissListener = mOnDismissListener;
            if (tController.getLayoutRes() <= 0 && tController.getDialogView() == null) {
                throw new IllegalArgumentException("请先调用setLayoutRes()方法设置弹窗所需的xml布局!");
            }
            //弹窗是否可以取消
            tController.cancelable = mCancelable;
        }
    }
}
