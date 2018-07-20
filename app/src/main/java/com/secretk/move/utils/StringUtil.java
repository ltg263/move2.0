package com.secretk.move.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;

import com.secretk.move.MoveApplication;
import com.secretk.move.R;
import com.secretk.move.baseManager.BaseManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
     * 判断字符串是否不为空
     *
     * @param str
     * @return
     */
    public static String getBeanString(String str) {
        if(isNotBlank(str)){
            return str;
        }
        return "";
    }


    /**
     * 判断是否是手机号
     *
     * @param mobile
     * @return
     */
    public static boolean isMobileNO(String mobile) {
        if(mobile.length()==11){
            return true;
        }
        return false;
    }
    /**
     * 判断是否是手机号
     *
     * @param mobile
     * @return
     */
    public static boolean isMobileNO_(String mobile) {
        String regex = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,1,3,5-8])|(18[0-9])|166|198|199|(147))\\\\d{8}$";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(mobile);
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
    public static String getTimeToHm(long seconds) {
        Date d = new Date(seconds);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(d).toString();
    }

    /**
     * 毫秒数转日期
     */
    public static String getTimeToMs1(long seconds) {
        Date d = new Date(seconds);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
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
    /**
     * 毫秒数转日期
     */
    public static String getTimeToE(long seconds) {
        Date d = new Date(seconds);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  EEEE");
        return sdf.format(d).toString();
    }
    /**
     * 毫秒数转日期
     */
    public static String getTimeToEhm(long seconds) {
        Date d = new Date(seconds);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  EEEE HH:mm");
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
    public static void etSearchChangedListener(final EditText et, final View btn, final EtChange etChange) {
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0 && et.getText().toString().trim().length() != 0) {
                    etChange.etYes();
                }
                if (et.getText().toString().trim().length() == 0) {
                    if(btn!=null){
                        btn.setSelected(false);
                    }
                    etChange.etNo();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
    public abstract static class EtChange{
        //有内容
         public abstract void etYes();
         //无内容
         public void etNo(){}
    }
    public static void getVpPosition(ViewPager vp, final VpPageSelected vps){
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
            @Override
            public void onPageSelected(int position) {
                vps.getVpPageSelected(position);
            }
        });
    }
    public abstract static class VpPageSelected{
        public abstract void getVpPageSelected(int position);
    }
    public static String getMimeType(String fileName) {
        String result = "";
        int extPos = fileName.lastIndexOf(".");
        if(extPos != -1) {
            String ext = fileName.substring(extPos + 1);
            result = MimeTypeMap.getSingleton().getMimeTypeFromExtension(ext);
        }
        if(TextUtils.isEmpty(result)){
            result = "application/octet-stream";
        }
        return result;
    }

    /**
     * 文件后缀
     * @param fileName
     * @return
     * {"imgUrl":"/upload/posts/201805/ztFpnYSlKj.jpg"}
     */
    public static String getFileSuffix(String fileName) {
        String result = "";
        int extPos = fileName.lastIndexOf(".");
        if(extPos != -1) {
            return fileName.substring(extPos + 1);
        }
        return result;
    }
    /**
     * 将html文本内容中包含img标签的图片，宽度变为屏幕宽度，高度根据宽度比例自适应
     **/
    public static String getNewContent(String htmltext){
        try {
            if(!htmltext.contains("&nbsp;") && htmltext.contains("&nbsp")) {
                htmltext = htmltext.replaceAll("&nbsp", "&nbsp;");
            }
            Document doc= Jsoup.parse(htmltext);
            Elements elements=doc.getElementsByTag("img");
            for (Element element : elements) {
                element.attr("width","100%").attr("height","auto");
               String str =  element.attr("src");
               if(str.contains("image/png;base64")){
                   element.attr("src","");
               }
            }
            return doc.toString();
        } catch (Exception e) {
            return htmltext;
        }
    }
    /**
     * EditText获取焦点并显示软键盘
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        //其中editText为dialog中的输入框的 EditText
        if(editText!=null){
            //设置可获得焦点
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            //请求获得焦点
            editText.requestFocus();
            //调用系统输入法
            InputMethodManager inputManager = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(editText, 0);
            editText.setSelection(editText.getText().toString().length());
        }
    }
    public static String getCurrentClassName(Activity activity){
        return activity.getComponentName().getClassName();
    }

    /**
     * 加V
     *  //用户类型，数字，用户类型:
     *  1-普通用户；
     *  2-项目方；
     *  3-评测媒体；4-机构
     * @return
     */
    public static String getUserType(int userType, ImageView ivModelType){
        String ut="";
        switch (userType){
            case 1:
                ut="普通用户";
                break;
            case 2:
                if(ivModelType!=null){
//                    ivModelType.setVisibility(View.VISIBLE);
                    GlideUtils.loadUrlDd(MoveApplication.getContext(),ivModelType, R.drawable.ic_model_type_xm);
                }
                ut="项目方";
                break;
            case 3:
                if(ivModelType!=null){
//                    ivModelType.setVisibility(View.VISIBLE);
                    GlideUtils.loadUrlDd(MoveApplication.getContext(),ivModelType, R.drawable.ic_model_type_mt);
                }
                ut="评测媒体";
                break;
            case 4:
                if(ivModelType!=null){
//                    ivModelType.setVisibility(View.VISIBLE);
                    GlideUtils.loadUrlDd(MoveApplication.getContext(),ivModelType, R.drawable.ic_model_type_jg);
                }
                ut="机构用户";
                break;
        }
        return ut;
    }
    /**
     * 用户等级
     * 1-普通用户；
     * 2-高级用户;
     * 3-VIP
     * @return
     */
    public static String getUserDegree(int userType){
        String ut="";
        switch (userType){
            case 1:
                ut="普通用户";
                break;
            case 2:
                ut="高级用户";
                break;
            case 3:
                ut="VIP";
                break;
        }
        return ut;
    }

    /**
     * 获取软件版本号
     *
     * @return
     */
    public static String getVersionCode() {
        try {
            return BaseManager.app.getPackageManager().getPackageInfo(
                    BaseManager.app.getPackageName(), 0).versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getStateValueStr(float score){
        if(score>=9.5){
            return "AAA";
        }else if(score>=9){
            return "AA";
        }else if(score>=8.5){
            return "A";
        }else if(score>=8){
            return "BBB";
        }else if(score>=7){
            return "BB";
        }else if(score>=6){
            return "B";
        }else if(score>=5){
            return "CCC";
        }else if(score>=4){
            return "CC";
        }else if(score>=3){
            return "C";
        }else{
          return "D";
        }
    }
    //判断某一个类是否存在任务栈里面
    public static boolean isExistMainActivity(Context mContext ,Class<?> activity){
        Intent intent = new Intent(mContext, activity);
        ComponentName cmpName = intent.resolveActivity(mContext.getPackageManager());
        boolean flag = false;
        if (cmpName != null) { // 说明系统中存在这个activity
            ActivityManager am = (ActivityManager) mContext.getSystemService(mContext.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskInfoList = am.getRunningTasks(10);  //获取从栈顶开始往下查找的10个activity
            for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
                if (taskInfo.baseActivity.equals(cmpName)) { // 说明它已经启动了
                    flag = true;
                    break;  //跳出循环，优化效率
                }
            }
        }
        return flag;
    }
}
