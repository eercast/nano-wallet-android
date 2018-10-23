package com.eercast.cellcoin.ui.intro;

import android.app.AlertDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ajalt.reprint.core.Reprint;
import com.hwangjr.rxbus.annotation.Subscribe;

import java.util.HashMap;

import javax.inject.Inject;

import com.eercast.cellcoin.R;
import com.eercast.cellcoin.analytics.AnalyticsEvents;
import com.eercast.cellcoin.analytics.AnalyticsService;
import com.eercast.cellcoin.broadcastreceiver.ClipboardAlarmReceiver;
import com.eercast.cellcoin.bus.CreatePin;
import com.eercast.cellcoin.bus.RxBus;
import com.eercast.cellcoin.databinding.FragmentIntroNewWalletBinding;
import com.eercast.cellcoin.model.Credentials;
import com.eercast.cellcoin.network.AccountService;
import com.eercast.cellcoin.ui.common.ActivityWithComponent;
import com.eercast.cellcoin.ui.common.BaseFragment;
import com.eercast.cellcoin.ui.common.FragmentUtility;
import com.eercast.cellcoin.ui.common.WindowControl;
import com.eercast.cellcoin.ui.home.HomeFragment;
import com.eercast.cellcoin.util.ExceptionHandler;
import com.eercast.cellcoin.util.SharedPreferencesUtil;
import io.realm.Realm;

/**
 * The Intro Screen to the app
 */

public class IntroNewWalletFragment extends BaseFragment {
    public static String TAG = IntroNewWalletFragment.class.getSimpleName();
    private String seed;

    @Inject
    Realm realm;

    @Inject
    AccountService accountService;

    @Inject
    SharedPreferencesUtil sharedPreferencesUtil;

    @Inject
    AnalyticsService analyticsService;

    /**
     * Create new instance of the fragment (handy pattern if any data needs to be passed to it)
     *
     * @return IntroNewWalletFragment instance
     */
    public static IntroNewWalletFragment newInstance() {
        Bundle args = new Bundle();
        IntroNewWalletFragment fragment = new IntroNewWalletFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // init dependency injection
        if (getActivity() instanceof ActivityWithComponent) {
            ((ActivityWithComponent) getActivity()).getActivityComponent().inject(this);
        }

        analyticsService.track(AnalyticsEvents.SEED_CONFIRMATION_VIEWED);

        // inflate the view
        FragmentIntroNewWalletBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_intro_new_wallet, container, false);
        view = binding.getRoot();

        setStatusBarWhite(view);
        hideToolbar();

        // subscribe to bus
        RxBus.get().register(this);

        // get seed from storage
        Credentials credentials = realm.where(Credentials.class).findFirst();
        if (credentials != null) {
            seed = credentials.getSeed();
            binding.setSeed(seed);
        } else {
            ExceptionHandler.handle(new Exception("Problem accessing generated seed"));
        }

        // bind data to view
        binding.setHandlers(new ClickHandlers());
        binding.introNewWalletMessage.setText(Html.fromHtml(getString(R.string.intro_new_wallet_message)));

        accountService.open();

//        if (credentials != null && !credentials.getHasAnsweredAnalyticsQuestion()) {
//            showAnalyticsOptIn(analyticsService, realm);
//        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // unregister from bus
        RxBus.get().unregister(this);
    }

    private void goToHomeScreen() {
        // set confirm flag
        sharedPreferencesUtil.setConfirmedSeedBackedUp(true);

        // go to home screen
        if (getActivity() instanceof WindowControl) {
            ((WindowControl) getActivity()).getFragmentUtility().replace(
                    HomeFragment.newInstance(),
                    FragmentUtility.Animation.ENTER_LEFT_EXIT_RIGHT,
                    FragmentUtility.Animation.ENTER_RIGHT_EXIT_LEFT,
                    HomeFragment.TAG
            );
        }
    }

    @Subscribe
    public void receiveCreatePin(CreatePin createPin) {
        realm.beginTransaction();
        Credentials credentials = realm.where(Credentials.class).findFirst();
        if (credentials != null) {
            credentials.setPin(createPin.getPin());
        }
        realm.commitTransaction();
        goToHomeScreen();
    }

    public class ClickHandlers {

        /**
         * Confirm button listener
         *
         * @param view View
         */
        public void onClickConfirm(View view) {
            analyticsService.track(AnalyticsEvents.SEED_CONFIRMATON_CONTINUE_BUTTON_PRESSED);

            // show the copy seed dialog
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Light_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(getContext());
            }
            builder.setTitle(R.string.intro_new_wallet_continue_title)
                    .setMessage(R.string.intro_new_wallet_continue_message)
                    .setPositiveButton(R.string.intro_new_wallet_continue_positive, (dialog, which) -> {
                        if (!Reprint.isHardwarePresent() || !Reprint.hasFingerprintRegistered()) {
                            // if no fingerprint software is present or user has not registered
                            // a fingerprint show pin screen
                            showCreatePinScreen();
                        } else {
                            // otherwise, go on in
                            goToHomeScreen();
                        }
                    })
                    .setNegativeButton(R.string.intro_new_wallet_continue_negative, (dialog, which) -> {

                    })
                    .show();
        }

        /**
         * Seed Click Listener
         *
         * @param view View
         */
        public void onClickSeed(View view) {
            final HashMap<String, String> customData = new HashMap<>();
            customData.put("location", "seed confirmation");
            analyticsService.track(AnalyticsEvents.SEED_COPIED, customData);

            // copy address to clipboard
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText(ClipboardAlarmReceiver.CLIPBOARD_NAME, seed);
            if (clipboard != null) {
                clipboard.setPrimaryClip(clip);
            }
            // clear clipboard in 2 minutes
            setClearClipboardAlarm();

            // show the copy seed dialog
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Light_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(getContext());
            }
            builder.setTitle(R.string.intro_new_wallet_seed_copied_title)
                    .setMessage(R.string.intro_new_wallet_seed_copied)
                    .setPositiveButton(R.string.intro_new_wallet_seed_done, (dialog, which) -> {

                    })
                    .show();
        }
    }
}
