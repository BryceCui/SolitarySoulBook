package com.cuipengyu.solitarysoulbook.activitys;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.base.BaseActivity;
import com.cuipengyu.solitarysoulbook.base.BaseReadView;
import com.cuipengyu.solitarysoulbook.base.OnReadStateChangeListener;
import com.cuipengyu.solitarysoulbook.base.OnViewClickListener;
import com.cuipengyu.solitarysoulbook.entity.Constants;
import com.cuipengyu.solitarysoulbook.entity.bean.ChapterBody;
import com.cuipengyu.solitarysoulbook.entity.bean.ChapterLink;
import com.cuipengyu.solitarysoulbook.entity.bean.EvenBusEntityBook;
import com.cuipengyu.solitarysoulbook.entity.bean.UserShelfBean;
import com.cuipengyu.solitarysoulbook.mvp.controller.ReadActivityController;
import com.cuipengyu.solitarysoulbook.mvp.presenter.ReadActivityPresenter;
import com.cuipengyu.solitarysoulbook.utils.CacheManager;
import com.cuipengyu.solitarysoulbook.utils.DbUtils;
import com.cuipengyu.solitarysoulbook.utils.LogUtils;
import com.cuipengyu.solitarysoulbook.utils.ScreenUtil;
import com.cuipengyu.solitarysoulbook.utils.SettingManager;
import com.cuipengyu.solitarysoulbook.utils.ThemeManager;
import com.cuipengyu.solitarysoulbook.view.OverlappedWidget;
import com.cuipengyu.solitarysoulbook.widget.BindViewHolder;
import com.cuipengyu.solitarysoulbook.widget.CustomDialog;
import com.cuipengyu.solitarysoulbook.widget.PageWidget;

import org.greenrobot.eventbus.EventBus;

public class ReadActivity extends BaseActivity implements ReadActivityController.ReadView, View.OnClickListener {
    //使用onActivityResult通知书架更新
    public FragmentTransaction mFragmentTransaction;
    public FragmentManager fragmentManager;
    public String curFragmentTag = "mShelfFragment";
    private ReadActivityController.ReadPresenter mReadActivityPresenter;
    private String bookID;
    private int currentChapter = 1;
    private String data;
    private String bookId;
    private BaseReadView mPageWidget;
    private ChapterLink chapterLink;
    private boolean startRead = false;
    private FrameLayout read_widget;
    private int curTheme = -1;
    private RelativeLayout read_setting_layout;
    private RelativeLayout Relativelayout_main;
    private int FontSize = 15;
    private int MinFontSize = 15;
    private TextView read_chapter_list_tv, read_pageview_tv, read_overlapped_tv, read_minfontsize__tv, read_maxfontsize__tv;
    private ImageView theme_leather_bg, read_theme_green;
    private int mType = 0;
    private String bookName;
    private String bookImage;
    private CustomDialog customDialog = null;

    @Override
    public int bindViewLayout() {
        return R.layout.activity_read;
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        bookID = intent.getStringExtra("bookID");
        bookName = intent.getStringExtra("bookName");
        bookImage = intent.getStringExtra("bookImage");
        chapterLink = new ChapterLink();
        new ReadActivityPresenter(this);
        curTheme = SettingManager.getInstance().getReadTheme();
        ThemeManager.setReaderTheme(curTheme, Relativelayout_main);
        mReadActivityPresenter.getChapterListData(bookID);
    }

