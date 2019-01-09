package com.example.akshitagupta.project3c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context arg0, Intent arg1) {
        // TODO Auto-generated method stub
        Bundle l = arg1.getExtras();
        String data = l.getString("buttonname");

        if (data.equals("San Francisco")) {
            Intent intent = new Intent(arg0, AttractionsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            arg0.startActivity(intent);
        }
        else if(data.equals("New York")) {
            Intent intent = new Intent(arg0, Attractions2.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            arg0.startActivity(intent);        }
    }
}
