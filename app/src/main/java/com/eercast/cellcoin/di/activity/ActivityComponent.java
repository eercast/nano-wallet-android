package com.eercast.cellcoin.di.activity;

import com.google.gson.Gson;

import com.eercast.cellcoin.MainActivity;
import com.eercast.cellcoin.di.application.ApplicationComponent;
import com.eercast.cellcoin.model.NanoWallet;
import com.eercast.cellcoin.network.AccountService;
import com.eercast.cellcoin.ui.home.HomeFragment;
import com.eercast.cellcoin.ui.intro.IntroLegalFragment;
import com.eercast.cellcoin.ui.intro.IntroNewWalletFragment;
import com.eercast.cellcoin.ui.intro.IntroSeedFragment;
import com.eercast.cellcoin.ui.intro.IntroWelcomeFragment;
import com.eercast.cellcoin.ui.pin.CreatePinDialogFragment;
import com.eercast.cellcoin.ui.pin.PinDialogFragment;
import com.eercast.cellcoin.ui.receive.ReceiveDialogFragment;
import com.eercast.cellcoin.ui.send.SendFragment;
import com.eercast.cellcoin.ui.settings.SettingsDialogFragment;
import dagger.Component;

@Component(modules = {ActivityModule.class}, dependencies = {ApplicationComponent.class})
@ActivityScope
public interface ActivityComponent {
    @ActivityScope
    AccountService provideAccountService();

    // wallet
    NanoWallet provideNanoWallet();

    @ActivityScope
    Gson provideGson();

    void inject(AccountService accountService);

    void inject(CreatePinDialogFragment createPinDialogFragment);

    void inject(HomeFragment homeFragment);

    void inject(IntroLegalFragment introLegalFragment);

    void inject(IntroNewWalletFragment introNewWalletFragment);

    void inject(IntroWelcomeFragment introWelcomeFragment);

    void inject(IntroSeedFragment introSeedFragment);

    void inject(MainActivity mainActivity);

    void inject(NanoWallet nanoWallet);

    void inject(PinDialogFragment pinDialogFragment);

    void inject(ReceiveDialogFragment receiveDialogFragment);

    void inject(SendFragment sendFragment);

    void inject(SettingsDialogFragment settingsDialogFragment);
}
