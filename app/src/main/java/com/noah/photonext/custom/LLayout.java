package com.noah.photonext.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.noah.photonext.R;

/**
 * Created by HuyLV-CT on 06-Dec-16.
 */

public class LLayout extends LinearLayout {

    public static float DEFAULT_ICON_SIZE = 100, DEFAULT_TEXT_SIZE = 12;
    ImageView iv;
    TextView tv;
    float icon_size;
    float text_size;
    private Drawable d;
    private CharSequence t;
    private Context context;

    public LLayout(Context context) {
        super(context);
    }

    public LLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    void init(AttributeSet attrs){
        context = getContext();
        if(attrs!=null){
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LLayout);
            d = typedArray.getDrawable(R.styleable.LLayout_icon);
            t = typedArray.getText(R.styleable.LLayout_text);
            icon_size = typedArray.getDimension(R.styleable.LLayout_icon_size, DEFAULT_ICON_SIZE);
            text_size = typedArray.getDimension(R.styleable.LLayout_text_size, DEFAULT_TEXT_SIZE);

            iv = new ImageView(context);
            LinearLayout.LayoutParams p = new LayoutParams((int) icon_size, (int) icon_size);
            iv.setLayoutParams(p);
            iv.setImageDrawable(d);
            addView(iv);

            tv = new FontText(context);
            tv.setText(t);
            tv.setTextSize(text_size);
            addView(tv);
            typedArray.recycle();
        }
        setGravity(Gravity.CENTER);
        setOrientation(VERTICAL);

        //add ripple effect
        TypedValue outValue = new TypedValue();
        getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
        setBackgroundResource(outValue.resourceId);
    }

    public void setRippleEffectBorderless(){
        TypedValue outValue = new TypedValue();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, outValue, true);
        }
        setBackgroundResource(outValue.resourceId);
    }

    public void setSelected(){
        iv.setColorFilter(ContextCompat.getColor(context, R.color.colorAccent));
        tv.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
    }

    public void clearColor(){
        iv.setColorFilter(Color.WHITE);
        tv.setTextColor(Color.WHITE);
    }

    public void setIconImage(int resid) {
        iv.setImageResource(resid);
    }

    public void setText(String text) {
        tv.setText(text);
    }

    public void setImageBitmap(Bitmap bm) {
        iv.setImageBitmap(bm);
    }
}
