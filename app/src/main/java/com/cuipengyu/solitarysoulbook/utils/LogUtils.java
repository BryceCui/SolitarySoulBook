package com.cuipengyu.solitarysoulbook.utils;

import android.util.Log;

import com.cuipengyu.solitarysoulbook.BuildConfig;

public class LogUtils {
    static String className;//类名
    static String methodName;//方法名
    static int lineNumber;//行数
    private static String mNewline = System.getProperty("line.separator");

    private LogUtils() {
        /* Protect from instantiations */
    }

    public static void e(String message) {
        if (!isDebuggable())
            return;
        // Throwable instance must be created before any methods
        getMethodNames(new Throwable().getStackTrace());
        Log.e(className, createLogInformation(message));
    }

    public static boolean isDebuggable() {
        return BuildConfig.DEBUG;
    }

    private static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }


    private static String createLogInformation(String log) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("|--日志所在位置--|");
        buffer.append(methodName);
        buffer.append("(")
                .append(className)
                .append(":")
                .append(lineNumber)
                .append(")");
        buffer.append(mNewline);
        buffer.append("|--日志输出信息--|")
                .append(mNewline)
                .append(log);
        return buffer.toString();
    }

    public static void i(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.i(className, createLogInformation(message));
    }

    public static void d(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.d(className, createLogInformation(message));
    }

    public static void v(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.v(className, createLogInformation(message));
    }

    public static void w(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.w(className, createLogInformation(message));
    }

    public static void wtf(String message) {
        if (!isDebuggable())
            return;

        getMethodNames(new Throwable().getStackTrace());
        Log.wtf(className, createLogInformation(message));
    }

}