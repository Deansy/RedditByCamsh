package com.camsh.reddit;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FrontPageList extends ListActivity{

    private ThingAdapter adapter;
    private List<Thing> list;
    private RedditSession reddit = new RedditSession();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new GetRoot(this).execute(new RedditCookie("NULL"));
    }
    private class GetRoot extends AsyncTask<RedditCookie, Void, List<Thing>> {
        Context context;
        public GetRoot(Context context) {
            this.context = context;
        }

        protected List<Thing> doInBackground(RedditCookie... cookieString) {
            // Incase I need to pass in a RedditCookie for calls.
            return reddit.GetRoot();
        }

        protected void onPostExecute(List<Thing> result) {
            list = result;
            adapter = new ThingAdapter(context, R.layout.list_item, list);
            setListAdapter(adapter);
        }
    }
}