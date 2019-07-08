package cc.cellcoin.cellcoin.ui.home;

import com.airbnb.epoxy.Typed2EpoxyController;

import java.util.List;

import cc.cellcoin.cellcoin.TransactionBindingModel_;
import cc.cellcoin.cellcoin.network.model.response.AccountHistoryResponseItem;

/**
 * Controller to handle adding and removing views on the wallet
 */

public class WalletController extends Typed2EpoxyController<List<AccountHistoryResponseItem>, HomeFragment.ClickHandlers> {

    @Override
    protected void buildModels(List<AccountHistoryResponseItem> accountHistoryResponses, HomeFragment.ClickHandlers clickHandlers) {
        for (AccountHistoryResponseItem accountHistoryItem: accountHistoryResponses) {
            new TransactionBindingModel_()
                    .id(accountHistoryItem.getHash())
                    .accountHistoryItem(accountHistoryItem)
                    .handlers(clickHandlers)
                    .addTo(this);
        }
    }
}