    @Override
    public void initView() {
        read_widget = findViewById(R.id.read_widget);
        read_setting_layout = findViewById(R.id.read_setting_layout);
        Relativelayout_main = findViewById(R.id.Relativelayout_main);
        read_setting_layout.setVisibility(View.GONE);
        read_chapter_list_tv = findViewById(R.id.read_chapter_list_tv);
        read_pageview_tv = findViewById(R.id.read_pageview_tv);
        read_overlapped_tv = findViewById(R.id.read_overlapped_tv);
        read_minfontsize__tv = findViewById(R.id.read_minfontsize__tv);
        read_maxfontsize__tv = findViewById(R.id.read_maxfontsize__tv);
        theme_leather_bg = findViewById(R.id.theme_leather_bg);
        read_theme_green = findViewById(R.id.read_theme_green);
        read_chapter_list_tv.setOnClickListener(this);
        read_pageview_tv.setOnClickListener(this);
        read_overlapped_tv.setOnClickListener(this);
        read_minfontsize__tv.setOnClickListener(this);
        read_maxfontsize__tv.setOnClickListener(this);
        theme_leather_bg.setOnClickListener(this);
        read_theme_green.setOnClickListener(this);
    }

    @Override
    public void setChapterListData(ChapterLink ChapterLink) {
        chapterLink = ChapterLink;
        data = ChapterLink.getMixToc().getChapters().get(currentChapter).getLink();
        bookId = ChapterLink.getMixToc().getBook();
//        mPageWidget = new NoAimWidget(this, ChapterLink.getMixToc().getBook(), ChapterLink.getMixToc().getChapters(), new ReadListener());
        mPageWidget = new PageWidget(this, bookId, ChapterLink.getMixToc().getChapters(), new ReadListener());
        read_widget.removeAllViews();
        read_widget.addView(mPageWidget);
        mPageWidget.setTextColor(ContextCompat.getColor(this, R.color.reader_menu_bg_color), ContextCompat.getColor(this, R.color.book_read_top_text));
        mPageWidget.setFontSize(ScreenUtil.dpToPx(12));
        readCurrentChapter();
    }

    @Override
    public void setChapterBody(ChapterBody chapterBody, int cha) {
        showChapterRead(chapterBody, cha);
    }

    private void readCurrentChapter() {
        //查看本地是否有缓存章节文件
        if (CacheManager.getInstance().getChapterFile(bookId, currentChapter) != null) {
            //存在
            showChapterRead(null, currentChapter);
        } else {
//            mPresenter.getChapterRead(mChapterList.get(currentChapter - 1).link, currentChapter);
            mReadActivityPresenter.getChapterBodyData(data, currentChapter - 1);
        }
    }

    private void showChapterRead(ChapterBody chapterBody, int currentChapter) {
        if (chapterBody != null) {
            CacheManager.getInstance().saveChapterFile(bookId, currentChapter, chapterBody);
        }
        if (!startRead) {
            startRead = true;
            if (!mPageWidget.isPrepared) {
                mPageWidget.init(curTheme);
            } else {
                mPageWidget.jumpToChapter(currentChapter);
            }
        }
    }

