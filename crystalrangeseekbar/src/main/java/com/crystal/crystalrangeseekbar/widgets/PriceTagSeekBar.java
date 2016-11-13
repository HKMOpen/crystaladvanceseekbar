package com.crystal.crystalrangeseekbar.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;

/**
 * Created by hesk on 16/11/13.
 */

public class PriceTagSeekBar extends CrystalRangeSeekbar implements OnRangeSeekbarChangeListener {
    private Number min_val, max_value;
    private Paint mPaintText;
    private float
            display_bar_y = 114f,
            top_press_thumb_y = 40f,
            top_small_thumb_y = 200f,

    text_small_y = 50f,
            text_big_y = 100f,

    big_size_text = 70f,
            small_size_text = 25f;


    public PriceTagSeekBar(Context context) {
        super(context);
    }

    public PriceTagSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PriceTagSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected int getMeasureSpecHeight(int heightMeasureSpec) {
        return super.getMeasureSpecHeight(heightMeasureSpec) * 5;
    }

    @Override
    protected void overrideBarDisplay(RectF bounce) {
        bounce.top = 0.5f * (getHeight() - getBarHeight()) + display_bar_y;
        bounce.bottom = 0.5f * (getHeight() + getBarHeight()) + display_bar_y;
    }

    @Override
    protected void init() {
        super.init();
        mPaintText = new Paint();
        mPaintText.setColor(Color.BLUE);
        mPaintText.setStyle(Paint.Style.FILL);
        mPaintText.setTextSize(20f);
        mPaintText.setFakeBoldText(true);
        mPaintText.setAntiAlias(true);
        mPaintText.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    public void apply() {
        super.apply();
        setOnRangeSeekbarChangeListener(this);
    }

    @Override
    public void valueChanged(Number minValue, Number maxValue) {
        min_val = minValue;
        max_value = maxValue;
    }

    @Override
    protected void overrideThumbPosition(RectF rect, Thumb touched) {
        if (touched.equals(getPressedThumb())) {
            rect.top = top_press_thumb_y;
            rect.left = rect.left - 78f;
        } else if (touched.equals(getPressedThumb())) {
            rect.top = top_press_thumb_y;
            rect.left = rect.left - 78f;
        } else {
            rect.top = top_small_thumb_y;
        }
    }

    protected float getTextSize(float size, int char_size) {
        if (char_size >= 5 && size == big_size_text) {
            return size - 30f;
        } else {
            return size;
        }
    }

    @Override
    protected void setupLeftThumb(Canvas canvas, Paint paint, RectF rect) {
        super.setupLeftThumb(canvas, paint, rect);
        if (min_val == null) return;
        String ts = min_val.intValue() <= 0 ? "Free" : min_val.toString() + "K";
        RectF main = getLeftThumbRect();
        if (Thumb.MIN.equals(getPressedThumb())) {
            mPaintText.setTextSize(getTextSize(big_size_text, ts.length()));
            canvas.drawText(ts, main.left + 130f, main.top + text_big_y, mPaintText);
        } else {
            mPaintText.setTextSize(getTextSize(small_size_text, ts.length()));
            canvas.drawText(ts, main.left + 55f, main.top + text_small_y, mPaintText);
        }
    }

    @Override
    protected void setupRightThumb(Canvas canvas, Paint paint, RectF rect) {
        super.setupRightThumb(canvas, paint, rect);
        if (max_value == null) return;
        String ts = max_value.intValue() >= 100 ? max_value.toString() + "K+" : max_value.toString() + "K";
        RectF main = getRightThumbRect();
        if (Thumb.MAX.equals(getPressedThumb())) {
            mPaintText.setTextSize(getTextSize(big_size_text, ts.length()));
            canvas.drawText(ts, main.left + 130f, main.top + text_big_y, mPaintText);
        } else {
            mPaintText.setTextSize(getTextSize(small_size_text, ts.length()));
            canvas.drawText(ts, main.left + 55f, main.top + text_small_y, mPaintText);
        }
    }
}
