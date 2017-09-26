package com.mran.cardpage;

import android.view.View;

/**
 * Created by M on 2017/9/17.
 */

class CardItem {
    private View mView;
    public int mIndexInCards;//记录card在card堆中的位置,上层到下层依次是0-4;
    public int cardWidth;
    public int cardHeight;
    public int cardTop;
    public int cardLeft;
    public int cardRight;
    public int cardBottom;
    public int oldCardWidth;
    public int oldCardHeight;
    public int oldCardTop;
    public int oldCardLeft;
    public int oldCardRight;
    public int oldCardBottom;
    public int alpha;

    CardItem(View page, int index) {
        this.mView = page;
        this.mView.setTag(index);
    }

    public View getView() {
        return mView;
    }

    public void setView(View view) {
        mView = view;
    }

    public int getCardWidth() {
        return cardWidth;
    }

    public void setCardWidth(int cardWidth) {
        this.cardWidth = cardWidth;
    }

    public int getCardHeight() {
        return cardHeight;
    }

    public void setCardHeight(int cardHeight) {
        this.cardHeight = cardHeight;
    }

    public int getCardTop() {
        return cardTop;
    }

    public void setCardTop(int cardTop) {
        this.cardTop = cardTop;
    }

    public int getCardLeft() {
        return cardLeft;
    }

    public void setCardLeft(int cardLeft) {
        this.cardLeft = cardLeft;
    }

    public int getCardRight() {
        return cardRight;
    }

    public void setCardRight(int cardRight) {
        this.cardRight = cardRight;
    }

    public int getCardBottom() {
        return cardBottom;
    }

    public void setCardBottom(int cardBottom) {
        this.cardBottom = cardBottom;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public void update() {
        oldCardWidth = cardWidth;
        oldCardHeight = cardHeight;
        oldCardTop = cardTop;
        oldCardLeft = cardLeft;
        oldCardRight = cardRight;
        oldCardBottom = cardBottom;
    }
}
