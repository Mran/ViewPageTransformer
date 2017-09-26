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
            page.setAlpha(position);
        } else if (position <= 0) {
            page.setAlpha(1 + position);
            page.setPivotY(page.getHeight() / 2);
            page.setPivotX(0);
            page.setCameraDistance(60000);
            page.setRotationY((position * 180));
            page.setTranslationX(position * -page.getWidth());
        } else if (position <= 1) {

            page.setTranslationX(position * -page.getWidth());

        } else if (position > 1) {


            page.setTranslationX(position * -page.getWidth());

        }
    }
}
