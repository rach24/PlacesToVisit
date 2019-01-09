package com.example.akshitagupta.project3c;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class AttractionsActivity extends MainActivity
{
    private String[] attractionUrls;
    private static final String MY_PERMISSION = "edu.uic.cs478.f18.project3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the attraction names and URLs from the strings resource file
        attractionUrls = getResources().getStringArray(R.array.attractionUrls);
        String[] attractionNames = getResources().getStringArray(R.array.attractions);

        //Provide the layout files to the two fragments
        initializeListItemsFragment(R.id.listItemsFragmentContainer, attractionNames);
        initializeBrowserFragment(R.id.browserFragmentContainer);

        if (ContextCompat.checkSelfPermission(this, MY_PERMISSION) == PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{MY_PERMISSION}, 0);
        }
    }


    // listItemClicked: This method responds to a list item being clicked
    // by sending the correct URL to the browser fragment.
    public void listItemClicked(int position) {
        updateBrowserFragment(attractionUrls[position]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //Inflate the menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        //Disable Attractions menu item because this is the current activity.
        MenuItem attractionsMenuItem = menu.findItem(R.id.attractionsMenuItem);
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
                //Do nothing, this is the current activity.
                return true;
            case (R.id.attractions2MenuItem):
               //Toast.makeText(AttractionsActivity.this,"Under Construction!!", Toast.LENGTH_LONG).show();
                startAttractions2Activity();
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

    private void startAttractions2Activity()
    {
        Intent intent = new Intent(this, Attractions2.class);
        startActivity(intent);
    }
}