    @Override
    public void setPresenter(ReadActivityController.ReadPresenter presenter) {
        this.mReadActivityPresenter = presenter;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                customDialog = new CustomDialog.Builder(getFragmentManager()).setLayoutRes(R.layout.dialog_readloding_layout).setGravity(Gravity.CENTER).setCancelable(true).setDimount(0.1f).addOnClickListener(R.id.read_dialog_yes, R.id.read_dialog_no).setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, CustomDialog tDialog) {
                        switch (view.getId()) {
                            case R.id.read_dialog_yes:
                                if (bookName.equals("")) {
                                    return;
                                }
                                DbUtils.getSession().getUserShelfBeanDao().insertOrReplace(new UserShelfBean(null, null, bookName, bookImage, bookID));
                                customDialog.dismiss();
                                finish();
                                break;
                            case R.id.read_dialog_no:
                                customDialog.dismiss();
                                finish();
                                break;
                        }
                    }
                }).create().show();

                break;
            case KeyEvent.KEYCODE_MENU:
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (SettingManager.getInstance().isVolumeFlipEnable()) {
                    return true;
                }
                break;
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (SettingManager.getInstance().isVolumeFlipEnable()) {
                    return true;
                }
                break;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            if (SettingManager.getInstance().isVolumeFlipEnable()) {
                mPageWidget.nextPage();
                return true;// 防止翻页有声音
            }
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            if (SettingManager.getInstance().isVolumeFlipEnable()) {
                mPageWidget.prePage();
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.read_chapter_list_tv:
                //章节目录
                break;
            case R.id.read_pageview_tv:
                LoadingShow();
                mPageWidget = new PageWidget(this, bookId, chapterLink.getMixToc().getChapters(), new ReadListener());
                read_widget.removeAllViews();
                read_widget.addView(mPageWidget);
                LoadingDismiss();
//                mPageWidget.setTextColor(ContextCompat.getColor(this, R.color.reader_menu_bg_color), ContextCompat.getColor(this, R.color.book_read_top_text));
//                mPageWidget.setFontSize(ScreenUtil.dpToPx(12));
                break;
            case R.id.read_overlapped_tv:
                LoadingShow();
                mPageWidget = new OverlappedWidget(this, chapterLink.getMixToc().getBook(), chapterLink.getMixToc().getChapters(), new ReadListener());
                read_widget.removeAllViews();
                read_widget.addView(mPageWidget);
                LoadingDismiss();
//                mPageWidget.setTextColor(ContextCompat.getColor(this, R.color.reader_menu_bg_color), ContextCompat.getColor(this, R.color.book_read_top_text));
//                mPageWidget.setFontSize(ScreenUtil.dpToPx(12));
                break;
            case R.id.read_minfontsize__tv:
                if (MinFontSize < FontSize) {
                    FontSize--;
                    mPageWidget.setFontSize(FontSize);
                }
                break;
            case R.id.read_maxfontsize__tv:
                FontSize++;
                mPageWidget.setFontSize(FontSize);
                break;
            case R.id.theme_leather_bg:
                mPageWidget.setTheme(3);
                break;
            case R.id.read_theme_green:
                mPageWidget.setTheme(2);
                break;
        }
    }

    //通过findFragmentByTag找到fragment发送标记
    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        Fragment f = fragmentManager.findFragmentByTag(curFragmentTag);
        /*然后在碎片中调用重写的onActivityResult方法*/
        f.onActivityResult(1, resultCode, data);
    }

    private class ReadListener implements OnReadStateChangeListener {

        @Override
        public void onChapterChanged(int chapter) {
            Log.e("chapter", chapter + "");
            currentChapter = chapter;
//            mTocListAdapter.setCurrentChapter(currentChapter);
            // 加载前一节 与 后三节
            for (int i = chapter - 1; i <= chapter + 3 && i <= chapterLink.getMixToc().getChapters().size(); i++) {
                if (i > 0 && i != chapter && CacheManager.getInstance().getChapterFile(bookId, i) == null) {
                    mReadActivityPresenter.getChapterBodyData(chapterLink.getMixToc().getChapters().get(i - 1).getLink(), i);
                    Log.e("onChapterChanged---" + i, chapterLink.getMixToc().getChapters().get(i).getLink());
//                    setdata(data, i - 1);
                }
            }
        }

        @Override
        public void onPageChanged(int chapter, int page) {
            Log.e("chapter2", chapter + "--" + page);
        }

        @Override
        public void onLoadChapterFailure(int chapter) {
            Log.e("onLoadChapterFailure", chapter + "--");
            startRead = false;
            if (CacheManager.getInstance().getChapterFile(bookId, chapter) == null)
                mReadActivityPresenter.getChapterBodyData(chapterLink.getMixToc().getChapters().get(chapter - 1).getLink(), chapter);
//            setdata(data, chapter);
        }

        @Override
        public void onCenterClick() {
            read_setting_layout.setVisibility(View.VISIBLE);
        }

        @Override
        public void onFlip() {
            read_setting_layout.setVisibility(View.GONE);
        }
    }
}
