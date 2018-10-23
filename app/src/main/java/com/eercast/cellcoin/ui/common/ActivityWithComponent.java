package com.eercast.cellcoin.ui.common;

import com.eercast.cellcoin.di.activity.ActivityComponent;
import com.eercast.cellcoin.di.application.ApplicationComponent;

/**
 * Interface for Activity with a Component
 */

public interface ActivityWithComponent {
    ActivityComponent getActivityComponent();
    ApplicationComponent getApplicationComponent();
}
