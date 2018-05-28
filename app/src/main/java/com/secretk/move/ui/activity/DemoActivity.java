package com.secretk.move.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.view.AppBarHeadView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by zc on 2018/4/23.
 */

public class DemoActivity extends BaseActivity {
    Intent intent;
    String html = "\"<h1><a href=\" \">染sdfsdf）</a></h1><p><img src=\"http://t11.baidu.com/it/u=3630895579,2620611754&amp;" +
            "fm=173&amp;s=58203D7215605B011DD1A5CE0000E0A3&amp;w=605&amp;h=259&amp;img.JPEG\">" +
            "</p><p style=\"text-align: justify;\">" +
            "<a href=\"https://github.com/mycolorway/simditor/releases\" rel=\"nofollow\">" +
            "https://github.com/mycolorway/simditor/releases</a></p><p style=\"text-align: justify;\">" +
            "在以上地址下载zip文件，你也可以使用npm和bower安装Simditor：</p><p style=\"text-align: justify;\">" +
            "npm install simditor</p><p style=\"text-align: justify;\">bower install simditor</p><p style=\"text-align: justify;\">" +
            "在你需要使用simditor的页面中引入以下文件</p><p style=\"text-align: justify;\">Simditor基于jQuery和module.js</p>" +
            "<p style=\"text-align: justify;\">hotkeys.js是用来绑定快捷键的</p><p style=\"text-align: justify;\">" +
            "uploader.js是用来上传文件的，如果你不需要上传可以不引入</p><p><img width=\"500px\" " +
            "src=\"http://t10.baidu.com/it/u=2527874913,1332299608&amp;fm=173&amp;s=8752E5328BE04D2214F489CA030080B2&amp;" +
            "w=500&amp;h=129&amp;img.JPEG\"></p><p style=\"text-align: justify;\">在页面中创建一个textarea元素，" +
            "并设置id属性。</p><p><img width=\"500px\" src=\"http://t12.baidu.com/it/u=4154786144,3813079523&amp;fm=173" +
            "&amp;w=500&amp;h=26&amp;img.JPEG\"></p><p style=\"text-align: justify;\">运行<span style=\"color:red\">以下脚本代码</span>，一个简单的实例就完成啦。" +
            "</p><p style=\"text-align: justify;\">textarea属性是必须的，值可以是jQuery对象、" +
            "HTML元素或者选择器</p><p><img width=\"500px\" src=\"http://t11.baidu.com/it/u=3094095583,2224466738&amp;" +
            "fm=173&amp;s=8150E0334B23672414D080DA0000C0B3&amp;w=500&amp;h=78&amp;img.JPEG\"></p><p>" +
            "<img src=\"http://t11.baidu.com/it/u=2787633514,1772497155&amp;fm=173&amp;s=092875330B6265205A7D85DA0000C0B2&amp;" +
            "w=640&amp;h=253&amp;img.JPEG\"></p><p style=\"text-align: justify;\">一些可选的选项：</p><p " +
            "style=\"text-align: justify;\">placeholder simditor的占位符。使用占位符textarea默认的属性值。" +
            "</p><p style=\"text-align: justify;\">toolbar 显示工具栏按钮</p><p style=\"text-align: justify;\">" +
            "toolbarFloat 滚动时固定工具栏在浏览器的顶部</p><p style=\"text-align: justify;\">toolbarHidden 隐藏工具栏</p>" +
            "<p style=\"text-align: justify;\">defaultImage 默认图像占位符。在Simditor插入图片时使用。</p>" +
            "<p style=\"text-align: justify;\">tabIndent 使用“tab”键进行缩进</p><p style=\"text-align: justify;\">pasteImage" +
            " 支持从剪贴板粘贴图片上传。只支持Firefox和Chrome。</p><p style=\"text-align: justify;\"><br></p><ul><li><br></li></ul>\"";
    private TextView tv;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_demo;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        tv = findViewById(R.id.tv);

        NetworkImageGetter imageGetter = new NetworkImageGetter();
        Spanned spanned = Html.fromHtml(html, imageGetter, null);
//        Spanned spanned = Html.fromHtml(html);
        // 图片文字居中显示
//        tv.setGravity(Gravity.CENTER_HORIZONTAL);
        tv.setText(spanned);



    }

    @Override
    protected void initData() {

    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        return null;
    }

    public void main(View view){
        intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void http(View view){
        intent=new Intent(this,HttpActivity.class);
        startActivity(intent);
    }
    public void addlabel(View view){
        intent=new Intent(this,AddLabelActivity.class);
        startActivity(intent);
    }
    private final class NetworkImageGetter implements Html.ImageGetter {

        @Override
        public Drawable getDrawable(String source) {
            // TODO Auto-generated method stub

            LevelListDrawable d = new LevelListDrawable();
            new LoadImage().execute(source, d);
            return d;
        }

    }
    /**** 异步加载图片 **/
    private final class LoadImage extends AsyncTask<Object, Void, Bitmap> {

        private LevelListDrawable mDrawable;

        @Override
        protected Bitmap doInBackground(Object... params) {
            String source = (String) params[0];
            mDrawable = (LevelListDrawable) params[1];

            try {
                InputStream is = new URL(source).openStream();
                return BitmapFactory.decodeStream(is);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {

            if (bitmap != null) {
                BitmapDrawable d = new BitmapDrawable(bitmap);
                mDrawable.addLevel(1, 1, d);
                mDrawable
                        .setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                // mDrawable.setBounds(0, 0,
                // getWindowManager().getDefaultDisplay().getWidth(),
                // bitmap.getHeight());
                mDrawable.setLevel(1);
                CharSequence t = tv.getText();
                tv.setText(t);
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);


        return true;
    }
}
