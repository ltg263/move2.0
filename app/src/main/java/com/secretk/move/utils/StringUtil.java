package com.secretk.move.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件名：com.juanct.iwork99.util
 * 描    述：字符串处理类
 * 作    者：yujian
 * 时    间：2016/8/1 20:08
 * 版    权： 南海云
 */
public class StringUtil {
    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return ("".equals(str) || "null".equals(str) || str == null);
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * @describe: 验证手机号是否合法
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^13[0-9]{9}|14[57][0-9]{8}|17[0678][0-9]{8}|18[0-9]{9}|15[012356789][0-9]{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * @param idcard
     * @return
     * @describe: 验证身份证号
     * @author bixingfang 2013-9-20 下午5:34:03 boolean
     */
    public static boolean isIdcard(String idcard) {
        Pattern p = Pattern.compile("(^([0-9]{17})([0-9Xx]{1})$)|(^([0-9a-zA-Z]{8})\\-([0-9a-zA-Z]{1})$)");
        Matcher m = p.matcher(idcard);
        return m.matches();

    }

    /**
     * @param email
     * @return
     * @describe: 验证邮箱是否合法
     * @author yujian 2013-5-17 上午10:36:30 boolean
     */

    public static boolean isEmail(String email) {
        Pattern pattern = Pattern.compile(
                "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?");
        Matcher m = pattern.matcher(email);
        return m.matches();
    }

    /**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断字符串是否为数字
     */
    public static boolean hasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches())
            flag = true;
        return flag;
    }

    /**
     * 判断字符串是否为英文
     */
    public static boolean ischeckEnglish(String str) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9]+$");
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 判断字符串是否包含中文
     */
    public static boolean ischeckChinese(String str) {
        Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]+");
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 提取短信中的验证码6位
     */
    public static String getSmsNumber(String sms) {
        Pattern pattern = Pattern.compile("\\d{6}");
        Matcher matcher = pattern.matcher(sms);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }


    /**
     * 对包含中文的图片路径进行转码
     */
    public static String getChinese(String imgPath) {
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]+");
        Matcher matcher = pattern.matcher(imgPath);
        if (matcher.find()) {
            String s = imgPath.substring(0, imgPath.indexOf(matcher.group()));
            String p = URLEncoder.encode("个人头像");
            s = s + p + imgPath.substring(imgPath.indexOf(matcher.group()) + matcher.group().length());
            return s;
        }
        return imgPath;
    }

    /**
     * 判断是不是gif
     */
    public static String getUrl(String value) {
        String reg = "(?=http://)(?:[^.\\s]*?\\.)+(gif)";
        Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(value);
        boolean blnp = m.find();
        if (blnp == true) {
            return m.group(0);
        }
        return null;
    }

    /**
     * 描述: 取出空格、换行
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 描述: 将字符串转成毫秒数 格式年月日时分秒
     */
    public static String getMsToTime(String time) {
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time));
            return c.getTimeInMillis() + "";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 毫秒数转日期
     */
    public static String getTimeToMs(long seconds) {
        Date d = new Date(seconds);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(d).toString();
    }

    /**
     * 毫秒数转日期
     */
    public static String getTimeToMs1(long seconds) {
        Date d = new Date(seconds);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(d).toString();
    }

    /**
     * 毫秒数转日期
     */
    public static String getTimeToM(long seconds) {
        Date d = new Date(seconds);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(d).toString();
    }

    public static String format(String format, Object value) {
        return String.format(format, value);
    }

    /**
     * 获取assets前置目录
     */
    public static String getAssetsFilePath(String filePath) {

        return "file:///android_asset/web/" + filePath;
    }


    /**
     * 将list转化成数组
     *
     * @param strList
     * @return
     */
    public static String[] toStringArray(List<String> strList) {
        String[] array = new String[strList.size()];
        strList.toArray(array);
        return array;
    }

    /**
     * @return
     */
    public static long getDataLong(String data) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            return sdf.parse(data).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //汉字转拼音
   /* public static String getFirstLetter4Name(String value) {
        ArrayList<HanziToPinyin.Token> tokens = HanziToPinyin.getInstance().get(value);
        StringBuilder sb = new StringBuilder();
        if (tokens != null && tokens.size() > 0) {
            for (HanziToPinyin.Token token : tokens) {
                if (HanziToPinyin.Token.PINYIN == token.type) {
                    sb.append(token.target);
                } else {
                    sb.append(token.source);
                }
            }
        }
        return sb.toString();
    }
*/
    /**
     * 网络判断
     *
     * @param context
     * @return flag 11 111
     */
    public static boolean isNetworkAvailable(Context context) {
        boolean flag = false;
        ConnectivityManager localConnectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (localConnectivityManager != null) {
            try {
                NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
                flag = !((localNetworkInfo == null) || (!localNetworkInfo.isAvailable()));
            } catch (Exception e) {
                LogUtil.d("The network is not available" + e.getMessage());
                flag = false;
            }
        }
        return flag;
    }



    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
    /**
     * 监听输入框输的变化
     */
    public static void etSearchChangedListener(final EditText et, final Button btn) {
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0 && et.getText().toString().trim().length() != 0) {
                    btn.setSelected(true);
                }
                if (et.getText().toString().trim().length() == 0) {
                    btn.setSelected(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
