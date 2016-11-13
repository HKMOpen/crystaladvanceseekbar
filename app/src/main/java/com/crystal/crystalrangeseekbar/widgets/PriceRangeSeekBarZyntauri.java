package com.crystal.crystalrangeseekbar.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;

/**
 * Created by hesk on 16年11月13日.
 */

public class PriceRangeSeekBarZyntauri extends CrystalRangeSeekbar implements OnRangeSeekbarChangeListener {
    private Number min_val, max_value;

    public PriceRangeSeekBarZyntauri(Context context) {
        super(context);
    }

    public PriceRangeSeekBarZyntauri(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PriceRangeSeekBarZyntauri(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected int getMeasureSpecHeight(int heightMeasureSpec) {
        return super.getMeasureSpecHeight(heightMeasureSpec) * 2;
    }

    Paint mPaintText;

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
    protected void setupLeftThumb(Canvas canvas, Paint paint, RectF rect) {
        super.setupLeftThumb(canvas, paint, rect);
        if (min_val == null) return;
        String ts = min_val.toString() + "K";
        RectF main = getLeftThumbRect();
        canvas.drawText(ts, main.left + 30f, main.top + 20f, mPaintText);
    }

    @Override
    protected void setupRightThumb(Canvas canvas, Paint paint, RectF rect) {
        super.setupRightThumb(canvas, paint, rect);
        if (max_value == null) return;
        String ts = max_value.toString() + "K";
        RectF main = getRightThumbRect();
        canvas.drawText(ts, main.left + 30f, main.top + 20f, mPaintText);
    }
}
