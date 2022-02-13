package com.sharews.online;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button mButtonToggle;
    private Button mButtonReverseVNC;
    private TextView mAddress;
    private boolean mIsMainServiceRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonToggle = findViewById(R.id.toggle);
        mAddress = findViewById(R.id.address);
        mButtonReverseVNC = findViewById(R.id.reverse_vnc);

        mButtonToggle.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MainService.class);
            if(mIsMainServiceRunning) {
                intent.setAction(MainService.ACTION_STOP);
            }
            else {
                intent.setAction(MainService.ACTION_START);
            }
            mButtonToggle.setEnabled(false);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent);
            } else {
                startService(intent);
            }
        });

        mButtonReverseVNC.setOnClickListener(view -> {

        });
    }
}