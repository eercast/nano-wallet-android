package com.eercast.cellcoin.util;

import com.eercast.cellcoin.analytics.AnalyticsService;
import timber.log.Timber;

public class ExceptionHandler {
    public static void handle(Throwable t) {
        if (t != null) {
            // log to crashlytics
            AnalyticsService.trackCustomException(t);

            // Log to console
            Timber.e(t.getMessage());
        }
    }
}