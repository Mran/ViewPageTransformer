package com.mran.cardpage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.widget.ViewDragHelper.STATE_DRAGGING;
import static android.support.v4.widget.ViewDragHelper.STATE_IDLE;

/**
 * Created by M on 2017/9/15.
 */

public class CardPage extends ViewGroup {
    private final static int CARDMAXSIZE = 5;
    private final static int CARD_SPACING = 30;
    private int width;//整个宽度
    private int height;//整个高度
    private int middleWidthPostion;//中间位置
    private int percentage10;//百分之10的宽度
    private float suofangxish = 0.9f;
    private int jianju = 100;
    private Paint mPaint;
    private Context mContext;
    private AttributeSet mAttributeSet;
    private int[] cardWidth = {0, 0, 0, 0, 0};//card的宽度
    private int[] cardHeight = {0, 0, 0, 0, 0};//card的高度
    private int[] cardLeftPostion = {0, 0, 0, 0, 0};//card左边坐标
    private int[] cardTopPostion = {0, 0, 0, 0, 0};//card上部坐标
    private int[] cardColor = {0xc8ffeff4, 0xd8ffeff4, 0xe8ffeff4, 0xf8ffeff4};
    private int pageSize = 0;
    private int pageInTop = 0;
    private int pageInBottom = 0;
    private int pageLast = 0;
    private float mMoveDetal = 0;//全局移动变量
    boolean changedSuccess = false;//用来判断是否移动成功
    boolean forcelayout = false;
    private int topPosition = jianju * 4;
    private int leftPsotion;
    float firstTouchPosition = 0;
    float alpha;
    private int dragState = -1;
    private ViewDragHelper mViewDragHelper;
    private List<CardItem> mViewList = new ArrayList<>();

