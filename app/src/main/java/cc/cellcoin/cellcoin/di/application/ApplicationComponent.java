package cc.cellcoin.cellcoin.di.application;


import javax.inject.Named;

import cc.cellcoin.cellcoin.analytics.AnalyticsService;
import cc.cellcoin.cellcoin.di.analytics.AnalyticsModule;
import cc.cellcoin.cellcoin.di.persistence.PersistenceModule;
import cc.cellcoin.cellcoin.util.SharedPreferencesUtil;
import dagger.Component;
import io.realm.Realm;

@Component(modules = {ApplicationModule.class, PersistenceModule.class, AnalyticsModule.class})
@ApplicationScope
public interface ApplicationComponent {
    // persistence module
    SharedPreferencesUtil provideSharedPreferencesUtil();

    // database
    Realm provideRealm();

    AnalyticsService provideAnalyticsService();

    // encryption key
    @Named("encryption_key")
    byte[] providesEncryptionKey();
}
