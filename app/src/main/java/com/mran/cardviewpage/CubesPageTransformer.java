package com.mran.cardviewpage;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by M on 2017/9/21.
 */

public class CubesPageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {
        if (position <=-1) { /* [-Infinity,-1)*/
        /*页面已经在屏幕左侧且不可视*/
        } else if (position <= 0) { /* [-1,0]*/
            /*页面从左侧进入或者向左侧滑出的状态*/
            page.setCameraDistance(100000);
            page.setPivotX(page.getMeasuredWidth());
            page.setPivotY(page.getMeasuredHeight()*0.5f);
            page.setRotationY(90*position);
        } else if (position <= 1) {/* (0,1]*/
            /*页面从右侧进入或者向右侧滑出的状态*/
            page.setCameraDistance(100000);
            page.setPivotX(0);
            page.setPivotY( page.getWidth()*(0.5f));
            page.setRotationY(90*position);
        }else if (position >1){
        /*页面已经在屏幕右侧且不可视*/

        }
    }
}
