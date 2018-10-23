package com.eercast.cellcoin.di.application;


import javax.inject.Named;

import com.eercast.cellcoin.analytics.AnalyticsService;
import com.eercast.cellcoin.di.analytics.AnalyticsModule;
import com.eercast.cellcoin.di.persistence.PersistenceModule;
import com.eercast.cellcoin.util.SharedPreferencesUtil;
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
