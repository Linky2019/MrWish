
package com.baselibrary.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * © 2012 amsoft.cn 名称：AbLogUtil.java 描述：日志工具类.
 */
public class AbLogUtil {

    /**
     * debug开关.
     */
    public static boolean D = true;

    /**
     * info开关.
     */
    public static boolean I = true;

    /**
     * error开关.
     */
    public static boolean E = true;

    /**
     * 起始执行时间.
     */
    public static long startLogTimeInMillis = 0;

    private static File folder = new File("/sdcard/homemonitor/log");

    private static File log = new File(folder.getPath() + "//" + "Log.txt");

    private static String MYLOG_PATH_SDCARD_DIR = "/sdcard/";// 日志文件在sdcard中的路径

    private static int SDCARD_LOG_FILE_SAVE_DAYS = 10;// sd卡中日志文件的最多保存天数

    private static String MYLOGFILEName = "Log.txt";// 本类输出的日志文件名称

    private static SimpleDateFormat myLogSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 日志的输出格式

    private static SimpleDateFormat logfile = new SimpleDateFormat("yyyy-MM-dd");// 日志文件格式

    /**
     * debug日志
     *
     * @param message
     */
    public static void d(String message) {
        Log.d("AbLogUtil", message);
        //  writeLogtoFile(tag,message);
    }

    public static void d(String tag, String message) {
        Log.d(tag, message);
        //  writeLogtoFile(tag,message);
    }

    /**
     * debug日志
     *
     * @param context
     * @param message
     */
    public static void d(Context context, String message) {
        String tag = context.getClass().getSimpleName();
        d(tag, message);
    }

    /**
     * debug日志
     *
     * @param clazz
     * @param message
     */
    public static void d(Class<?> clazz, String message) {
        String tag = clazz.getSimpleName();
        d(tag, message);
    }

    /**
     * debug日志
     *
     * @param context
     * @param format
     * @param args
     */
    public static void d(Context context, String format, Object... args) {
        String tag = context.getClass().getSimpleName();
        d(tag, buildMessage(format, args));
    }

    /**
     * debug日志
     *
     * @param clazz
     * @param format
     * @param args
     */
    public static void d(Class<?> clazz, String format, Object... args) {
        String tag = clazz.getSimpleName();
        d(tag, buildMessage(format, args));
    }

    /**
     * info日志
     *
     * @param tag
     * @param message
     */
    public static void i(String tag, String message) {
        Log.i(tag, message);
    }

    /**
     * info日志
     *
     * @param context
     * @param message
     */
    public static void i(Context context, String message) {
        String tag = context.getClass().getSimpleName();
        i(tag, message);
    }

    /**
     * info日志
     *
     * @param clazz
     * @param message
     */
    public static void i(Class<?> clazz, String message) {
        String tag = clazz.getSimpleName();
        i(tag, message);
    }

    /**
     * info日志
     *
     * @param context
     * @param format
     * @param args
     */
    public static void i(Context context, String format, Object... args) {
        String tag = context.getClass().getSimpleName();
        i(tag, buildMessage(format, args));
    }

    /**
     * info日志
     *
     * @param clazz
     * @param format
     * @param args
     */
    public static void i(Class<?> clazz, String format, Object... args) {
        String tag = clazz.getSimpleName();
        i(tag, buildMessage(format, args));
    }

    /**
     * error日志
     *
     * @param message
     */
    public static void e(String message) {
        Log.e("AbLogUtil", message);
    }

    public static void e(String tag, String message) {
        Log.e(tag, message);
    }

    /**
     * error日志
     *
     * @param context
     * @param message
     */
    public static void e(Context context, String message) {
        String tag = context.getClass().getSimpleName();
        e(message);
    }

    /**
     * error日志
     *
     * @param clazz
     * @param message
     */
    public static void e(Class<?> clazz, String message) {
        String tag = clazz.getSimpleName();
        e(message);
    }

    /**
     * error日志
     *
     * @param context
     * @param format
     * @param args
     */
    public static void e(Context context, String format, Object... args) {
        String tag = context.getClass().getSimpleName();
        e(buildMessage(format, args));
    }

    /**
     * error日志
     *
     * @param clazz
     * @param format
     * @param args
     */
    public static void e(Class<?> clazz, String format, Object... args) {
        String tag = clazz.getSimpleName();
        e(buildMessage(format, args));
    }

    /**
     * 描述：记录当前时间毫秒.
     */
    public static void prepareLog(String tag) {
        Calendar current = Calendar.getInstance();
        startLogTimeInMillis = current.getTimeInMillis();
        Log.d(tag, "日志计时开始：" + startLogTimeInMillis);
    }

