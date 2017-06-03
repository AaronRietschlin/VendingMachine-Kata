package com.ariets.kata.ui.vending;

import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.ariets.kata.R;
import com.ariets.kata.model.DisplayProvider;
import com.ariets.kata.model.MoneyValidator;
import com.ariets.kata.model.ProductDispenser;
import com.ariets.kata.model.VendingMachine;
import com.ariets.kata.ui.widget.CoinView;
import com.ariets.kata.ui.widget.ProductTextView;
import com.ariets.kata.utils.Injector;
import com.ariets.kata.utils.KeyboardUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.support.design.widget.Snackbar.LENGTH_LONG;

public class VendingActivity extends AppCompatActivity implements VendingMachineContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.vending_tv_display)
    TextView tvDisplay;
    @BindView(R.id.vending_tv_coin_return_label)
    TextView tvCoinReturn;
    @BindView(R.id.vending_field_custom_value)
    TextInputLayout tilCustomValueField;

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
        presenter.attachView(this);
        tvDisplay.setText(presenter.getInitialDisplay());
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
        getMenuInflater().inflate(R.menu.vending, menu);
        moneyMenuItem = menu.findItem(R.id.menu_money_label);
        presenter.getInitialValue();
        return super.onCreateOptionsMenu(menu);
    }

    private void setupPresenter() {
        MoneyValidator moneyValidator = injector.provideMoneyValidator();
        DisplayProvider displayProvider = injector.provideAndroidMoneyValidator(this);
        ProductDispenser productDispenser = injector.provideProductDispenser();
        VendingMachine vendingMachine = injector.provideVendingMachine(moneyValidator, displayProvider, productDispenser);
        presenter = injector.providePresenter(vendingMachine, displayProvider);
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
        Snackbar.make(toolbar, "Product selected: " + view.getProduct(), LENGTH_LONG).show();
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
    public void onError(VendingError error) {
        @StringRes int errorStringRes = 0;
        switch (error) {
            case NO_COINS:
                errorStringRes = R.string.no_coins;
                break;
            case INVALID_COIN:
                errorStringRes = R.string.invalid_coin;
                break;
        }
        Snackbar.make(toolbar, errorStringRes, LENGTH_LONG).show();
    }

    private void resetChange() {
        tvCoinReturn.setText("");
    }

}
