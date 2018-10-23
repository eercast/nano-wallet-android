package com.eercast.cellcoin.di.analytics;

import android.content.Context;

import com.eercast.cellcoin.analytics.AnalyticsService;
import com.eercast.cellcoin.di.application.ApplicationScope;
import com.eercast.cellcoin.di.persistence.PersistenceModule;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module(includes = PersistenceModule.class)
public class AnalyticsModule {
    @Provides
    @ApplicationScope
    AnalyticsService providesAnalyticsService(Context context, Realm realm) {
        return new AnalyticsService(context.getApplicationContext(), realm);
    }
}
