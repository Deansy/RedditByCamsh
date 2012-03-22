package com.camsh.reddit;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cdeansy
 * Date: 21/03/2012
 * Time: 23:47
 * To change this template use File | Settings | File Templates.
 */
public class SubredditList extends ListActivity {

    private ThingAdapter adapter;
    private List<Thing> list;
    private RedditSession reddit = new RedditSession();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // A cookie is not needed for this call so a null cookie is passed.
        new getSubreddit(this).execute("AlienBlue");
    }

    private class getSubreddit extends AsyncTask<String, Void, List<Thing>> {
        Context context;
        public getSubreddit(Context context) {
            this.context = context;
        }

        protected List<Thing> doInBackground(String... subreddit) {
            // Incase I need to pass in a RedditCookie for calls.
            return reddit.GetSubreddit(subreddit[0]);
        }

        protected void onPostExecute(List<Thing> result) {
            list = result;
            adapter = new ThingAdapter(context, R.layout.list_item, list);
            setListAdapter(adapter);
        }
    }
}
