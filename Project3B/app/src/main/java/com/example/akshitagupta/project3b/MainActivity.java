package com.example.akshitagupta.project3b;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String MY_PERMISSION = "edu.uic.cs478.f18.project3";
    private static final String TOAST_INTENT ="edu.uic.cs478.f18.project3.receiverintent";
    private static final String TOAST_INTENT2 ="edu.uic.cs478.f18.project3.receiverintent2";
    private MyReceiver mReceiver1 ;
    private MyReceiver2 mReceiver2 ;
    private IntentFilter mFilter1 ;
    private IntentFilter mFilter2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mReceiver1 = new MyReceiver() ;
        mReceiver2 = new MyReceiver2() ;
        mFilter1 = new IntentFilter(TOAST_INTENT) ;
        mFilter1.setPriority(10);
        mFilter2 = new IntentFilter(TOAST_INTENT2) ;
        mFilter2.setPriority(20);
        registerReceiver(mReceiver1, mFilter1) ;
        registerReceiver(mReceiver2, mFilter2) ;

        final Button b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkPermission();
            }
        });
    }

    private void checkPermission() {

            if (ContextCompat.checkSelfPermission(this, MY_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{MY_PERMISSION}, 0);
            }
    }

    public void onRequestPermissionsResult(int code, String[] permissions, int[] results) {
            if (results[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Bummer: No permission", Toast.LENGTH_SHORT).show();
            }
        }
    }


