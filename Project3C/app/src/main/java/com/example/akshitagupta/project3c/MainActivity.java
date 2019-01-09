package com.example.akshitagupta.project3c;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public abstract class MainActivity extends AppCompatActivity implements ListItemsFragment.ListItemClickListener
    {
        private BrowserFragment browserFragment;
        private ListItemsFragment listItemsFragment;
        private FrameLayout listItemsFrameLayout, browserFrameLayout;
        private FragmentManager fragmentManager;
        private int browserFragmentResourceID;
        private final String BROWSER_FRAGMENT_TAG = "Browser_Fragment_Tag";
        private final String LIST_ITEM_FRAGMENT_TAG = "List_Item_Fragment_Tag";


        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);

            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setLogo(R.mipmap.ic_launcher);
            getSupportActionBar().setDisplayUseLogoEnabled(true);


            //Set the fragment manager's onBackStackChangedListener
            fragmentManager = getFragmentManager();
            fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                public void onBackStackChanged() {
                    adjustFragmentLayouts();
                }
            });
        }


        // This method must be implemented by the subclass to specify
        // what to do when a list item is clicked on.
        public abstract void listItemClicked(int position);


        // adjustFragmentLayouts: This method changes the width and heights of the
        // fragments depending on the orientation of the device and whether or not
        // the browser fragment has been added.
        private void adjustFragmentLayouts()
        {
            //Check if the browser fragment has been added
            if(browserFragment.isAdded())
            {
                //Check the device orientation
                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                    configureFragmentLayoutPortrait();
                else
                    configureFragmentLayoutLandscape();
            }
            else
            {
                //Clear the list item selection from listItemsFragment
                if(listItemsFragment != null && listItemsFragment.isAdded())
                    listItemsFragment.clearListItemSelection();

                configureFragmentLayoutBrowserNotAdded();
            }
        }


        // configureFragmentLayoutPortrait: This method sets the width and height of the
        // two fragments to display properly in portrait mode.
        private void configureFragmentLayoutPortrait()
        {
            listItemsFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT));

            browserFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,
                    MATCH_PARENT));
        }


        // configureFragmentLayoutLandscape: This method sets the width and height of the
        // two fragments to display properly in landscape mode.
        private void configureFragmentLayoutLandscape()
        {
            listItemsFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT, 1f));

            browserFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT, 2f));
        }


        // configureFragmentLayoutBrowserNotAdded: This method sets the width and height of the
        // two fragments to display properly when the browser fragment has not been added.
        private void configureFragmentLayoutBrowserNotAdded()
        {
            listItemsFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT,
                    MATCH_PARENT));

            browserFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(0,
                    MATCH_PARENT));
        }


        // initializeListItemsFragment: This method sets up the ListItemsFragment to be
        // displayed.  If a ListItem fragment already existed, that fragment will be used
        // instead of creating a new one.
        protected void initializeListItemsFragment(int resourceID, String[] listItemNames)
        {
            listItemsFrameLayout = (FrameLayout) findViewById(resourceID);

            //Get the previous ListItems fragment if one existed.
            listItemsFragment = (ListItemsFragment)fragmentManager.findFragmentByTag(LIST_ITEM_FRAGMENT_TAG);

            //If a previous fragment didn't exist add a new one.
            if(listItemsFragment == null)
            {
                //Begin a FragmentTransaction to add ListItemsFragment
                FragmentTransaction addListItemsFragmentTransaction = fragmentManager.beginTransaction();

                //Create bundle to pass ID of array to ListItemFragment
                Bundle bundle = new Bundle();
                bundle.putStringArray(ListItemsFragment.ARRAY_ID_EXTRA_KEY, listItemNames);

                //Create new fragment
                listItemsFragment = new ListItemsFragment();
                listItemsFragment.setArguments(bundle);

                //Add the new fragment to the container.
                addListItemsFragmentTransaction.replace(resourceID, listItemsFragment, LIST_ITEM_FRAGMENT_TAG);

                //Commit the transaction
                addListItemsFragmentTransaction.commit();
            }
        }


        // initializeBrowserFragment: This method creates a new BrowserFragment and sets
        // it up to be displayed.
        protected void initializeBrowserFragment(int resourceID)
        {
            this.browserFragmentResourceID = resourceID;
            browserFrameLayout = (FrameLayout) findViewById(browserFragmentResourceID);
            browserFragment = new BrowserFragment();
            createNewBrowserFragment();
        }


        // addBrowserFragment: This method adds the current BrowserFragment to the display.
        private void addBrowserFragment()
        {
            //Begin the transaction
            FragmentTransaction addBrowserFragmentTransaction = fragmentManager.beginTransaction();

            //Add the fragment to the FrameLayout
            addBrowserFragmentTransaction.add(browserFragmentResourceID, browserFragment, BROWSER_FRAGMENT_TAG);
            addBrowserFragmentTransaction.addToBackStack(null);

            //Commit the transaction
            addBrowserFragmentTransaction.commit();
            fragmentManager.executePendingTransactions();
        }


        // createNewBrowserFragment: This method creates a new BrowserFragment to be displayed.
        // if an existing BrowserFragment already exists, that one will be used instead.
        private void createNewBrowserFragment()
        {
            //Get the previous BrowserFragment if one existed.
            browserFragment = (BrowserFragment)fragmentManager.findFragmentByTag(BROWSER_FRAGMENT_TAG);

            //Create a new browser fragment if one does not exist.
            if(browserFragment == null)
                browserFragment = new BrowserFragment();

            adjustFragmentLayouts();
        }


        // updateBrowserFragment: This method updates the browser fragment to
        // show the specified URL.
        protected void updateBrowserFragment(String url)
        {
            //Add the browser fragment if it is not already added.
            if(!browserFragment.isAdded())
                addBrowserFragment();

            browserFragment.displayUrl(url);
        }
}
