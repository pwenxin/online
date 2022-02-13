package com.sharews.online;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MainService extends Service {
    private static final String TAG = "MainService";
    final static String ACTION_START = "start";
    final static String ACTION_STOP = "stop";

    public MainService() {
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate: Mainrservice");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}