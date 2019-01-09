package com.example.akshitagupta.project3c;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Attractions2 extends MainActivity
{
    private String[] attractionUrls;
    private static final String MY_PERMISSION = "edu.uic.cs478.f18.project3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"Activity Under Construction, Come back Later!!", Toast.LENGTH_SHORT).show();

        if (ContextCompat.checkSelfPermission(this, MY_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{MY_PERMISSION}, 0);
        }
    }

    public void listItemClicked(int position) {
        updateBrowserFragment(attractionUrls[position]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //Inflate the menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        //Disable Attractions2 menu item because this is the current activity.
        MenuItem attractionsMenuItem = menu.findItem(R.id.attractions2MenuItem);
        attractionsMenuItem.setEnabled(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        //Determine which menu item was selected
        switch (menuItem.getItemId())
        {
            case (R.id.attractionsMenuItem):
                startAttractionsActivity();
                return true;
            case (R.id.attractions2MenuItem):
                //Do nothing, this is the current activity.
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void onRequestPermissionsResult(int code, String[] permissions, int[] results) {
        if (results[0] == PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Bummer: No permission", Toast.LENGTH_SHORT).show();
        }
    }

    // startAttractionsActivity: This method starts the SF Activity.
    private void startAttractionsActivity()
    {
        Intent intent = new Intent(this, AttractionsActivity.class);
        startActivity(intent);
    }
}
