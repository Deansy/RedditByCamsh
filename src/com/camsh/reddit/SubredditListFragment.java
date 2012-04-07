package com.camsh.reddit;

import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.Toast;

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
        //openURL("http://www.reddit.com/");
    }

    @Override
    public void onListItemClick (ListView l, View v, int position, long id)  {
        Thing clicked = (Thing)l.getAdapter().getItem(position);
        Toast toast = Toast.makeText(getActivity(),"Upvotes: " + clicked.upvotes, Toast.LENGTH_SHORT);
        toast.show();
        openURL(clicked.URL);
    }

    void openURL(String URL) {
        ThingViewFragment fragment = (ThingViewFragment) getFragmentManager().findFragmentById(R.id.viewer);
        WebView wv = null;
        if (fragment != null){
            wv = (WebView)fragment.getView();
        }
        if (wv != null) {
            wv.loadUrl(URL);
        }

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
