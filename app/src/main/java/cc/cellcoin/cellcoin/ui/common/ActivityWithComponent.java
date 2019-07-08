package cc.cellcoin.cellcoin.ui.common;

import cc.cellcoin.cellcoin.di.activity.ActivityComponent;
import cc.cellcoin.cellcoin.di.application.ApplicationComponent;

/**
 * Interface for Activity with a Component
 */

public interface ActivityWithComponent {
    ActivityComponent getActivityComponent();
    ApplicationComponent getApplicationComponent();
}
