package com.cuipengyu.solitarysoulbook.utils;

import com.cuipengyu.solitarysoulbook.entity.bean.ChapterBody;

import java.io.File;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/6/7
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ：
 */
public class CacheManager {
    private static CacheManager manager;

    public static CacheManager getInstance() {
        return manager == null ? (manager = new CacheManager()) : manager;
    }



    private String getSearchHistoryKey() {
        return "searchHistory";
    }

    /**
     * 获取我收藏的书单列表
     *
     * @return
     */

    private String getCollectionKey() {
        return "my_book_lists";
    }



    private String getTocListKey(String bookId) {
        return bookId + "-bookToc";
    }

    public File getChapterFile(String bookId, int chapter) {
        File file = FileUtil.getChapterFile(bookId, chapter);
        if (file != null && file.length() > 50)
            return file;
        return null;
    }

    public void saveChapterFile(String bookId, int chapter, ChapterBody data) {
        File file = FileUtil.getChapterFile(bookId, chapter);
        String body=data.getChapter().getBody();
        FileUtil.writeFile(file.getAbsolutePath(), StringUtils.formatContent(body), false);
    }

    /**
     * 获取缓存大小
     *
     * @return
     */
    /**
     * 清除缓存
     *
     * @param clearReadPos 是否删除阅读记录
     */
}
