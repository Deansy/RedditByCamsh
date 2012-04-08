package com.camsh.reddit.activites;

import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import com.camsh.reddit.R;
import com.camsh.reddit.T3ThingAdapter;
import com.camsh.reddit.R.id;
import com.camsh.reddit.R.layout;
import com.camsh.reddit.api.RedditSession;
import com.camsh.reddit.api.T3Thing;


public class SubredditListFragment extends ListFragment {

    private T3ThingAdapter adapter;
    private List<T3Thing> list;
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
        T3Thing clicked = (T3Thing)l.getAdapter().getItem(position);
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

    private class getSubreddit extends AsyncTask<String, Void, List<T3Thing>> {

        public getSubreddit() {

        }

        protected List<T3Thing> doInBackground(String... subreddit) {
            // Incase I need to pass in a RedditCookie for calls.
            return reddit.GetSubreddit(subreddit[0]);
        }

        protected void onPostExecute(List<T3Thing> result) {
            list = result;
            adapter = new T3ThingAdapter(getActivity(), R.layout.list_item, list);
            setListAdapter(adapter);
        }
    }
}
