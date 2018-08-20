package com.ngti.leandro.lol.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import timber.log.Timber;

public class CheckNetwork {


    private static final String TAG = CheckNetwork.class.getSimpleName();


    public static boolean isInternetAvailable(Context context) {
        NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (info == null) {
            Timber.d(TAG, "no internet connection");
            return false;
        } else {
            if (info.isConnected()) {
                Timber.d(TAG, " internet connection available...");
                return true;
            } else {
                Timber.d(TAG, " internet connection");
                return true;
            }

        }
    }
}
