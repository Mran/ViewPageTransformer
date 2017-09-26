package com.mran.cardviewpage;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by M on 2017/9/21.
 */

public class CardStackPaegTransformer implements ViewPager.PageTransformer {
    public void transformPage(View page, float position) {
        if (position <= -1) {
//            page.setAlpha(0);
        } else if (position <= 0) {

        } else if (position < 1) {
            page.setAlpha((float) (1 - position * 0.1));
            page.setPivotX(page.getWidth() / 2f);
            page.setPivotY(page.getHeight() / 2f);
            page.setScaleX((float) Math.pow(0.9f, position));    /*0.9f为缩放系数*/
            page.setScaleY((float) Math.pow(0.9f, position));
            page.setTranslationX(position * -page.getWidth());
            page.setTranslationY(-position * 70);/*70每层card的Y轴间隔*/

        } else if (position >= 1) {
            page.setAlpha((float) (1 - position * 0.1));
            page.setPivotX(page.getWidth() / 2f);
            page.setPivotY(page.getHeight() / 2f);
            page.setScaleX((float) Math.pow(0.9f, position));
            page.setScaleY((float) Math.pow(0.9f, position));
            page.setTranslationX(position * -page.getWidth());
            page.setTranslationY(-position * 70);
        }

    }
}
