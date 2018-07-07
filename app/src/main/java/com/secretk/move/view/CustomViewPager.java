package com.secretk.move.view;

import android.content.Context;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.utils.LogUtil;

import java.util.ArrayList;

/**
 * 文件名：com.hn.ssc.view
 * 描    述：自定义viewager
 * 作    者：yujian
 * 时    间：2016/5/17 15:19
 * 版    权： 南海云
 */
public class CustomViewPager extends LinearLayout {
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 图片轮播视图
     */
    private ViewPager mAdvPager = null;
    /**
     * 滚动图片视图适配
     */
    private ImageCycleAdapter mAdvAdapter;
    /**
     * 图片轮播指示器控件
     */
    private ViewGroup mGroup;

    /**
     * 图片轮播指示个图
     */
    private ImageView mImageView = null;

    /**
     * 滚动图片指示视图列表
     */
    private ImageView[] mImageViews = null;

    /**
     * 图片滚动当前图片下标
     */
    private int mImageIndex = 0;

    /**
     * 手机密度
     */
    private float mScale;
    /**
     * 停留时间
     */
    private int stayTime = 5000;

    private boolean isStop;
    private TextView[] mTextViews;
    private TextView mTextView;
    ArrayList<String> imageNameList;

    /**
     * @param context
     */
    public CustomViewPager(Context context) {
        super(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mScale = context.getResources().getDisplayMetrics().density;
        LayoutInflater.from(context).inflate(R.layout.custom_viewpager, this);
        mAdvPager = (ViewPager) findViewById(R.id.adv_pager);
//        mAdvPager.setOnPageChangeListener(new GuidePageChangeListener());
        mAdvPager.addOnPageChangeListener(new GuidePageChangeListener());
        mAdvPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        // 开始图片滚动
                        startImageTimerTask();
                        break;
                    default:
                        // 停止图片滚动
                        stopImageTimerTask();
                        break;
                }
                return false;
            }
        });
        // 滚动图片右下指示器视
        mGroup = (ViewGroup) findViewById(R.id.viewGroup);

    }

    /**
     * 装填图片数据
     *
     * @param imageUrlList
     * @param imageCycleViewListener
     */
    public void setImageResources(ArrayList<String> imageUrlList, ImageCycleViewListener imageCycleViewListener) {

        // 清除
        mGroup.removeAllViews();
        // 图片广告数量
        final int imageCount = imageUrlList.size();
        mImageViews = new ImageView[imageCount];
        for (int i = 0; i < imageCount; i++) {
            mImageView = new ImageView(mContext);
            int imageParams = (int) (mScale * 8 + 0.5f);// XP与DP转换，适应应不同分辨率
            int imagePadding = (int) (mScale * 5 + 0.5f);
            LayoutParams params = new LayoutParams(imageParams, imageParams);
            params.leftMargin  =30;
//            mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mImageView.setLayoutParams(params);
            mImageView.setPadding(imagePadding, imagePadding, imagePadding, imagePadding);
            mImageViews[i] = mImageView;
            if (i == 0) {
                mImageViews[i].setBackgroundResource(R.drawable.bule_dot);
            } else {
                mImageViews[i].setBackground(ContextCompat.getDrawable(mContext, R.drawable.white_dot));
                mImageViews[i].getBackground().setAlpha(50);
//                mImageViews[i].setBackgroundResource(R.drawable.banner_dian_blur);
            }
            mGroup.addView(mImageViews[i]);
        }

        mAdvAdapter = new ImageCycleAdapter(mContext, imageUrlList, imageNameList, imageCycleViewListener);
        mAdvPager.setAdapter(mAdvAdapter);
        startImageTimerTask();
    }

    /**
     * 图片轮播(手动控制自动轮播与否，便于资源控件）
     */
    public void startImageCycle() {
        startImageTimerTask();
    }

    /**
     * 暂停轮播—用于节省资源
     */
    public void pushImageCycle() {
        stopImageTimerTask();
    }

    /**
     * 设置停留的时间
     *
     * @param stayTime
     */
    public void setStayTime(int stayTime) {
        this.stayTime = stayTime;
    }

    /**
     * 图片滚动任务
     */
    private void startImageTimerTask() {
        stopImageTimerTask();
        // 图片滚动
        mHandler.postDelayed(mImageTimerTask, stayTime);
    }

    /**
     * 停止图片滚动任务
     */
    private void stopImageTimerTask() {
        isStop = true;
        mHandler.removeCallbacks(mImageTimerTask);
    }

    private Handler mHandler = new Handler();

    /**
     * 图片自动轮播Task
     */
    private Runnable mImageTimerTask = new Runnable() {
        @Override
        public void run() {
            if (mImageViews != null) {
                mAdvPager.setCurrentItem(mAdvPager.getCurrentItem() + 1);
                if (!isStop) {  //if  isStop=true   //当你退出后 要把这个给停下来 不然 这个一直存在 就一直在后台循环
                    mHandler.postDelayed(mImageTimerTask, 2000);
                }

            }
        }
    };

    /**
     * 轮播图片监听
     *
     * @author minking
     */
    private final class GuidePageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE)
                startImageTimerTask();
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int index) {
            index = index % mImageViews.length;
            // 设置当前显示的图片
            mImageIndex = index;
            // 设置图片滚动指示器背
//            mImageViews[index].setBackgroundResource(R.drawable.banner_dian_focus);
            mImageViews[index].setBackgroundResource(R.drawable.bule_dot);

            for (int i = 0; i < mImageViews.length; i++) {
                if (index != i) {
                    mImageViews[i].setBackground(ContextCompat.getDrawable(mContext, R.drawable.white_dot));
                    mImageViews[i].getBackground().setAlpha(50);
//                    mImageViews[i].setBackgroundResource(R.drawable.banner_dian_blur);
                }
            }
        }
    }

    public int getCurPos() {
        return mAdvPager.getCurrentItem() % mImageViews.length;
    }

    private int curPos = 0;

    private class ImageCycleAdapter extends PagerAdapter {

        /**
         * 图片视图缓存列表
         */
        private ArrayList<ImageView> mImageViewCacheList;

        /**
         * 图片资源列表
         */
        private ArrayList<String> mAdList = new ArrayList<>();
        private ArrayList<String> nameList = new ArrayList<>();

        /**
         * 广告图片点击监听
         */
        private ImageCycleViewListener mImageCycleViewListener;

        private Context mContext;

        public ImageCycleAdapter(Context context, ArrayList<String> adList, ArrayList<String> nameList, ImageCycleViewListener imageCycleViewListener) {
            this.mContext = context;
            this.mAdList = adList;
            this.nameList = nameList;
            mImageCycleViewListener = imageCycleViewListener;
            mImageViewCacheList = new ArrayList<>();
        }

        @Override
        public int getCount() {
//			return mAdList.size();
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            curPos = position % mAdList.size();
            String imageUrl = mAdList.get(curPos);
            LogUtil.w("position:"+position+" curPos:"+curPos);
            RoundImageView imageView = null;
            if (mImageViewCacheList.isEmpty()) {
                imageView = new RoundImageView(mContext);
                imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                // 设置图片点击监听
                imageView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mImageCycleViewListener.onImageClick(curPos, v);
                    }
                });
            } else {
                imageView = (RoundImageView) mImageViewCacheList.remove(0);
            }
            imageView.setTag(imageUrl);
            container.addView(imageView);
            mImageCycleViewListener.displayImage(imageUrl, imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ImageView view = (ImageView) object;
            mAdvPager.removeView(view);
            mImageViewCacheList.add(view);

        }

    }

    /**
     * 轮播控件的监听事件
     *
     * @author minking
     */
    public static interface ImageCycleViewListener {
        /**
         * 加载图片资源
         *
         * @param imageURL
         * @param imageView
         */
        public void displayImage(String imageURL, ImageView imageView);

        /**
         * 单击图片事件
         *
         * @param position
         * @param imageView
         */
        public void onImageClick(int position, View imageView);
    }


}
