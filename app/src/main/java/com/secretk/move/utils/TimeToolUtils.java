package com.secretk.move.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.secretk.move.R;
import com.secretk.move.ui.activity.SubmitProjectActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeToolUtils {
    private final static long yearLevelValue = 365 * 24 * 60 * 60 * 1000;
    private final static long monthLevelValue = 30 * 24 * 60 * 60 * 1000;
    private final static long dayLevelValue = 24 * 60 * 60 * 1000;
    private final static long hourLevelValue = 60 * 60 * 1000;
    private final static long minuteLevelValue = 60 * 1000;
    private final static long secondLevelValue = 1000;

    public static String getDifference(long nowTime, long targetTime) {// 目标时间与当前时间差
        long period = targetTime - nowTime;
        return getDifference(period);
    }

    public static String getDifference(long period) {// 根据毫秒差计算时间差
        String result = null;
        /******* 计算出时间差中的年、月、日、天、时、分、秒 *******/
        int year = getYear(period);
        int month = getMonth(period - year * yearLevelValue);
        int day = getDay(period - year * yearLevelValue - month * monthLevelValue);
        int hour = getHour(period - year * yearLevelValue - month * monthLevelValue - day * dayLevelValue);
        int minute = getMinute(period - year * yearLevelValue - month * monthLevelValue - day * dayLevelValue - hour * hourLevelValue);
        int second = getSecond(period - year * yearLevelValue - month * monthLevelValue - day * dayLevelValue - hour * hourLevelValue - minute * minuteLevelValue);
        if (year == 0) {
            result = month + "月" + day + "天" + hour + "小时" + minute + "分" + second + "秒";
            if (month == 0) {
                result = day + "天" + hour + "小时" + minute + "分" + second + "秒";
                /*if(day==0){
                    result = hour + "时" + minute + "分" + second + "秒";
					if(hour==0){
						result = minute + "分" + second + "秒";
						if(minute==0){
							result = second + "秒";
							if(second==0){
								result="时间已到";
							}
						}
					}
				}*/
            }
        } else {
            result = year + "年" + month + "月" + day + "天" + hour + "小时" + minute + "分" + second + "秒";
        }
        return result;
    }

    public static int getYear(long period) {
        return (int) (period / yearLevelValue);
    }

    public static int getMonth(long period) {
        return (int) (period / monthLevelValue);
    }

    public static int getDay(long period) {
        return (int) (period / dayLevelValue);
    }

    public static int getHour(long period) {
        return (int) (period / hourLevelValue);
    }

    public static int getMinute(long period) {
        return (int) (period / minuteLevelValue);
    }

    public static int getSecond(long period) {
        return (int) (period / secondLevelValue);
    }

    /**
     * 将一个时间戳转换成提示性时间字符串，如刚刚，1秒前
     *  若是当天，则显示时间12:00，若是其他时间（当年），月/日；其他年份，年/月/日
     * @param
     * @return
     */
    public static String convertTimeToFormat(long timeStamp) {
        long curTime = System.currentTimeMillis() / 1000L;
        long time = curTime - timeStamp;
        if (time < 60 && time >= 0) {
            return "刚刚";
        } else if (time >= 60 && time < 3600) {
            return time / 60 + "分钟前";
        } else if (time >= 3600 && time < 3600 * 24) {
            return time / 3600 + "小时前";
        } else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {
            return time / 3600 / 24 + "天前";
        } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 + "个月前";
        } else if (time >= 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 / 12 + "年前";
        } else {
            return "刚刚";
        }
//        int year = getYear(period);
//        int month = getMonth(period - year * yearLevelValue);
//        int day = getDay(period - year * yearLevelValue - month * monthLevelValue);
//        int hour = getHour(period - year * yearLevelValue - month * monthLevelValue - day * dayLevelValue);
//        return
    }

    /**
     * 将一个时间戳转换成提示性时间字符串，如刚刚，1秒前
     * @param timeStamp
     * @return
     */
    public static String convertTimeToFormatA(long timeStamp) {
        long curTime = System.currentTimeMillis() / 1000L;
        long time = curTime - timeStamp;
        if (time < 60 && time >= 0) {
            return "刚刚";
        } else if (time >= 60 && time < 3600) {
            return time / 60 + "分钟前";
        } else if (time >= 3600 && time < 3600 * 24) {
            return time / 3600 + "小时前";
        } else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {
            return time / 3600 / 24 + "天前";
        } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 + "个月前";
        } else if (time >= 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 / 12 + "年前";
        } else {
            return "刚刚";
        }
    }

    /**
     * 将时间改成今天、昨天、前天
     */
    public static String getTimeType(long timeStamp, String pattern, boolean addTime) {
        try {
            String str = "";
            Calendar now = Calendar.getInstance();
            long ms = 1000 * (now.get(Calendar.HOUR_OF_DAY) * 3600 + now.get(Calendar.MINUTE) * 60 + now.get(Calendar.SECOND));// 毫秒数
            long ms_now = now.getTimeInMillis();
            String newTime = new SimpleDateFormat("HH:mm").format(new Date(timeStamp)).toString();
            if (ms_now - timeStamp < ms) {
                str = "今天 " + newTime;
            } else if (ms_now - timeStamp < (ms + 24 * 3600 * 1000)) {
                pattern = "HH:mm";
                String time = new SimpleDateFormat(pattern).format(new Date(timeStamp)).toString();
                str = addTime ? "昨天 " + time : "昨天";
                //+newTime;
            } /*else if (ms_now - timeStamp < (ms + 24 * 3600 * 1000 * 2)) {
                str = "前天";
				//+newTim;
			}*/ else {
                if (TextUtils.isEmpty(pattern))
                    pattern = "MM-dd";
                str = new SimpleDateFormat(pattern).format(new Date(timeStamp)).toString();
            }
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(timeStamp)).toString();
    }

    /**
     * 获取系统当前时间
     *
     * @param pattern
     * @return
     */
    public static String getTime(String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = new Date();
        return format.format(date);
    }

    /**
     * 毫秒转字符串时间
     *
     * @param time
     * @param pattern 格式
     * @return
     */
    public static String getMilltoTime(long time, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = new Date(time);
        return format.format(date);
    }

    /**
     * 字符串时间转毫秒
     *
     * @param time
     * @param pattern 格式
     * @return
     */
    public static long getTimetoMill(String time, String pattern) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(new SimpleDateFormat(pattern).parse(time));
            return c.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @TargetApi(Build.VERSION_CODES.M)
    public static void showTimeView(Context context, Type Type, long minTime, long currentTime, boolean isCycle, final OnTimeChangedListener onTimeChangedListener) {
        showTimeView(context, Type, minTime, 0, currentTime, isCycle, onTimeChangedListener);
    }
    public static void showTimeView(Context context, Type Type, long minTime, long maxTime, long currentTime, boolean isCycle, final OnTimeChangedListener onTimeChangedListener) {
        if (currentTime == 0) {
            currentTime = System.currentTimeMillis();
        }
        if (maxTime == 0) {
            long tenYears = 50L * 365 * 1000 * 60 * 60 * 24L;
//            maxTime = System.currentTimeMillis() + tenYears;
            maxTime = System.currentTimeMillis();//最大的时间为当前时间
        }
        TimePickerDialog mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(new OnDateSetListener() {
                    @Override
                    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                        onTimeChangedListener.onDateSet(timePickerView, millseconds);
                    }
                })
                .setCancelStringId(context.getString(R.string.no))
                .setSureStringId(context.getString(R.string.yes))
                .setTitleStringId(context.getString(R.string.select_time))
                .setCyclic(isCycle)
                .setMinMillseconds(minTime)
                .setMaxMillseconds(maxTime)
                .setCurrentMillseconds(currentTime)
                .setThemeColor(ContextCompat.getColor(context, R.color.app_background))
                .setType(Type)
                .setWheelItemTextNormalColor(ContextCompat.getColor(context, R.color.title_gray_aa))
                .setWheelItemTextSelectorColor(ContextCompat.getColor(context, R.color.app_background))
                .setWheelItemTextSize(14)
                .build();
        mDialogAll.show(((SubmitProjectActivity) context).getSupportFragmentManager(), Type.toString());
    }

    /**
     * 时间控件
     */
    public interface OnTimeChangedListener {
        void onDateSet(TimePickerDialog timePickerView, long millseconds);
    }

}