package edu.uic.cs478.Project3A;

// import edu.uic.cs478.BroadcastReceiver1.R;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity
{
    private static final String MY_PERMISSION = "edu.uic.cs478.f18.project3" ;
    private static int m = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Button b1 = (Button) findViewById(R.id.button1);
        final Button b2 = (Button) findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkPermissionAndBroadcast(1);
            }
        }) ;
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkPermissionAndBroadcast(2);
            }
        }) ;

    }
    private void checkPermissionAndBroadcast(int i) {

        if(i == 1) {
            m = 1;
            if (ContextCompat.checkSelfPermission(this, MY_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
                Intent aIntent = new Intent("edu.uic.cs478.f18.project3.receiverintent");
                aIntent.putExtra("buttonname","San Francisco");
                sendOrderedBroadcast(aIntent,MY_PERMISSION);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{MY_PERMISSION}, 0);
            }
        }
        else if(i == 2){
            m = 2;
            if (ContextCompat.checkSelfPermission(this, MY_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
                Intent aIntent = new Intent("edu.uic.cs478.f18.project3.receiverintent2");
                aIntent.putExtra("buttonname","New York");
                sendOrderedBroadcast(aIntent,MY_PERMISSION);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{MY_PERMISSION}, 0);
            }
        }

    }

    public void onRequestPermissionsResult(int code, String[] permissions, int[] results) {
        if (results.length > 0) {
            if (results[0] == PackageManager.PERMISSION_GRANTED) {
                if (m == 1) {
                    Intent aIntent = new Intent("edu.uic.cs478.f18.project3.receiverintent");
                    aIntent.putExtra("buttonname","San Francisco");
                    Toast.makeText(this, "Permission Granted to explore San Francisco", Toast.LENGTH_SHORT).show();
                    sendOrderedBroadcast(aIntent,MY_PERMISSION);
                }
                else if (m == 2){
                    Intent aIntent = new Intent("edu.uic.cs478.f18.project3.receiverintent2");
                    aIntent.putExtra("buttonname","New York");
                    Toast.makeText(this, "Permission Granted to explore New York", Toast.LENGTH_SHORT).show();
                    sendOrderedBroadcast(aIntent,MY_PERMISSION);
                }
            }
            else {
                Toast.makeText(this, "Bummer: No permission", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
