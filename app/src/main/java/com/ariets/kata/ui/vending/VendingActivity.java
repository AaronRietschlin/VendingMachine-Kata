package com.ariets.kata.ui.vending;

import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.ariets.kata.R;
import com.ariets.kata.model.DisplayProvider;
import com.ariets.kata.model.MoneyValidator;
import com.ariets.kata.model.Product;
import com.ariets.kata.model.ProductDispenser;
import com.ariets.kata.model.VendingMachine;
import com.ariets.kata.ui.widget.CoinView;
import com.ariets.kata.ui.widget.ProductTextView;
import com.ariets.kata.utils.Injector;
import com.ariets.kata.utils.KeyboardUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

import static android.support.design.widget.Snackbar.LENGTH_LONG;

/**
 * The Activity (UI) for the Vending Machine.
 */
public class VendingActivity extends AppCompatActivity implements VendingMachineContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.vending_tv_display)
    TextView tvDisplay;
    @BindView(R.id.vending_tv_coin_return_label)
    TextView tvCoinReturn;
    @BindView(R.id.vending_field_custom_value)
    TextInputLayout tilCustomValueField;
    @BindView(R.id.vending_check_exact_change)
    CheckBox checkboxExactChange;

    private MenuItem moneyMenuItem;
    private Injector injector;
    private VendingMachineContract.Presenter<VendingMachineContract.View> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        injector = new Injector();
        setSupportActionBar(toolbar);
        setupPresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.vending, menu);
        moneyMenuItem = menu.findItem(R.id.menu_money_label);
        presenter.getInitialValue();
        return true;
    }

    private void setupPresenter() {
        if (presenter != null) {
            presenter.detachView();
        }
        double initialValue = checkboxExactChange.isChecked() ? 0.00 : 100;
        MoneyValidator moneyValidator = injector.provideMoneyValidator();
        DisplayProvider displayProvider = injector.provideAndroidMoneyValidator(this);
        ProductDispenser productDispenser = injector.provideProductDispenser();
        VendingMachine vendingMachine = injector.provideVendingMachine(moneyValidator, displayProvider, productDispenser, initialValue);
        presenter = injector.providePresenter(vendingMachine, displayProvider);
        presenter.attachView(this);
        presenter.initialize();
    }

    @OnClick({
            R.id.coin_view_quarter,
            R.id.coin_view_dime,
            R.id.coin_view_nickel,
            R.id.coin_view_penny
    })
    public void onCoinClicked(CoinView view) {
        resetChange();
        presenter.insertCoin(view.getCoin());
    }

    @OnClick({
            R.id.vending_product_candy,
            R.id.vending_product_chips,
            R.id.vending_product_cola,
    })
    public void onProductClicked(ProductTextView view) {
        presenter.selectProduct(view.getProduct());
        resetChange();
    }

    @OnClick(R.id.vending_btn_custom_value)
    public void onCustomValueButtonClicked() {
        EditText editText = tilCustomValueField.getEditText();
        String value = editText.getText().toString();
        if (!TextUtils.isEmpty(value)) {
            resetChange();
            presenter.insertCustomValue(value);
        }
        editText.setText("");
        KeyboardUtils.removeKeyboard(editText);
    }

    @OnClick(R.id.vending_btn_coin_return)
    public void onReturnCoinsClicked() {
        resetChange();
        presenter.returnCoins();
    }

    @OnCheckedChanged(R.id.vending_check_exact_change)
    public void onExactChangeChecked(boolean checked) {
        setupPresenter();
    }

    @Override
    public void setCurrentValue(String currentValue) {
        if (moneyMenuItem != null) {
            moneyMenuItem.setTitle(currentValue);
        }
    }

    @Override
    public void returnChange(String change) {
        tvCoinReturn.setText(getString(R.string.coin_return_value, change));
    }

    @Override
    public void onSuccess(Product product) {
        Snackbar.make(toolbar, getString(R.string.enjoy_your_product, product), LENGTH_LONG).show();
    }

    @Override
    public void onError(VendingError error, String... params) {
        @StringRes int errorStringRes = 0;
        switch (error) {
            case NO_COINS:
                errorStringRes = R.string.no_coins;
                break;
            case INVALID_COIN:
                errorStringRes = R.string.invalid_coin;
                break;
            case PRODUCT_SOLD_OUT:
                errorStringRes = R.string.product_sold_out;
                break;
            case INSUFFICIENT_FUNDS:
                errorStringRes = R.string.insufficient_funds;
                break;
            case EXACT_CHANGE:
                errorStringRes = R.string.exact_change;
                break;
        }
        if (params == null) {
            Snackbar.make(toolbar, errorStringRes, LENGTH_LONG).show();
        } else {
            Snackbar.make(toolbar, getString(errorStringRes, params), LENGTH_LONG).show();
        }
    }

    @Override
    public void setCurrentDisplay(String display) {
        tvDisplay.setText(display);
        String insertCoin = getString(R.string.display_insert_coin);
        if (!TextUtils.equals(display, insertCoin)) {
            delayAndDisplay(insertCoin);
        }
    }

    private void resetChange() {
        tvCoinReturn.setText("");
    }

    private void delayAndDisplay(final String textToDisplay) {
        tvDisplay.postDelayed(() -> setCurrentDisplay(textToDisplay), 3000);
    }

}
