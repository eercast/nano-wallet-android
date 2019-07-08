package cc.cellcoin.cellcoin.di.activity;

import cc.cellcoin.cellcoin.di.application.ApplicationComponent;
import cc.cellcoin.cellcoin.model.NanoWalletTest;
import dagger.Component;

@Component(modules = {ActivityModule.class}, dependencies = {ApplicationComponent.class})
@ActivityScope
public interface TestActivityComponent extends ActivityComponent {
    void inject(NanoWalletTest nanoWalletTest);
}
