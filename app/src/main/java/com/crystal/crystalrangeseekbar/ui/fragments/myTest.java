package com.crystal.crystalrangeseekbar.ui.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.R;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.PriceTagSeekBar;

/**
 * Created by hesk on 16年11月12日.
 */

public class myTest extends Fragment {

    protected View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null)
                parent.removeView(rootView);
        }

        try {
            rootView = inflater.inflate(R.layout.my_test, container, false);
        } catch (InflateException e) {
            e.printStackTrace();
        }

        return rootView;
    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    public void init() {
        setRangeSeekbar1();
    }

    private void setRangeSeekbar1() {
        // get seekbar from view
        final PriceTagSeekBar rangeSeekbar = (PriceTagSeekBar) rootView.findViewById(R.id.rangeSeekbar1);
        // get min and max text view
        final TextView tvMin = (TextView) rootView.findViewById(R.id.textMin1);
        final TextView tvMax = (TextView) rootView.findViewById(R.id.textMax1);
        // set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin.setText(String.valueOf(minValue));
                tvMax.setText(String.valueOf(maxValue));
            }
        });
        // set final value listener
        rangeSeekbar.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                Log.d("CRS=>", String.valueOf(minValue) + " : " + String.valueOf(maxValue));
            }
        });
        //rangeSeekbar.setFixGap(10f);
        rangeSeekbar.setBarHighlightColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rangeSeekbar.setMinValue(0).setMaxValue(100)
                        .setMinStartValue(7).setMaxStartValue(10).apply();
            }
        }, 3000);

        rangeSeekbar
                .setMaxValue(100)
                .setMinValue(0)
                .setCornerRadius(40f)
                .setLeftThumbDrawable(R.drawable.ic_price_tag)
                .setLeftThumbHighlightDrawable(R.drawable.ic_price_tag_b)
                .setRightThumbDrawable(R.drawable.ic_price_tag)
                .setRightThumbHighlightDrawable(R.drawable.ic_price_tag_b);
    }


}
