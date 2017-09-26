package com.mran.cardviewpage;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by M on 2017/9/21.
 */

public class BookFlippageFadePageTransormer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {
        if (position <= -1) {
            /*页面已经在屏幕左侧且不可视*/
            page.setAlpha(position);
        } else if (position <= 0) {
             /*页面从左侧进入或者向左侧滑出的状态*/
            page.setAlpha(1 + position);
            page.setPivotY(page.getHeight() / 2);
            page.setPivotX(0);
            page.setCameraDistance(60000);/*调整摄像机的位置，避免出现糊脸的感觉*/
            page.setRotationY((position * 180));
            page.setTranslationX(position * -page.getWidth());
        } else if (position <= 1) {
/*页面从右侧进入或者向右侧滑出的状态*/
            page.setTranslationX(position * -page.getWidth());

        } else if (position > 1) {

/*页面已经在屏幕右侧且不可视*/
            page.setTranslationX(position * -page.getWidth());

        }
    }
}
