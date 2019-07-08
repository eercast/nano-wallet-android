package cc.cellcoin.cellcoin.di.activity;

import com.google.gson.Gson;

import cc.cellcoin.cellcoin.MainActivity;
import cc.cellcoin.cellcoin.di.application.ApplicationComponent;
import cc.cellcoin.cellcoin.model.NanoWallet;
import cc.cellcoin.cellcoin.network.AccountService;
import cc.cellcoin.cellcoin.ui.home.HomeFragment;
import cc.cellcoin.cellcoin.ui.intro.IntroLegalFragment;
import cc.cellcoin.cellcoin.ui.intro.IntroNewWalletFragment;
import cc.cellcoin.cellcoin.ui.intro.IntroSeedFragment;
import cc.cellcoin.cellcoin.ui.intro.IntroWelcomeFragment;
import cc.cellcoin.cellcoin.ui.pin.CreatePinDialogFragment;
import cc.cellcoin.cellcoin.ui.pin.PinDialogFragment;
import cc.cellcoin.cellcoin.ui.receive.ReceiveDialogFragment;
import cc.cellcoin.cellcoin.ui.send.SendFragment;
import cc.cellcoin.cellcoin.ui.settings.SettingsDialogFragment;
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
