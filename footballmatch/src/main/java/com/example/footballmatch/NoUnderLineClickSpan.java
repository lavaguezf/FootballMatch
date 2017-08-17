package com.example.footballmatch;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by U310 on 2016/11/29.
 */

public class NoUnderLineClickSpan extends ClickableSpan {
    String text;
    public NoUnderLineClickSpan(String text) {
        super();
        this.text=text;
    }

    @Override
    public void onClick(View widget) {
          }

    @Override
    public void updateDrawState(TextPaint ds) {
        //去掉下划线
        ds.setUnderlineText(false);
        super.updateDrawState(ds);

    }
}
