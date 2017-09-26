package com.mran.cardviewpage;

import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Created by M on 2017/9/13.
 */

public class ViewPagerAdapter extends PagerAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
