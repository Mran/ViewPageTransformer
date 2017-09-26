## 书籍翻页渐隐

![](http://oe38oe3ti.bkt.clouddn.com/17-9-26/44602896.jpg)
```java
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

```
## 翻页旋转

![](http://oe38oe3ti.bkt.clouddn.com/17-9-26/2974439.jpg)
```java
public class FilpPageRotationPageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {
        if (position < -1) { /* [-Infinity,-1)*/
            /*页面已经在屏幕左侧且不可视*/
        } else if (position <= 0) { /* [-1,0]*/
            /*页面从左侧进入或者向左侧滑出的状态*/
            page.setCameraDistance(60000);
            page.setAlpha(1 + position);
            page.setTranslationX(page.getWidth() * -position);
            page.setPivotX(0);
            page.setRotationY(position * 90);
        } else if (position <= 1) {/* (0,1]*/
            /*页面从右侧进入或者向右侧滑出的状态*/
            page.setCameraDistance(60000);
            page.setTranslationX(page.getWidth() * -position);
            page.setPivotX(0);
            page.setRotationY(position * 90);
            page.setAlpha(1 - position);
        } else if (position > 1) {
        /*页面已经在屏幕右侧且不可视*/
        }
    }
}
```
## 卡片绕中心旋转

![](http://oe38oe3ti.bkt.clouddn.com/17-9-26/49301453.jpg)
```java
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
```
## 立方体旋转

![](http://oe38oe3ti.bkt.clouddn.com/17-9-26/64235752.jpg)
```java
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
```
## 转盘旋转

![](http://oe38oe3ti.bkt.clouddn.com/17-9-26/6336470.jpg)
```java
public class TurntablePageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {
        if (position < -1) { /* [-Infinity,-1)*/
        /*页面已经在屏幕左侧且不可视*/
        } else if (position <= 0) { /* [-1,0]*/
            /*页面从左侧进入或者向左侧滑出的状态*/
            page.setPivotX(page.getWidth() / 2);
            page.setPivotY(page.getHeight());
            page.setRotation(90*position);
        } else if (position <= 1) {/* (0,1]*/
            /*页面从右侧进入或者向右侧滑出的状态*/
            page.setPivotX(page.getWidth() / 2);
            page.setPivotY(page.getHeight());
            page.setRotation(90*position);
        } else if (position > 1) {
        /*页面已经在屏幕右侧且不可视*/

        }
    }
}
```
## 层叠缩放

![](http://oe38oe3ti.bkt.clouddn.com/17-9-26/61359792.jpg)
```java
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
```
## 折叠向上

![](http://oe38oe3ti.bkt.clouddn.com/17-9-26/37159823.jpg)
```java
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
```
## 滑动缩放

![](http://oe38oe3ti.bkt.clouddn.com/17-9-26/51293176.jpg)
```java
public class ZoominPagerTransFormer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {


        if (position < -1) { /* [-Infinity,-1)*/
        /*页面已经在屏幕左侧且不可视*/
            page.setScaleX((float) (1 + position * 0.1));
            page.setScaleY((float) (1 + position * 0.1));
        } else if (position <= 0) { /* [-1,0]*/
            /*页面从左侧进入或者向左侧滑出的状态*/

            page.setScaleX((float) (1 + position * 0.1));
            page.setScaleY((float) (1 + position * 0.1));
        } else if (position <= 1) {/* (0,1]*/
            /*页面从右侧进入或者向右侧滑出的状态*/
            page.setScaleX((float) (1-  position * 0.1));
            page.setScaleY((float) (1 - position * 0.1));
        } else if (position > 1) {
        /*页面已经在屏幕右侧且不可视*/
            page.setScaleX((float) (1-  position * 0.1));
            page.setScaleY((float) (1 - position * 0.1));
        }
    }
}
```
## 卡片堆叠

![](http://oe38oe3ti.bkt.clouddn.com/17-9-26/56385672.jpg)
```java
public class CardStackPaegTransformer implements ViewPager.PageTransformer {
    public void transformPage(View page, float position) {
        if (position <= -1) {
         /*页面已经在屏幕左侧且不可视*/
        } else if (position <= 0) {
        /*页面从左侧进入或者向左侧滑出的状态*/
        } else if (position < 1) {
            /*页面从右侧进入或者向右侧滑出的状态*/
            page.setAlpha((float) (1 - position * 0.1));
            page.setPivotX(page.getWidth() / 2f);
            page.setPivotY(page.getHeight() / 2f);
            page.setScaleX((float) Math.pow(0.9f, position));    /*0.9f为缩放系数*/
            page.setScaleY((float) Math.pow(0.9f, position));
            page.setTranslationX(position * -page.getWidth());
            page.setTranslationY(-position * 70);/*70每层card的Y轴间隔*/

        } else if (position >= 1) {
            /*页面已经在屏幕右侧且不可视*/
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
```
## 卡片堆叠 进阶版

![](http://oe38oe3ti.bkt.clouddn.com/17-9-26/4091466.jpg)
这个进阶版可以看到是在滑动结束后有一个弹跳的类似于果冻动画,这个实际上我是对Viewpage设置了滑动监听,在滑动状态改变时对当前的page设置一个缩放动画,这样就好了
下面是果冻动画的代码,这里安利一个网站http://inloop.github.io/interpolator/,这个网站可以直观看到不同插值器的曲线变化情况,我的这个插值器也是在这里经过调整之后得到的.
```java
/*配合以下代码可以实现滑动结束后的果冻效果*/
int mCurrentPosition=0;
float mFactor = 0.5f;
mAnimation = new ScaleAnimation(0.9f, 1.0f, 0.9f, 1.0f, 150, 450);
        mAnimation.setDuration(500);
        mAnimation.setFillAfter(true);
        mAnimation.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float input) {
                return (float) (Math.pow(2, -10 * input) * Math.sin((input - mFactor / 4) * (2 * Math.PI) / mFactor) + 0.9);
            }
        });


mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("MainActivity", "onPageScrolled: position" + position + " positionOffset" + positionOffset + " positionOffsetPixels" + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("MainActivity", "onPageSelected: position" + position);
                mImageViewList.get(mCurrentPosition ).clearAnimation();
                mCurrentPosition = position;
                mImageViewList.get(mCurrentPosition).startAnimation(mAnimation);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("MainActivity", "onPageScrollStateChanged: state" + state);

            }
        });

```