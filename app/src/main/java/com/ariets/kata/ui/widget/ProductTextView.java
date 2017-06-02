package com.ariets.kata.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.ariets.kata.R;
import com.ariets.kata.model.Product;

import static com.ariets.kata.model.Product.CANDY;
import static com.ariets.kata.model.Product.CHIPS;
import static com.ariets.kata.model.Product.COLA;

public class ProductTextView extends AppCompatTextView {

    private static final int PRODUCT_CANDY = 1;
    private static final int PRODUCT_CHIPS = 2;
    private static final int PRODUCT_COLA = 3;

    @Nullable
    private Product product;

    public ProductTextView(Context context) {
        this(context, null);
    }

    public ProductTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProductTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.getTheme()
                    .obtainStyledAttributes(attrs, R.styleable.ProductTextView, 0, 0);
            try {
                setProductType(context, typedArray.getInteger(R.styleable.ProductTextView_productType, 0));
            } finally {
                typedArray.recycle();
            }
        }
    }

    private void setProductType(Context context, int productType) {
        switch (productType) {
            case PRODUCT_CANDY:
                product = CANDY;
                break;
            case PRODUCT_CHIPS:
                product = CHIPS;
                break;
            case PRODUCT_COLA:
                product = COLA;
                break;
        }
        if (product != null) {
            setText(context.getString(R.string.product_title, product.toString(),
                    product.getDisplayPrice()));
        }
    }

    @Nullable public Product getProduct() {
        return product;
    }
}
