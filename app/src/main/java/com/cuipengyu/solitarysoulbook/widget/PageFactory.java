package com.cuipengyu.solitarysoulbook.widget;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.cuipengyu.solitarysoulbook.base.OnReadStateChangeListener;
import com.cuipengyu.solitarysoulbook.entity.bean.BookStatus;
import com.cuipengyu.solitarysoulbook.entity.bean.ChapterLink;
import com.cuipengyu.solitarysoulbook.utils.FileUtil;
import com.cuipengyu.solitarysoulbook.utils.ScreenUtil;
import com.cuipengyu.solitarysoulbook.utils.SettingManager;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Vector;

/**
 * Create by    ： 崔鹏宇
 * Time  is     ： 2018/4/20
 * Email        ： cuipengyusoul@gmail.com
 * Github       ： https://github.com/SolitarySoul
 * Instructions ： 工具类
 */
public class PageFactory {
    /**
     * 屏幕的宽和高
     */
    private int mViewHeight, mViewWidth;
    /**
     * 文字绘制区域宽和高
     */
    private int mTextViewVisibleHeight, mTextViewVisibleWidth;
    /**
     * 文字绘制区域对于屏幕的margin
     */
    private int mMarginHeight, mMarginWidth;
    /**
     * 内容绘制
     */
    private Rect rectF;
    /**
     * 文字和标题字体的大小
     */
    private int mTextViewFontSize, mTitleFontSize;
    /**
     * 每页总行数
     */
    private int mPageLineCount;
    /**
     * 行间距
     */
    private int mLineSpace;
    /**
     * 字节长度
     */
    private int mBufferByteLen;
    /**
     * 高效文件内存映射
     */
    private MappedByteBuffer mFileMapByteBuffer;
    /**
     * 当前页和临时页面的位置
     */
    private int curEndPos = 0, curBeginPos = 0, tempEndPos, tempBeginPos;
    /**
     * 当前章节和临时章节
     */
    private int curChapter, tempChapter;
    /**
     * mLines：行数
     * Vector：可实现自动增长的对象数组
     * mLines：存储章节页面的内容
     */
    private Vector<String> mLines = new Vector<>();
    /**
     * 画笔
     * 标题画笔
     * 背景图片
     */
    private Paint mPaint;
    private Paint mTitlePaint;
    private Bitmap mBookPageBg;
    /**
     * 书籍id
     */
    private String mBookId;
    /**
     * 章节的总数
     */
    private int mChapterSize = 0;
    /**
     * 当前页
     */
    private int mCurrentPage = 1;
    /**
     * 字符编码
     */
    private String mCharset = "UTF-8";

    private List<ChapterLink.MixTocBean.ChaptersBean> mChaptersBeanList;
    private OnReadStateChangeListener mChangeListener;

    public PageFactory(String bookId, List<ChapterLink.MixTocBean.ChaptersBean> chaptersBeanList) {
        this(ScreenUtil.getAppWidth(), ScreenUtil.getAppHeight(), 15, bookId, chaptersBeanList);
    }

    public PageFactory(int Width, int Height, int FontSize, String bookId, List<ChapterLink.MixTocBean.ChaptersBean> chapters) {
        mViewWidth = Width;
        mViewHeight = Height;
        mTextViewFontSize = FontSize;
        mBookId = bookId;
        //行间距等于字体大小的2/5
        mLineSpace = mTextViewFontSize / 5 * 2;
        mTitleFontSize = ScreenUtil.dpToPx(16);
        mMarginHeight = ScreenUtil.dpToPx(15);
        mMarginWidth = ScreenUtil.dpToPx(15);
        //文字区域的高 = 2倍的边距 -2倍的标题大小-2倍的行间距
        mTextViewVisibleHeight = mViewHeight - mMarginHeight * 2 - mTitleFontSize * 2 - mLineSpace * 2;
        mTextViewVisibleWidth = mViewWidth - mMarginWidth * 2;
        //页面总行数= 屏幕高/内容字体大小+行距
        mPageLineCount = mViewHeight / (mTextViewFontSize + mLineSpace);
        rectF = new Rect(0, 0, mViewWidth, mViewHeight);
        //设置 ANTI_ALIAS_FLAG 属性可以产生平滑的边缘
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(mTextViewFontSize);
        mPaint.setColor(Color.BLACK);
        //标题画笔设置
        mTitlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTitlePaint.setTextSize(mTitleFontSize);
        mTitlePaint.setColor(Color.BLACK);
        mBookId = bookId;
        mChaptersBeanList = chapters;
    }

