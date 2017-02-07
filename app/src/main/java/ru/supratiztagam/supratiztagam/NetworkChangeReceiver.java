package ru.supratiztagam.supratiztagam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Created by muxammed on 26.01.2017.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

    public static final String NETWORK_AVAILABLE_ACTION = "ru.supratiztagam.supratiztagam";
    public static final String IS_NETWORK_AVAILABLE = "isNetworkAvailable";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent networkStateIntent = new Intent(NETWORK_AVAILABLE_ACTION);
        networkStateIntent.putExtra(IS_NETWORK_AVAILABLE,  isConnectedToInternet(context));
        LocalBroadcastManager.getInstance(context).sendBroadcast(networkStateIntent);
    }

    private boolean isConnectedToInternet(Context context) {
        try {
            if (context != null) {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                return networkInfo != null && networkInfo.isConnected();
            }
            return false;
        } catch (Exception e) {
            Log.e(NetworkChangeReceiver.class.getName(), e.getMessage());
            return false;
        }
    }
}
