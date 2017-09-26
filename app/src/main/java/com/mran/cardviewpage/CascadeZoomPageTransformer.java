package com.mran.cardviewpage;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by M on 2017/9/24.
 */

public class CascadeZoomPageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {
        if (position < -1) { /* [-Infinity,-1)*/
        /*页面已经在屏幕左侧且不可视*/
        } else if (position <= 0) { /* [-1,0]*/
            /*页面从左侧进入或者向左侧滑出的状态*/
            page.setAlpha(1 + position);
        } else if (position <= 1) {/* (0,1]*/
            /*页面从右侧进入或者向右侧滑出的状态*/
            page.setTranslationX(page.getWidth() * -position);
            page.setScaleX(1-position*0.5f);
            page.setScaleY(1-position*0.5f);
            page.setAlpha(1 - position);
        }else if (position >1){
        /*页面已经在屏幕右侧且不可视*/

        }
    }
}
