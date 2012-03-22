package com.camsh.reddit;

import android.app.ListActivity;
import android.app.ListFragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;


public class SubredditListFragment extends ListFragment {

    private ThingAdapter adapter;
    private List<Thing> list;
    private RedditSession reddit = new RedditSession();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // A cookie is not needed for this call so a null cookie is passed.
        new getSubreddit().execute("AlienBlue");
    }

    private class getSubreddit extends AsyncTask<String, Void, List<Thing>> {

        public getSubreddit() {

        }

        protected List<Thing> doInBackground(String... subreddit) {
            // Incase I need to pass in a RedditCookie for calls.
            return reddit.GetSubreddit(subreddit[0]);
        }

        protected void onPostExecute(List<Thing> result) {
            list = result;
            adapter = new ThingAdapter(getActivity(), R.layout.list_item, list);
            setListAdapter(adapter);
        }
    }
}