    public CardPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CardPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.mContext = context;
        this.mAttributeSet = attributeSet;
        mPaint = new Paint();
        setWillNotDraw(false);
        mViewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == getChildAt(getChildCount() - 1);/*只有处于最顶层的view才可以被拖动,否则在顶层拖动未结束时拖动其他view,顶层view会被释放*/
            }


            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {

                super.onEdgeDragStarted(edgeFlags, pointerId);
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {

                return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return jianju * 4;
            }

            @Override
            public void onViewDragStateChanged(int state) {
                dragState = state;
                switch (state) {
                    case STATE_DRAGGING:
                        break;
                    case STATE_IDLE:
                        forcelayout = false;
                        xx = 0;
                        cx = 0;
                        if (changedSuccess) {
//                            removeView(getChildAt(getChildCount() - 1));
                            removeViewInLayout(getChildAt(getChildCount() - 1));
                            if (pageInBottom < pageSize)
                                addView(mViewList.get(pageInBottom++).getView(), 0);
                        }
                        break;
                }

                super.onViewDragStateChanged(state);
            }

            int xx = 0;
            float cx = 0;

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {

                alpha = (float) ((float) Math.abs(left - leftPsotion) / (0.95 * width));//更新即将出来的Card的透明度
                mMoveDetal = (float) ((float) Math.abs(left - leftPsotion) / (0.95 * width));
                Log.d("CardPage", "onViewPositionChanged: left" + left + "mMoveDeta" + mMoveDetal + "leftPsotion" + leftPsotion + "width" + width);
                forcelayout = true;
                xx += dx;
                cx += (int) (dx * jianju / (0.90 * width));
                int size=getChildCount();
                int addx=0;
                if (size<CARDMAXSIZE)
                    addx=CARDMAXSIZE-size;
                for (int i = 0; i < getChildCount(); i++) {
                    View view = getChildAt(i);
                    if (view == changedView)
                        continue;
                    Log.d("CardPage", "onViewPositionChanged: xx" + (xx) + "dx" + dx + "cx" + cx + "jianju" + jianju + "width" + width + "alpha" + alpha);
                    view.setTop((int) (jianju * (i+addx) + mMoveDetal * jianju));

                    view.setScaleX((float) (1+0.11*mMoveDetal));
                    view.setScaleY((float) (1+0.11*mMoveDetal));
                }
                Log.d("CardPage", "onViewPositionChanged: view.width"+getChildAt(getChildCount()-1).getWidth());

                invalidate();
                super.onViewPositionChanged(changedView, left, top, dx, dy);
            }


            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                Log.d("CardPage", "onViewReleased: releasedChildtag" + releasedChild.getTag());

                /*惯性滑动*/
                if (Math.abs(releasedChild.getLeft() - leftPsotion) <= 0.3 * 0.9 * width) {
                    mViewDragHelper.settleCapturedViewAt(leftPsotion, topPosition);
                    changedSuccess = false;
                } else if (releasedChild.getLeft() - leftPsotion < 0) {/*向左划*/
                    changedSuccess = true;
                    mViewDragHelper.settleCapturedViewAt(-width, topPosition);
                } else {/*向右划*/
                    changedSuccess = true;
                    mViewDragHelper.settleCapturedViewAt(width, topPosition);
                }
                invalidate();

            }

        });
        mViewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT | ViewDragHelper.EDGE_RIGHT);
    }

    private int index = 0;

    public CardPage addPage(View pageView) {
        mViewList.add(new CardItem(pageView, index++));
        return this;
    }

    int tags = 0;

    public void build() {
        int firstSize = Math.min(CARDMAXSIZE, mViewList.size());
        for (int i = 0; i < firstSize; i++) {
            CardItem cardItem = mViewList.get(i);
            cardItem.mIndexInCards = i;
            addView(cardItem.getView(), 0);
            pageInBottom++;
            Log.d("CardPage", "build: tags" + tags++);
        }
        pageInBottom++;
        pageSize = mViewList.size();
        childSize = Math.min(pageSize, CARDMAXSIZE);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("CardPage", "onDraw: ");
        mPaint.setColor(0xff303F9F);
        mPaint.setTextSize(45);
        for (int i = 0; i < getChildCount(); i++) {
            canvas.drawText(String.valueOf(mViewList.get((Integer) getChildAt(i).getTag()).cardWidth), i * 100, 100, mPaint);
            canvas.drawText(String.valueOf(mViewList.get((Integer) getChildAt(i).getTag()).cardTop), i * 100, 200, mPaint);
        }
        mPaint.setStrokeWidth(15);
        canvas.drawLine(0, jianju * 4, width, jianju * 4, mPaint);
        canvas.save();
    }

    boolean firstMeasure = true;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("CardPage", "onMeasure: tags" + tags++);

        if (leftPsotion == 0) {/*避免多次计算*/
            width = getSize(400, widthMeasureSpec);
            height = getSize(400, heightMeasureSpec);
            percentage10 = (int) (width * 0.1);
            middleWidthPostion = width / 2;
            leftPsotion = 0;
            topPosition = jianju * 4;
        }

        firstMeasure = false;

    }


    int childSize;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.d("CardPage", "onLayout: ");
        childSize = getChildCount();
        int size=getChildCount();
        int addx=0;
        if (size<CARDMAXSIZE)
            addx=CARDMAXSIZE-size;
        for (int i = 0; i < childSize; i++) {
            View view = getChildAt(i);
            int cardWidth = (int) (Math.pow(suofangxish, childSize-i-addx) * width);
            Log.d("CardPage", "onLayout: cardWidth" + cardWidth);
            Log.d("CardPage", "onLayout: i_" + i + "jianju * i" + jianju * i);
            view.layout(middleWidthPostion - cardWidth / 2, jianju * (i+addx), middleWidthPostion + cardWidth / 2, jianju * (i+addx) + cardWidth);
        }
    }

    private int getSize(int defaultSize, int measureSpec) {

        int mySize = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
                mySize = defaultSize;
                break;
            case MeasureSpec.AT_MOST:
                mySize = size;
                break;
            case MeasureSpec.EXACTLY:
                mySize = size;
                break;
        }
        return mySize;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                firstTouchPosition = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return mViewDragHelper.shouldInterceptTouchEvent(ev);

    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }
}
