package com.sharews.online;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.IBinder;
import android.util.Log;

public class MainService extends Service {
    private static final String TAG = "MainService";
    final static String ACTION_START = "start";
    final static String ACTION_STOP = "stop";

    final static String ACTION_HANDLE_MEDIA_PROJECTION_RESULT = "action_handle_media_projection_result";
    final static String EXTRA_MEDIA_PROJECTION_RESULT_DATA = "result_data_media_projection";
    final static String EXTRA_MEDIA_PROJECTION_RESULT_CODE = "result_code_media_projection";
    final static String ACTION_HANDLE_INPUT_RESULT = "action_handle_a11y_result";
    final static String EXTRA_INPUT_RESULT = "result_a11y";
    final static String ACTION_HANDLE_WRITE_STORAGE_RESULT = "action_handle_write_storage_result";
    final static String EXTRA_WRITE_STORAGE_RESULT = "result_write_storage";

    private MediaProjectionManager mMediaProjectionManager;
    private MediaProjection mMediaProjection;
    private int mResultCode;
    private Intent mResultData;

    public enum StatusEvent {
        STARTED,
        STOPPED,
    }

    public MainService() {
    }

    @Override
    public void onCreate() {
        mMediaProjectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
        Log.i(TAG, "onCreate: MainService");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (ACTION_HANDLE_MEDIA_PROJECTION_RESULT.equals(intent.getAction())) {
            Log.d(TAG, "onStartCommand: handle media projection result");
            // Step 4 (optional): coming back from capturing permission check, now starting capturing machinery
            mResultCode = intent.getIntExtra(EXTRA_MEDIA_PROJECTION_RESULT_CODE, 0);
            mResultData = intent.getParcelableExtra(EXTRA_MEDIA_PROJECTION_RESULT_DATA);
            startScreenCapture();
        }

        if (ACTION_HANDLE_WRITE_STORAGE_RESULT.equals(intent.getAction())) {
            Log.d(TAG, "onStartCommand: handle write storage result");
            // Step 3: coming back from write storage permission check, start capturing
            // or ask for ask for capturing permission first (then going in step 4)
            if (mResultCode != 0 && mResultData != null) {
                startScreenCapture();
            } else {
                Log.i(TAG, "Requesting confirmation");
                // This initiates a prompt dialog for the user to confirm screen projection.
                Intent mediaProjectionRequestIntent = new Intent(this, MediaProjectionRequestActivity.class);
                mediaProjectionRequestIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(mediaProjectionRequestIntent);
            }
        }

        Log.i(TAG, "onStartCommand: MainService");
        return super.onStartCommand(intent, flags, startId);
    }

    private void startScreenCapture() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}