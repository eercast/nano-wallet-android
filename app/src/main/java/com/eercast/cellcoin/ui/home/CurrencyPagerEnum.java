package com.eercast.cellcoin.ui.home;

import com.eercast.cellcoin.R;

/**
 * View Pager types
 */

public enum CurrencyPagerEnum {
    NANO(R.layout.view_home_amount_nano),
//    BTC(R.layout.view_home_amount_btc),
    LOCAL(R.layout.view_home_amount_local_currency);

    private int mLayoutResId;

    CurrencyPagerEnum(int layoutResId) {
        mLayoutResId = layoutResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }
}
