package com.ariets.kata.ui.widget;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ariets.kata.ui.vending.VendingActivity;

import org.assertj.android.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.ariets.kata.model.Product.CANDY;
import static com.ariets.kata.model.Product.CHIPS;
import static com.ariets.kata.model.Product.COLA;
import static com.ariets.kata.ui.widget.ProductTextView.PRODUCT_CANDY;
import static com.ariets.kata.ui.widget.ProductTextView.PRODUCT_CHIPS;
import static com.ariets.kata.ui.widget.ProductTextView.PRODUCT_COLA;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ProductTextViewTest {

    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule<>(VendingActivity.class);

    @Test
    public void setProductTypeForCandySetsProperProduct() {
        Context context = activityTestRule.getActivity();
        ProductTextView productTextView = new ProductTextView(context);

        productTextView.setProductType(context, PRODUCT_CANDY);

        assertEquals(CANDY, productTextView.getProduct());
    }

    @Test
    public void setProductTypeForChipsSetsProperProduct() {
        Context context = activityTestRule.getActivity();
        ProductTextView productTextView = new ProductTextView(context);

        productTextView.setProductType(context, PRODUCT_CHIPS);

        assertEquals(CHIPS, productTextView.getProduct());
    }

    @Test
    public void setProductTypeForColaSetsProperProduct() {
        Context context = activityTestRule.getActivity();
        ProductTextView productTextView = new ProductTextView(context);

        productTextView.setProductType(context, PRODUCT_COLA);

        assertEquals(COLA, productTextView.getProduct());
    }

    @Test
    public void setProductTypeForCandySetsProperText() {
        Context context = activityTestRule.getActivity();
        ProductTextView productTextView = new ProductTextView(context);
        String expected = CANDY + " ($" + CANDY.getDisplayPrice() + ")";

        productTextView.setProductType(context, PRODUCT_CANDY);

        Assertions.assertThat(productTextView).hasText(expected);
    }

    @Test
    public void setProductTypeForChipsSetsProperText() {
        Context context = activityTestRule.getActivity();
        ProductTextView productTextView = new ProductTextView(context);
        String expected = CHIPS + " ($" + CHIPS.getDisplayPrice() + ")";

        productTextView.setProductType(context, PRODUCT_CHIPS);

        Assertions.assertThat(productTextView).hasText(expected);
    }

    @Test
    public void setProductTypeForColaSetsProperText() {
        Context context = activityTestRule.getActivity();
        ProductTextView productTextView = new ProductTextView(context);
        String expected = COLA + " ($" + COLA.getDisplayPrice() + ")";

        productTextView.setProductType(context, PRODUCT_COLA);

        Assertions.assertThat(productTextView).hasText(expected);
    }

}