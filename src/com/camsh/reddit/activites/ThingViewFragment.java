package com.camsh.reddit.activites;

import com.camsh.reddit.R;
import com.camsh.reddit.R.id;
import com.camsh.reddit.R.layout;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class ThingViewFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.thing_view, container, false);
        
        WebView wv = (WebView)v.findViewById(R.id.webView1);
        wv.setWebViewClient(new WebViewClient());
        wv.loadUrl("http://google.com");


       return wv;
    }
}
