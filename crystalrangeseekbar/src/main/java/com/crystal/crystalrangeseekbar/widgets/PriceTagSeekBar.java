package com.crystal.crystalrangeseekbar.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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

    private float getTextHeight(String text, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect.height() / 1.1f;
    }

    private float getTextWidth(String text, Paint paint) {
        return paint.measureText(text);
    }

    public PriceTagSeekBar setFontSizeSmall(float n) {
        small_size_text = n;
        return this;
    }

    public PriceTagSeekBar setFontSizeBig(float n) {
        big_size_text = n;
        return this;
    }

    public PriceTagSeekBar setPressBigThumbY(float n) {
        top_press_thumb_y = n;
        return this;
    }

    public PriceTagSeekBar setPressSmallThumbY(float n) {
        top_small_thumb_y = n;
        return this;
    }

    public PriceTagSeekBar setBarPosY(float n) {
        display_bar_y = n;
        return this;
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

    private float finddrawxv1(RectF main, String display_text, Paint mpaint) {
        mpaint.setTextSize(getTextSize(small_size_text, display_text.length()));
        mpaint.setTextAlign(Paint.Align.CENTER);
        float t_width = getTextWidth(display_text, mpaint);
        float dx1 = main.right - main.left;
        float ddx = dx1 - t_width;
        float start_x = main.left + ddx / 2f + t_width / 2f;
        return start_x;
    }

    private float finddrawxv2(RectF main, String display_text, Paint mpaint) {
        mpaint.setTextSize(getTextSize(big_size_text, display_text.length()));
        mpaint.setTextAlign(Paint.Align.CENTER);
        float t_width = getTextWidth(display_text, mpaint);
        float dx1 = main.right - main.left;
        float ddx = dx1 - t_width;
        float start_x = main.left + t_width / 1f + 5f;
        return start_x;
    }

    @Override
    protected void setupLeftThumb(Canvas canvas, Paint paint, RectF rect) {
        super.setupLeftThumb(canvas, paint, rect);
        if (min_val == null) return;
        String ts = min_val.intValue() <= 0 ? "Free" : min_val.toString() + "K";
        RectF main = getLeftThumbRect();
        if (Thumb.MIN.equals(getPressedThumb())) {
            float t_height = getTextHeight(ts, mPaintText);
            float start_x = finddrawxv2(main, ts, mPaintText);
            canvas.drawText(ts, start_x, main.top + text_big_y, mPaintText);
        } else {
            float t_height = getTextHeight(ts, mPaintText);
            float start_x = finddrawxv1(main, ts, mPaintText);
            canvas.drawText(ts, start_x, main.top + text_small_y, mPaintText);
        }
    }

    @Override
    protected void setupRightThumb(Canvas canvas, Paint paint, RectF rect) {
        super.setupRightThumb(canvas, paint, rect);
        if (max_value == null) return;
        String ts = max_value.intValue() >= 100 ? max_value.toString() + "K+" : max_value.toString() + "K";
        RectF main = getRightThumbRect();
        if (Thumb.MAX.equals(getPressedThumb())) {
            float t_height = getTextHeight(ts, mPaintText);
            float start_x = finddrawxv2(main, ts, mPaintText);
            canvas.drawText(ts, start_x, main.top + text_big_y, mPaintText);
        } else {
            float t_height = getTextHeight(ts, mPaintText);
            float start_x = finddrawxv1(main, ts, mPaintText);
            canvas.drawText(ts, start_x, main.top + text_small_y, mPaintText);
        }
    }
}
