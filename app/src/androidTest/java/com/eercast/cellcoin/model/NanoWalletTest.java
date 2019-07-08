package cc.cellcoin.cellcoin.model;

import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.UiThreadTest;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import javax.inject.Inject;

import cc.cellcoin.cellcoin.NanoApplication;
import cc.cellcoin.cellcoin.di.activity.ActivityModule;
import cc.cellcoin.cellcoin.di.activity.DaggerTestActivityComponent;
import cc.cellcoin.cellcoin.di.activity.TestActivityComponent;
import cc.cellcoin.cellcoin.util.SharedPreferencesUtil;

/**
 * Test the Nano Utility functions
 */


@RunWith(AndroidJUnit4.class)
public class NanoWalletTest extends InstrumentationTestCase {
    private TestActivityComponent testActivityComponent;

    @Inject
    SharedPreferencesUtil sharedPreferencesUtil;

    @Inject
    NanoWallet nanoWallet;

    public NanoWalletTest() {
    }

    @Before
    @UiThreadTest
    public void setUp() throws Exception {
        super.setUp();
        // build the activity component
        testActivityComponent = DaggerTestActivityComponent
                .builder()
                .applicationComponent(NanoApplication.getApplication(InstrumentationRegistry.getTargetContext().getApplicationContext()).getApplicationComponent())
                .activityModule(new ActivityModule(InstrumentationRegistry.getTargetContext()))
                .build();

        testActivityComponent.inject(this);
    }

    @Test
    @UiThreadTest
    public void setLocalCurrencyAmount() throws Exception {
        testActivityComponent.inject(nanoWallet);
        nanoWallet.setLocalCurrencyPrice(new BigDecimal("11.0402274899"));
        for (AvailableCurrency currency : AvailableCurrency.values()) {
            // set each potential currency
            sharedPreferencesUtil.setLocalCurrency(currency);
            nanoWallet.setAccountBalance(new BigDecimal("123414233000000000000000000000000000000"));
            nanoWallet.setSendNanoAmount(nanoWallet.getLongerAccountBalanceNano());
            Log.d("NanoWalletTest", currency.getLocale().toString() + " " + nanoWallet.getSendNanoAmountFormatted() + " " + nanoWallet.getSendLocalCurrencyAmountFormatted());
        }
    }


    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
