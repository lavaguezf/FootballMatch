package com.example.footballmatch.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;

/**
 * Created by U310 on 2016/12/4.
 */

public class SlidingView extends HorizontalScrollView {

    private LinearLayout myWapper;
    private ViewGroup menu;
    private ViewGroup content;

    private int currentScreenWidth;
    //menu的右边距  dp
    private int menuRightPadding = 80;

    private boolean once = false;

    private int menuWidth;
    private boolean isOpen;

    public SlidingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        currentScreenWidth = outMetrics.widthPixels;
//        //将50dp转化成像素值,将dp转化为px
        menuRightPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80,
                context.getResources()
                        .getDisplayMetrics());
    }


    //设置子view的宽和高、自己的宽和高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!once) {
            myWapper = (LinearLayout) getChildAt(0);
            menu = (ViewGroup) myWapper.getChildAt(0);
            content = (ViewGroup) myWapper.getChildAt(1);

            menuWidth = menu.getLayoutParams().width = currentScreenWidth - menuRightPadding;
            content.getLayoutParams().width = currentScreenWidth;
            once = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    //设置子view的位置

    /**
     * 通过设置偏移量来隐藏menu
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.scrollTo(menuWidth, 0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();

        switch (action) {
            case MotionEvent.ACTION_UP:
                //屏幕左边的剩余宽度
                int x = this.getScrollX();
                Log.i("aas","getScrollX:"+x);

                if (x>menuWidth*1/2){
                    this.smoothScrollTo(menuWidth, 0);
                    isOpen = true;
                }else {
                    this.smoothScrollTo(0, 0);
                    isOpen = false;
                }
                return true;

        }

        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float sacle = l * 1.0f / menuWidth;

        //属性动画
        float rightScale = 0.7f + 0.3f * sacle;
        float leftScale = 1.0f - sacle * 0.3f;
        float leftAlpha = 0.6f + 0.4f * (1.0f - sacle);
        //设置menu的偏移量
        ViewHelper.setTranslationX(menu, menuWidth * sacle * 0.8f);


//        //设置缩放中心点
//        ViewHelper.setPivotY(content,content.getHeight()/2);
//        ViewHelper.setPivotX(content,0);
//        //设置content的缩放
//        ViewHelper.setScaleX(content,rightScale);
//        ViewHelper.setScaleY(content,rightScale);
        //menu缩放
//        ViewHelper.setScaleX(menu, leftScale);
//        ViewHelper.setScaleY(menu, leftScale);
//        //menu透明度
//        ViewHelper.setAlpha(menu, leftAlpha);
    }

    public void openMenu() {
        if (isOpen) {
            return;
        }
        this.smoothScrollTo(0, 0);
        isOpen = true;
    }

    public void closeMenu() {
        if (!isOpen) {
            return;
        }
        this.smoothScrollTo(menuWidth, 0);
        isOpen = false;
    }

    //切换菜单
    public void changeMenu() {
        if (isOpen) {
            closeMenu();
        } else {
            openMenu();
        }
    }

    public void showOnContent() {
//        ViewHelper.setScrollX(menu,menuWidth);
    }
}
