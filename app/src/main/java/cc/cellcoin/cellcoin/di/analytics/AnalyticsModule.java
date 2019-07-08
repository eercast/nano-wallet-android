package cc.cellcoin.cellcoin.di.analytics;

import android.content.Context;

import cc.cellcoin.cellcoin.analytics.AnalyticsService;
import cc.cellcoin.cellcoin.di.application.ApplicationScope;
import cc.cellcoin.cellcoin.di.persistence.PersistenceModule;

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
