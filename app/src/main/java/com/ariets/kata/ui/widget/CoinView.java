package com.ariets.kata.ui.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ariets.kata.R;
import com.ariets.kata.model.Coin;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.ariets.kata.model.Coin.DIME;
import static com.ariets.kata.model.Coin.NICKEL;
import static com.ariets.kata.model.Coin.PENNY;
import static com.ariets.kata.model.Coin.QUARTER;

public class CoinView extends FrameLayout {

    @VisibleForTesting
    static final int COIN_NICKEL = 2;
    @VisibleForTesting
    static final int COIN_DIME = 3;
    @VisibleForTesting
    static final int COIN_QUARTER = 4;

    @BindView(R.id.coin_view_image)
    ImageView imageView;
    @BindView(R.id.coin_view_textview)
    TextView textView;

    @VisibleForTesting
    Coin coin;

    public CoinView(@NonNull Context context) {
        this(context, null);
    }

    public CoinView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CoinView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_coin, this, true);
        ButterKnife.bind(this);

        setupLayoutParams();

        if (attrs == null) {
            return;
        }
        Resources.Theme theme = context.getTheme();
        TypedArray typedArray = theme.obtainStyledAttributes(attrs, R.styleable.CoinView, 0, 0);
        try {
            int coinType = typedArray.getInteger(R.styleable.CoinView_coinType, 0);
            setCoinType(coinType);
            setupView();

            TypedValue outValue = new TypedValue();
            getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
            setBackgroundResource(outValue.resourceId);
        } finally {
            typedArray.recycle();
        }
    }

    @VisibleForTesting
    void setCoinType(int coinType) {
        switch (coinType) {
            case COIN_NICKEL:
                coin = NICKEL;
                break;
            case COIN_DIME:
                coin = DIME;
                break;
            case COIN_QUARTER:
                coin = QUARTER;
                break;
            default:
                coin = PENNY;
        }
    }

    private void setupLayoutParams() {
        FrameLayout.LayoutParams params = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        setLayoutParams(params);
    }

    @VisibleForTesting
    void setupView() {
        if (coin == null) {
            return;
        }
        @ColorRes int tintColor;
        @StringRes int textRes;
        @ColorInt int textColor = BLACK;
        switch (coin) {
            case NICKEL:
                textRes = R.string.coin_value_nickel;
                tintColor = R.color.coin_nickel;
                break;
            case DIME:
                textRes = R.string.coin_value_dime;
                tintColor = R.color.coin_dime;
                break;
            case QUARTER:
                textRes = R.string.coin_value_quarter;
                tintColor = R.color.coin_quarter;
                break;
            default:
                textRes = R.string.coin_value_penny;
                tintColor = R.color.coin_penny;
                textColor = WHITE;
        }
        imageView.setColorFilter(ContextCompat.getColor(getContext(), tintColor));
        textView.setText(textRes);
        textView.setTextColor(textColor);
    }

    @Nullable
    public Coin getCoin() {
        return coin;
    }
}
