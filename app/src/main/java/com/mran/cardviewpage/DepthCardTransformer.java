package com.mran.cardviewpage;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by M on 2017/9/21.
 * 这个要配和在ViewPager里设置android:clipChildren="false"
 * android:margin="xxdp"
 * 在ViewPager的外层里设置android:clipChildren="false"
 */

public class  DepthCardTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {
        if (position < -1) { /* [-Infinity,-1)*/
        /*页面已经在屏幕左侧第一个*/
            page.setCameraDistance(10000);
            page.setPivotX(page.getWidth()/2);
            page.setPivotY(page.getWidth());
            page.setRotationY(20);
        } else if (position <= 0) { /* [-1,0]*/
            /*页面从左侧进入或者向左侧滑出的状态*/

            page.setCameraDistance(10000);
            page.setPivotX(page.getWidth()/2);
            page.setPivotY(page.getWidth());
            page.setRotationY(-20+(1-position)*20);
        } else if (position <= 1) {/* (0,1]*/
            /*页面从右侧进入或者向右侧滑出的状态*/
            page.setCameraDistance(10000);
            page.setPivotX(page.getWidth()/2);
            page.setPivotY(page.getWidth());
            page.setRotationY(-20+(1-position)*20);
        } else if (position<=2) {
        /*页面已经在屏幕右侧第一个*/
            page.setCameraDistance(10000);
            page.setPivotX(page.getWidth()/2);
            page.setPivotY(page.getWidth());
            page.setRotationY(-20);
        }

    }
}