    /**
     * 描述：记录当前时间毫秒.
     */
    public static void prepareLog(Context context) {
        String tag = context.getClass().getSimpleName();
        prepareLog(tag);
    }

    /**
     * 描述：记录当前时间毫秒.
     */
    public static void prepareLog(Class<?> clazz) {
        String tag = clazz.getSimpleName();
        prepareLog(tag);
    }

    /**
     * 描述：打印这次的执行时间毫秒，需要首先调用prepareLog().
     *
     * @param tag       标记
     * @param message   描述
     * @param printTime 是否打印时间
     */
    public static void d(String tag, String message, boolean printTime) {
        Calendar current = Calendar.getInstance();
        long endLogTimeInMillis = current.getTimeInMillis();
        Log.d(tag, message + ":" + (endLogTimeInMillis - startLogTimeInMillis) + "ms");
    }

    /**
     * 描述：打印这次的执行时间毫秒，需要首先调用prepareLog().
     *
     * @param message   描述
     * @param printTime 是否打印时间
     */
    public static void d(Context context, String message, boolean printTime) {
        String tag = context.getClass().getSimpleName();
        d(tag, message, printTime);
    }

    /**
     * 描述：打印这次的执行时间毫秒，需要首先调用prepareLog().
     *
     * @param clazz     标记
     * @param message   描述
     * @param printTime 是否打印时间
     */
    public static void d(Class<?> clazz, String message, boolean printTime) {
        String tag = clazz.getSimpleName();
        d(tag, message, printTime);
    }

    /**
     * debug日志的开关
     *
     * @param d
     */
    public static void debug(boolean d) {
        D = d;
    }

    /**
     * info日志的开关
     *
     * @param i
     */
    public static void info(boolean i) {
        I = i;
    }

    /**
     * error日志的开关
     *
     * @param e
     */
    public static void error(boolean e) {
        E = e;
    }

    /**
     * 设置日志的开关
     *
     * @param e
     */
    public static void setVerbose(boolean d, boolean i, boolean e) {
        D = d;
        I = i;
        E = e;
    }

    /**
     * 打开所有日志，默认全打开
     */
    public static void openAll() {
        D = true;
        I = true;
        E = true;
    }

    /**
     * 关闭所有日志
     *
     * @param d
     */
    public static void closeAll() {
        D = false;
        I = false;
        E = false;
    }

    /**
     * format日志
     *
     * @param format
     * @param args
     * @return
     */
    private static String buildMessage(String format, Object... args) {
        String msg = (args == null) ? format : String.format(Locale.US, format, args);
        StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
        String caller = "<unknown>";
        for (int i = 2; i < trace.length; i++) {
            Class<?> clazz = trace[i].getClass();
            if (!clazz.equals(AbLogUtil.class)) {
                String callingClass = trace[i].getClassName();
                callingClass = callingClass.substring(callingClass.lastIndexOf('.') + 1);
                callingClass = callingClass.substring(callingClass.lastIndexOf('$') + 1);
                caller = callingClass + "." + trace[i].getMethodName();
                break;
            }
        }
        return String.format(Locale.US, "[%d] %s: %s",
                Thread.currentThread().getId(), caller, msg);
    }

    /**
     * 打开日志文件并写入日志
     *
     * @return
     **/
    private static void writeLogtoFile(String tag, String text) {// 新建或打开日志文件

        delFile();
        Date nowtime = new Date();
        String needWriteFiel = logfile.format(nowtime);
        String needWriteMessage = myLogSdf.format(nowtime) + "    " + "    " + tag + "    " + text;
        File file = new File(MYLOG_PATH_SDCARD_DIR, needWriteFiel + MYLOGFILEName);
        try {
            FileWriter filerWriter = new FileWriter(file, true);// 后面这个参数代表是不是要接上文件中原来的数据，不进行覆盖
            BufferedWriter bufWriter = new BufferedWriter(filerWriter);
            bufWriter.write(needWriteMessage);
            bufWriter.newLine();
            bufWriter.close();
            filerWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 删除制定的日志文件
     */
    public static void delFile() {// 删除日志文件
        String needDelFiel = logfile.format(getDateBefore());
        File file = new File(MYLOG_PATH_SDCARD_DIR, needDelFiel + MYLOGFILEName);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 得到现在时间前的几天日期，用来得到需要删除的日志文件名
     */
    private static Date getDateBefore() {
        Date nowtime = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(nowtime);
        now.set(Calendar.DATE, now.get(Calendar.DATE)
                - SDCARD_LOG_FILE_SAVE_DAYS);
        return now.getTime();
    }

}
