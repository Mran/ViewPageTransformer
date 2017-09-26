package com.mran.cardviewpage;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.mran.cardviewpage.R.drawable.e;
import static com.mran.cardviewpage.R.drawable.f;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;
    ViewPager mViewPager;
    int mCurrentPosition=0;
    ViewPager.PageTransformer mPageTransformer;
    Animation mAnimation;
    List<ImageView> mImageViewList = new ArrayList<>();
    int[] mDrawable = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, e, f, R.drawable.g, R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, e, f, R.drawable.g};
    int[] mColor = {R.color.a, R.color.b, R.color.c, R.color.d, R.color.e, R.color.f, R.color.g, R.color.a, R.color.b, R.color.c, R.color.d, R.color.e, R.color.f, R.color.g};
    float mFactor = 0.5f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textview);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setPageMargin(1);
        mViewPager.setOffscreenPageLimit(5);
        mAnimation = new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f, 150, 450);
        mAnimation.setDuration(500);
        mAnimation.setFillAfter(true);
        mAnimation.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float input) {
                return (float) (Math.pow(2, -10 * input) * Math.sin((input - mFactor / 4) * (2 * Math.PI) / mFactor) + 0.9);
            }
        });
        for (int i = 0; i < mDrawable.length; i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageResource(mColor[i]);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(100, 100));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mImageViewList.add(imageView);
        }
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mDrawable.length;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = mImageViewList.get(position);
                container.addView(imageView);
                return imageView;
            }


            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });


        /*卡片绕中心旋转*/
        mPageTransformer = new CardFlipoverPageTransormer();
          /*书籍翻页渐隐*/
        mPageTransformer = new BookFlippageFadePageTransormer();
        /*立方体旋转*/
        mPageTransformer = new CubesPageTransformer();
       /* 转盘旋转*/
        mPageTransformer = new TurntablePageTransformer();
       /* 翻页旋转*/
        mPageTransformer = new FilpPageRotationPageTransformer();
        /*单层叠缩放*/
        mPageTransformer = new CascadeZoomPageTransformer();
         /*折叠向上*/
        mPageTransformer = new DepthCardTransformer();
        /*滑动缩放*/
        mPageTransformer = new ZoominPagerTransFormer();

        /*卡片堆叠*/
        mPageTransformer = new CardStackPaegTransformer();

        mViewPager.setPageTransformer(true, mPageTransformer);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("MainActivity", "onPageScrolled: position" + position + " positionOffset" + positionOffset + " positionOffsetPixels" + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("MainActivity", "onPageSelected: position" + position);
                mImageViewList.get(mCurrentPosition ).clearAnimation();/*//把上一个page的动画取消掉*/
                mCurrentPosition = position;/*更新当前的page位置*/
                mImageViewList.get(mCurrentPosition).startAnimation(mAnimation);/*开始动画*/

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("MainActivity", "onPageScrollStateChanged: state" + state);

            }
        });
    }
}
