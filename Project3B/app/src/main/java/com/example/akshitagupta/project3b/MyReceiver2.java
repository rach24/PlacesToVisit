package com.example.akshitagupta.project3b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceiver2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context arg0, Intent arg1) {
        Bundle l = arg1.getExtras();
        String data = l.getString("buttonname");
        Toast.makeText(arg0, data, Toast.LENGTH_LONG).show();

    }
}