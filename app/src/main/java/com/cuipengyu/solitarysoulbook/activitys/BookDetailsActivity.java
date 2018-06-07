package com.cuipengyu.solitarysoulbook.activitys;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cuipengyu.solitarysoulbook.R;
import com.cuipengyu.solitarysoulbook.base.BaseActivity;
import com.cuipengyu.solitarysoulbook.entity.Constants;
import com.cuipengyu.solitarysoulbook.entity.bean.BookDetailsBean;
import com.cuipengyu.solitarysoulbook.entity.bean.BookDetailsId;
import com.cuipengyu.solitarysoulbook.mvp.controller.BookDeTailsActivityController;
import com.cuipengyu.solitarysoulbook.mvp.presenter.BookDeTailsPresenter;

public class BookDetailsActivity extends BaseActivity implements BookDeTailsActivityController.BookDeTailsView, View.OnClickListener {
    private String mBookName;
    private BookDeTailsActivityController.BookDeTailsPresenter presenter;
    private TextView bookName_tv, bookCat_tv, book_author_tv, book_lastChapter_tv, book_wordCount_tv, book_shortIntro_tv;
    private Button book_toRead_btn;
    private ImageView bookDetails_imag;
    private String bookId;
    private String mBookUrl;
    private String bookName;
    private String bookImage;

    @Override

    public int bindViewLayout() {
        return R.layout.activity_book_details;
    }

    @Override
    public void initView() {
        bookName_tv = findViewById(R.id.bookName_tv);
        bookCat_tv = findViewById(R.id.bookCat_tv);
        book_author_tv = findViewById(R.id.book_author_tv);
        book_lastChapter_tv = findViewById(R.id.book_lastChapter_tv);
        book_wordCount_tv = findViewById(R.id.book_wordCount_tv);
        book_shortIntro_tv = findViewById(R.id.book_shortIntro_tv);
        book_toRead_btn = findViewById(R.id.book_toRead_btn);
        bookDetails_imag = findViewById(R.id.bookDetails_imag);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        mBookName = intent.getStringExtra("bookName");
        mBookUrl = intent.getStringExtra("bookUrl");
        setTitleBar("书籍详情");
        setLeftImage();
        setLeftBarBack();
        book_toRead_btn.setOnClickListener(this);
        new BookDeTailsPresenter(this);
        if (mBookUrl.equals("")) presenter.setBookName(mBookName);//通过书籍名去查询书籍
        presenter.setidBook(mBookUrl);//通过书籍id查询详情
    }

    @Override
    public void setBookData(BookDetailsBean data) {
        bookName_tv.setText(data.getBooks().get(0).getTitle());
        bookCat_tv.setText(data.getBooks().get(0).getCat());
        book_author_tv.setText(data.getBooks().get(0).getAuthor());
        book_lastChapter_tv.setText(data.getBooks().get(0).getLastChapter());
        book_wordCount_tv.setText(String.valueOf(data.getBooks().get(0).getWordCount()));
        book_shortIntro_tv.setText(data.getBooks().get(0).getShortIntro());
        bookId = data.getBooks().get(0).get_id();
        Glide.with(this).load(Constants.IMG_BASE_URL + data.getBooks().get(0).getCover()).into(bookDetails_imag);
    }

    @Override
    public void setidBook(BookDetailsId data) {
        bookName=data.getTitle();
        bookName_tv.setText(bookName);
        bookCat_tv.setText(data.getCat());
        book_author_tv.setText(data.getAuthor());
        book_lastChapter_tv.setText(data.getLastChapter());
        book_wordCount_tv.setText(String.valueOf(data.getWordCount()));
        book_shortIntro_tv.setText(data.getLongIntro());
        bookId = data.get_id();
        bookImage=data.getCover();
        Glide.with(this).load(Constants.IMG_BASE_URL + data.getCover()).into(bookDetails_imag);
    }

    @Override
    public void setPresenter(BookDeTailsActivityController.BookDeTailsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.book_toRead_btn:
                Intent intent = new Intent(this, ReadActivity.class);
                intent.putExtra("bookID", bookId);
                intent.putExtra("bookName",bookName);
                intent.putExtra("bookImage",bookImage);
                startActivity(intent);
                break;
        }
    }
}
