package com.camsh.reddit;

import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import java.util.List;

public class FrontPageList extends ListActivity{

    private ThingAdapter adapter;
    private List<Thing> list;
    private RedditSession reddit = new RedditSession();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new getData(this).execute(new RedditCookie("NULL"));
    }
    private class getData extends AsyncTask<RedditCookie, Void, List<Thing>> {
        Context context;
        public getData(Context context) {
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