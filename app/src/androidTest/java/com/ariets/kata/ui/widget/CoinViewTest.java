package com.ariets.kata.ui.widget;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.FrameLayout;

import com.ariets.kata.ui.vending.VendingActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;
import static android.view.Gravity.CENTER;
import static com.ariets.kata.model.Coin.DIME;
import static com.ariets.kata.model.Coin.NICKEL;
import static com.ariets.kata.model.Coin.PENNY;
import static com.ariets.kata.model.Coin.QUARTER;
import static com.ariets.kata.ui.widget.CoinView.COIN_DIME;
import static com.ariets.kata.ui.widget.CoinView.COIN_NICKEL;
import static com.ariets.kata.ui.widget.CoinView.COIN_QUARTER;
import static junit.framework.Assert.assertEquals;
import static org.assertj.android.api.Assertions.assertThat;

@RunWith(AndroidJUnit4.class)
public class CoinViewTest {

    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule<>(VendingActivity.class);

    @Test
    public void contextConstructorSetsLayoutParams() {
        assertCenterGravity(new CoinView(activityTestRule.getActivity()));
    }

    @Test
    public void contextAnfAttributeSetConstructorSetsLayoutParams() {
        assertCenterGravity(new CoinView(activityTestRule.getActivity(), null));
    }

    @Test
    public void contextAnfAttributeSetAndStyleConstructorSetsLayoutParams() {
        assertCenterGravity(new CoinView(activityTestRule.getActivity(), null, 0));
    }

    @Test
    public void setupViewWithQuarterSetsItemsProperly() {
        CoinView coinView = new CoinView(activityTestRule.getActivity());
        coinView.coin = QUARTER;

        coinView.setupView();

        assertThat(coinView.textView).hasText("25¢").hasCurrentTextColor(BLACK);
    }

    @Test
    public void setupViewWithDimeSetsItemsProperly() {
        CoinView coinView = new CoinView(activityTestRule.getActivity());
        coinView.coin = DIME;

        coinView.setupView();

        assertThat(coinView.textView).hasText("10¢").hasCurrentTextColor(BLACK);
    }

    @Test
    public void setupViewWithNickelSetsItemsProperly() {
        CoinView coinView = new CoinView(activityTestRule.getActivity());
        coinView.coin = NICKEL;

        coinView.setupView();

        assertThat(coinView.textView).hasText("5¢").hasCurrentTextColor(BLACK);
    }

    @Test
    public void setupViewWithPennySetsItemsProperly() {
        CoinView coinView = new CoinView(activityTestRule.getActivity());
        coinView.coin = PENNY;

        coinView.setupView();

        assertThat(coinView.textView).hasText("1¢").hasCurrentTextColor(WHITE);
    }

    @Test
    public void setCoinTypeSetsCoinProperlyForQuarter() {
        CoinView coinView = new CoinView(activityTestRule.getActivity());

        coinView.setCoinType(COIN_QUARTER);

        assertEquals(QUARTER, coinView.getCoin());
    }

    @Test
    public void setCoinTypeSetsCoinProperlyForDime() {
        CoinView coinView = new CoinView(activityTestRule.getActivity());

        coinView.setCoinType(COIN_DIME);

        assertEquals(DIME, coinView.getCoin());
    }

    @Test
    public void setCoinTypeSetsCoinProperlyForNickel() {
        CoinView coinView = new CoinView(activityTestRule.getActivity());

        coinView.setCoinType(COIN_NICKEL);

        assertEquals(NICKEL, coinView.getCoin());
    }

    @Test
    public void setCoinTypeSetsCoinDefaultsToPenny() {
        CoinView coinView = new CoinView(activityTestRule.getActivity());

        coinView.setCoinType(14142);

        assertEquals(PENNY, coinView.getCoin());
    }

    private void assertCenterGravity(CoinView coinView) {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) coinView.getLayoutParams();

        assertEquals(CENTER, params.gravity);
    }


}