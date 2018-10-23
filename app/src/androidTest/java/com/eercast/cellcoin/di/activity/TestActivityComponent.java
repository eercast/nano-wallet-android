package com.eercast.cellcoin.di.activity;

import com.eercast.cellcoin.di.application.ApplicationComponent;
import com.eercast.cellcoin.model.NanoWalletTest;
import dagger.Component;

@Component(modules = {ActivityModule.class}, dependencies = {ApplicationComponent.class})
@ActivityScope
public interface TestActivityComponent extends ActivityComponent {
    void inject(NanoWalletTest nanoWalletTest);
}
