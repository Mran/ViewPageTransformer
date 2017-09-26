package com.mran.cardviewpage;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

/**
 * Created by M on 2017/9/21.
 * 卡片绕卡片中心旋转
 */

public class CardFlipoverPageTransormer implements PageTransformer {

    @Override
    public void transformPage(View page, float position) {
        if (position <= -1) {
            /*页面已经在屏幕左侧且不可视*/
            /*设置离开的page不可点击,不可见*/
            page.setClickable(false);
            page.setAlpha(0);
        } else if (position <= 0) {
            page.setClickable(false);
                /*页面从左侧进入或者向左侧滑出的状态*/
                    /*把旋转中心改为中间*/
            page.setAlpha(1);
            if (position <= -0.5)
                        /*旋转到中间时该页page隐藏掉*/
                page.setAlpha(0);
            page.setPivotX(page.getWidth() / 2);
            page.setPivotY(page.getHeight() / 2);


            page.setTranslationX(position * -page.getWidth());
            page.setCameraDistance(10000);
            page.setRotationY(position * 180);
        } else if (position <= 1) {
                    /*页面从右侧进入或者向右侧滑出的状态*/
                    /*初始状态要是隐藏状态*/
            page.setAlpha(0);
            if (position <= 0.5)
                         /*旋转到中间时该页page显示出来*/
                page.setAlpha(1);
            page.setPivotX(page.getWidth() / 2);
            page.setPivotY(page.getHeight() / 2);
            page.setTranslationX(position * -page.getWidth());
            page.setCameraDistance(10000);
            page.setRotationY(-180 - (1 - position) * 180);
        } else if (position >= 1) {
                        /*页面已经在屏幕右侧且不可视*/
        }
    }
}