    //从开始第一章位置打开书籍
    public void openBook() {
        openBook(new int[]{0, 0});
    }

    public void openBook(int[] pos) {
        openBook(1, pos);
    }

    //打开具体章节
    public int openBook(int chapter, int[] pos) {
        this.curChapter = chapter;
        this.mChapterSize = mChaptersBeanList.size();
        //如果当前章节大于章节总数 那就相等
        if (curChapter > mChapterSize) curChapter = mChapterSize;
        //获取文件路径
        String path = getBookFile(curChapter).getPath();
        try {
            //创建文件
            File file = new File(path);
            //获取文件长度
            long length = file.length();
            //如果文件长度大于10
            if (length > 10) {
                mBufferByteLen = (int) length;
                /**
                 * 只有RandomAccessFile获取的Channel才能开启任意的这三种模式
                 * FileChannel.MapMode.READ_ONLY：得到的镜像只能读不能写
                 * FileChannel.MapMode.READ_WRITE：得到的镜像可读可写（既然可写了必然可读），对其写会直接更改到存储节点
                 * FileChannel.MapMode.PRIVATE：得到一个私有的镜像，其实就是一个(position, size)区域的副本罢了，也是可读可写，只不过写不会影响到存储节点，就是一个普通的ByteBuffer了
                 * long position(); // 获取当前操作到节点文件的哪个位置
                 */
                mFileMapByteBuffer = new RandomAccessFile(file, "r").getChannel().map(FileChannel.MapMode.READ_ONLY, 0, length);
                curBeginPos = pos[0];
                curEndPos = pos[1];
                //章节监听
                onChapterChanged(curChapter);
                mLines.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取章节文件
     *
     * @param chapter
     * @return
     */
    public File getBookFile(int chapter) {
        File file = FileUtil.getChapterFile(mBookId, chapter);
        //获取字节编码
        mCharset = FileUtil.getCharset(file.getAbsolutePath());
        return file;
    }


    /**
     * 同步绘制内容
     *
     * @param canvas
     */
    public synchronized void onDraw(Canvas canvas) {
        //如果章节的内容为0 读取下一页
        if (mLines.size() == 0) {
            curEndPos = curBeginPos;
            mLines = pageDown();
        }
        if (mLines.size() > 0) {
            //左移运算符，num << 1,相当于num乘以2
            int y = mMarginHeight + (mLineSpace << 1);
            //如果bitmap不为空，则绘制背景
            if (mBookPageBg != null) {
                canvas.drawBitmap(mBookPageBg, null, rectF, null);
            } else {
                //设置背景为白色
                canvas.drawColor(Color.WHITE);
            }
            //绘制标题
            canvas.drawText(mChaptersBeanList.get(mCurrentPage - 1).getTitle(), mMarginWidth, y, mTitlePaint);
            y += mLineSpace + mTitleFontSize;
            // 绘制阅读页面文字
            for (String line : mLines) {
                Log.e("line---", line);
                y += mLineSpace;
                //endsWith() 方法用于测试字符串是否以指定的后缀结束。
                if (line.endsWith("@")) {
                    /**
                     *   beginIndex -- 起始索引（包括）
                     *   endIndex -- 结束索引（不包括）
                     *   public String substring(int beginIndex)
                     *   public String substring(int beginIndex, int endIndex)
                     */

                    canvas.drawText(line.substring(0, line.length() - 1), mMarginWidth, y, mPaint);
                    y += mLineSpace;
                } else {
                    canvas.drawText(line, mMarginWidth, y, mPaint);
                }
                y += mTitleFontSize;
            }
            // 绘制电池
            //            if (batteryBitmap != null) {
            //                canvas.drawBitmap(batteryBitmap, marginWidth + 2,
            //                        mHeight - marginHeight - ScreenUtils.dpToPxInt(12), mTitlePaint);
            //            }
            //绘制百分比
//            float percent = (float) currentChapter * 100 / chapterSize;
//            canvas.drawText(decimalFormat.format(percent) + "%", (mWidth - percentLen) / 2,
//                    mHeight - marginHeight, mTitlePaint);
            //绘制时间
//            String mTime = dateFormat.format(new Date());
//            canvas.drawText(mTime, mWidth - marginWidth - timeLen, mHeight - marginHeight, mTitlePaint);
            //保存阅读进度
            SettingManager.getInstance().saveReadProgress(mBookId, mCurrentPage, curBeginPos, curEndPos);
        }
    }

    /**
     * 根据指针位置读取下一页内容
     *
     * @return
     */
    private Vector<String> pageDown() {
        //Paragraph 段落
        String strParagraph = "";
        Vector<String> lines = new Vector<>();
        int paraSpace = 0;
        //总行数=文字总可见区域/字体大小和行间距
        mPageLineCount = mTextViewVisibleHeight / (mTextViewFontSize + mLineSpace);
        //当当前段落大小小于 总行数并且 最后位置小于字节长度
        while ((lines.size() < mPageLineCount) && (curEndPos < mBufferByteLen)) {
            //读取下一段落
            byte[] parabuffer = readParagraphForward(curEndPos);
            //更新最后位置
            curEndPos += parabuffer.length;
            try {
                //存储段落和编码
                strParagraph = new String(parabuffer, mCharset);
                // 段落中的换行符去掉，绘制的时候再换行
                strParagraph = strParagraph.replaceAll("\r\n", "  ").replaceAll("\n", " ");
                //当段落长度大于0时
                while (strParagraph.length() > 0) {
                    /**
                     * text表示我们的字符串；
                     * start表示从第几个字符串开始测量；
                     * end表示从测量到第几个字符串为止；
                     * measureForwards表示向前还是向后测量；
                     * maxWidth表示一个给定的最大宽度在这个宽度内能测量出几个字符；
                     * measuredWidth为一个可选项，可以为空，不为空时返回真实的测量值
                     * public int breakText (String text, boolean measureForwards, float maxWidth, float[] measuredWidth)
                     * public int breakText (char[] text, int index, int count, float maxWidth, float[] measuredWidth)
                     * public int breakText (CharSequence text, int start, int end, boolean measureForwards, float maxWidth, float[] measuredWidth)
                     */
                    int paintSize = mPaint.breakText(strParagraph, true, mTextViewVisibleWidth, null);
                    lines.add(strParagraph.substring(0, paintSize));
                    strParagraph = strParagraph.substring(paintSize);
                    if (lines.size() >= mPageLineCount) {
                        break;
                    }
                    lines.set(lines.size() - 1, lines.get(lines.size() - 1) + "@");
                    if (strParagraph.length() != 0) {
                        try {
                            curEndPos -= (strParagraph).getBytes(mCharset).length;
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    paraSpace += mLineSpace;
                    mPageLineCount = (mTextViewVisibleHeight - paraSpace) / (mTitleFontSize + mLineSpace);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        return lines;
    }

    /**
     * 读取下一段落
     *
     * @param curEndPos 当前页结束位置指针
     * @return
     */
    private byte[] readParagraphForward(int curEndPos) {
        byte b0;
        int i = curEndPos;
        while (i < mBufferByteLen) {
            b0 = mFileMapByteBuffer.get(i++);
            if (b0 == 0x0a) {
                break;
            }
        }
        int nParaSize = i - curEndPos;
        byte[] buf = new byte[nParaSize];
        for (i = 0; i < nParaSize; i++) {
            buf[i] = mFileMapByteBuffer.get(curEndPos + i);
        }
        return buf;
    }
    /**
     * 读取上一段落
     *
     * @param curBeginPos 当前页起始位置指针
     * @return
     */
    private byte[] readParagraphBack(int curBeginPos) {
        byte b0;
        int i = curBeginPos - 1;
        while (i > 0) {
            b0 = mFileMapByteBuffer.get(i);
            if (b0 == 0x0a && i != curBeginPos - 1) {
                i++;
                break;
            }
            i--;
        }
        int nParaSize = curBeginPos - i;
        byte[] buf = new byte[nParaSize];
        for (int j = 0; j < nParaSize; j++) {
            buf[j] = mFileMapByteBuffer.get(i + j);
        }
        return buf;
    }
    /**
     * 指针移到上一页页首
     */
    private void pageUp() {
        String strParagraph = "";
        Vector<String> lines = new Vector<>(); // 页面行
        int paraSpace = 0;
        mPageLineCount = mTextViewVisibleHeight / (mTextViewFontSize + mLineSpace);
        while ((lines.size() < mPageLineCount) && (curBeginPos > 0)) {
            Vector<String> paraLines = new Vector<>(); // 段落行
            byte[] parabuffer = readParagraphBack(curBeginPos); // 1.读取上一个段落

            curBeginPos -= parabuffer.length; // 2.变换起始位置指针
            try {
                strParagraph = new String(parabuffer, mCharset);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            strParagraph = strParagraph.replaceAll("\r\n", "  ");
            strParagraph = strParagraph.replaceAll("\n", " ");

            while (strParagraph.length() > 0) { // 3.逐行添加到lines
                int paintSize = mPaint.breakText(strParagraph, true, mTextViewVisibleWidth, null);
                paraLines.add(strParagraph.substring(0, paintSize));
                strParagraph = strParagraph.substring(paintSize);
            }
            lines.addAll(0, paraLines);

            while (lines.size() > mPageLineCount) { // 4.如果段落添加完，但是超出一页，则超出部分需删减
                try {
                    curBeginPos += lines.get(0).getBytes(mCharset).length; // 5.删减行数同时起始位置指针也要跟着偏移
                    lines.remove(0);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            curEndPos = curBeginPos; // 6.最后结束指针指向下一段的开始处
            paraSpace += mLineSpace;
            mPageLineCount = (mTextViewVisibleHeight - paraSpace) / (mTextViewFontSize + mLineSpace); // 添加段落间距，实时更新容纳行数
        }
    }
    /**
     * 获取最后一页的内容。比较繁琐，待优化
     *
     * @return
     */
    public Vector<String> pageLast() {
        String strParagraph = "";
        Vector<String> lines = new Vector<>();
        mCurrentPage = 0;
        while (curEndPos < mBufferByteLen) {
            int paraSpace = 0;
            mPageLineCount = mTextViewVisibleHeight / (mTextViewFontSize + mLineSpace);
            curBeginPos = curEndPos;
            while ((lines.size() < mPageLineCount) && (curEndPos < mBufferByteLen)) {
                byte[] parabuffer = readParagraphForward(curEndPos);
                curEndPos += parabuffer.length;
                try {
                    strParagraph = new String(parabuffer, mCharset);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                strParagraph = strParagraph.replaceAll("\r\n", "  ");
                strParagraph = strParagraph.replaceAll("\n", " "); // 段落中的换行符去掉，绘制的时候再换行

                while (strParagraph.length() > 0) {
                    int paintSize = mPaint.breakText(strParagraph, true, mTextViewVisibleWidth, null);
                    lines.add(strParagraph.substring(0, paintSize));
                    strParagraph = strParagraph.substring(paintSize);
                    if (lines.size() >= mPageLineCount) {
                        break;
                    }
                }
                lines.set(lines.size() - 1, lines.get(lines.size() - 1) + "@");

                if (strParagraph.length() != 0) {
                    try {
                        curEndPos -= (strParagraph).getBytes(mCharset).length;
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                paraSpace += mLineSpace;
                mPageLineCount = (mTextViewVisibleHeight - paraSpace) / (mTextViewFontSize + mLineSpace);
            }
            if (curEndPos < mBufferByteLen) {
                lines.clear();
            }
            mCurrentPage++;
        }
        //SettingManager.getInstance().saveReadProgress(bookId, currentChapter, curBeginPos, curEndPos);
        return lines;
    }

    public boolean hasNextPage() {
        return curChapter < mChaptersBeanList.size() || curEndPos < mBufferByteLen;
    }

    public boolean hasPrePage() {
        return curChapter > 1 || (curChapter == 1 && curBeginPos > 0);
    }

    /**
     * 跳转下一页
     */
    public BookStatus nextPage() {
        if (!hasNextPage()) { // 最后一章的结束页
            return BookStatus.NO_NEXT_PAGE;
        } else {
            tempChapter = curChapter;
            tempBeginPos = curBeginPos;
            tempEndPos = curEndPos;
            if (curEndPos >= mBufferByteLen) { // 中间章节结束页
                curChapter++;
                int ret = openBook(curChapter, new int[]{0, 0}); // 打开下一章
                if (ret == 0) {
                    onLoadChapterFailure(curChapter);
                    curChapter--;
                    curBeginPos = tempBeginPos;
                    curEndPos = tempEndPos;
                    return BookStatus.NEXT_CHAPTER_LOAD_FAILURE;
                } else {
                    mCurrentPage = 0;
                    onChapterChanged(curChapter);
                }
            } else {
                curBeginPos = curEndPos; // 起始指针移到结束位置
            }
            mLines.clear();
            mLines = pageDown(); // 读取一页内容
            onPageChanged(curChapter, ++mCurrentPage);
        }
        return BookStatus.LOAD_SUCCESS;
    }

    /**
     * 跳转上一页
     */
    public BookStatus prePage() {
        if (!hasPrePage()) { // 第一章第一页
            return BookStatus.NO_PRE_PAGE;
        } else {
            // 保存当前页的值
            tempChapter = curChapter;
            tempBeginPos = curBeginPos;
            tempEndPos = curEndPos;
            if (curBeginPos <= 0) {
                curChapter--;
                int ret = openBook(curChapter, new int[]{0, 0});
                if (ret == 0) {
                    onLoadChapterFailure(curChapter);
                    curChapter++;
                    return BookStatus.PRE_CHAPTER_LOAD_FAILURE;
                } else { // 跳转到上一章的最后一页
                    mLines.clear();
                    mLines = pageLast();
                    onChapterChanged(curChapter);
                    onPageChanged(curChapter, mCurrentPage);
                    return BookStatus.LOAD_SUCCESS;
                }
            }
            mLines.clear();
            pageUp(); // 起始指针移到上一页开始处
            mLines = pageDown(); // 读取一页内容
            onPageChanged(curChapter, --mCurrentPage);
        }
        return BookStatus.LOAD_SUCCESS;
    }

    public void cancelPage() {
        curChapter = tempChapter;
        curBeginPos = tempBeginPos;
        curEndPos = curBeginPos;

        int ret = openBook(curChapter, new int[]{curBeginPos, curEndPos});
        if (ret == 0) {
            onLoadChapterFailure(curChapter);
            return;
        }
        mLines.clear();
        mLines = pageDown();
    }

    /**
     * 获取当前阅读位置
     *
     * @return index 0：起始位置 1：结束位置
     */
    public int[] getPosition() {
        return new int[]{curChapter, curBeginPos, curEndPos};
    }

    public String getHeadLineStr() {
        if (mLines != null && mLines.size() > 1) {
            return mLines.get(0);
        }
        return "";
    }

    /**
     * 设置字体大小
     *
     * @param fontsize 单位：px
     */
    public void setTextFont(int fontsize) {
//        LogUtils.i("fontSize=" + fontsize);
        mTextViewFontSize = fontsize;
        mLineSpace = mTextViewFontSize / 5 * 2;
        mPaint.setTextSize(mTextViewFontSize);
        mPageLineCount = mTextViewVisibleHeight / (mTextViewFontSize + mLineSpace);
        curEndPos = curBeginPos;
        nextPage();
    }

    /**
     * 设置字体颜色
     *
     * @param textColor
     * @param titleColor
     */
    public void setTextColor(int textColor, int titleColor) {
        mPaint.setColor(textColor);
        mTitlePaint.setColor(titleColor);
    }

    public int getTextFont() {
        return mTextViewFontSize;
    }

    /**
     * 根据百分比，跳到目标位置
     *
     * @param persent
     */
    public void setPercent(int persent) {
        float a = (float) (mBufferByteLen * persent) / 100;
        curEndPos = (int) a;
        if (curEndPos == 0) {
            nextPage();
        } else {
            nextPage();
            prePage();
            nextPage();
        }
    }

    public void setBgBitmap(Bitmap BG) {
        mBookPageBg = BG;
    }
//    public void convertBetteryBitmap() {
//        batteryView = (ProgressBar) LayoutInflater.from(mContext).inflate(R.layout.layout_battery_progress, null);
//        batteryView.setProgressDrawable(ContextCompat.getDrawable(mContext,
//                SettingManager.getInstance().getReadTheme() < 4 ?
//                        R.drawable.seekbar_battery_bg : R.drawable.seekbar_battery_night_bg));
//        batteryView.setProgress(battery);
//        batteryView.setDrawingCacheEnabled(true);
//        batteryView.measure(View.MeasureSpec.makeMeasureSpec(ScreenUtils.dpToPxInt(26), View.MeasureSpec.EXACTLY),
//                View.MeasureSpec.makeMeasureSpec(ScreenUtils.dpToPxInt(14), View.MeasureSpec.EXACTLY));
//        batteryView.layout(0, 0, batteryView.getMeasuredWidth(), batteryView.getMeasuredHeight());
//        batteryView.buildDrawingCache();
//        //batteryBitmap = batteryView.getDrawingCache();
//        // tips: @link{https://github.com/JustWayward/BookReader/issues/109}
//        batteryBitmap = Bitmap.createBitmap(batteryView.getDrawingCache());
//        batteryView.setDrawingCacheEnabled(false);
//        batteryView.destroyDrawingCache();
//    }

//    public void setBattery(int battery) {
//        this.battery = battery;
//        convertBetteryBitmap();
//    }


    public void recycle() {
        if (mBookPageBg != null && !mBookPageBg.isRecycled()) {
            mBookPageBg.recycle();
            mBookPageBg = null;
//            LogUtils.d("mBookPageBg recycle");
        }

//        if (batteryBitmap != null && !batteryBitmap.isRecycled()) {
//            batteryBitmap.recycle();
//            batteryBitmap = null;
//            LogUtils.d("batteryBitmap recycle");
//        }
    }

    public void setOnReadStateChangeListener(OnReadStateChangeListener listener) {
        this.mChangeListener = listener;
    }

    private void onChapterChanged(int chapter) {
        if (mChangeListener != null)
            mChangeListener.onChapterChanged(chapter);
    }

    private void onPageChanged(int chapter, int page) {
        if (mChangeListener != null)
            mChangeListener.onPageChanged(chapter, page);
    }

    private void onLoadChapterFailure(int chapter) {
        if (mChangeListener != null)
            mChangeListener.onLoadChapterFailure(chapter);
    }
}
