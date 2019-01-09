package com.example.akshitagupta.project3c;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class BrowserFragment extends Fragment {

    private WebView webView;
    private LinearLayout webViewContainer;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    //
    // onCreateView: This inflates the view and checks to see if an existing WebView
    // already exists.  If it does, the old WebView is added to the view.
    //
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_browser, container, false);

        webViewContainer = (LinearLayout) view.findViewById(R.id.webViewContainer);

        //Add the previous WebView to the view if one exists.
        if(webView != null) {
            ViewGroup oldParent = (ViewGroup) webView.getParent();
            oldParent.removeView(webView);
            webViewContainer.addView(webView);
        } else {
            createNewWebView();
        }

        return view;
    }

    //
    // displayUrl: This method updates the WebView in the fragment to display
    // the specified url.
    //
    public void displayUrl(String url)
    {
        //Create a new WebView to clear the old data
        createNewWebView();

        //Enable JavaScript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //Display website to user
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }

    //
    // createNewWebView: This method creates a new WebView and adds it
    // to the WebView container.
    //
    private void createNewWebView()
    {
        //Remove the webView from it's parent if the webView exists
        if(webView != null) {
            ViewGroup parent = (ViewGroup) webView.getParent();
            parent.removeView(webView);
        }

        //Create a new WebView
        webView = new WebView(getContext());
        webView.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));

        //Add the new WebView to the webViewContainer
        webViewContainer.addView(webView);
    }
}
