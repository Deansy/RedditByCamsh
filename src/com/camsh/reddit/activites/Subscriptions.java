package com.camsh.reddit.activites;

import java.util.ArrayList;
import java.util.List;

import com.camsh.reddit.R;
import com.camsh.reddit.T3ThingAdapter;
import com.camsh.reddit.T5ThingAdapter;
import com.camsh.reddit.api.RedditCookie;
import com.camsh.reddit.api.RedditSession;
import com.camsh.reddit.api.T3Thing;
import com.camsh.reddit.api.T5Thing;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class Subscriptions extends ListActivity {

	RedditCookie cookie = null;
	private RedditSession reddit = new RedditSession();
	private List<T5Thing> list;
	private T5ThingAdapter adapter;
	
	ProgressDialog dialog;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	Bundle extras = getIntent().getExtras();
		if(extras !=null) {
			cookie = new RedditCookie(extras.getString("login_cookie"));
		}
		
		RedditSession reddit = new RedditSession();
		
		list =  reddit.GetSubs(cookie);
        Toast toast = Toast.makeText(getApplication(),list.get(1).title, Toast.LENGTH_SHORT);
        toast.show();
        
        ArrayList<String> stringList = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
        	stringList.add(list.get(i).title);
        	
        }
        new getSubs().execute("");
        dialog = ProgressDialog.show(Subscriptions.this, "", 
                "Loading. Please wait...", true);
    }
	
	@Override
	public void onListItemClick (ListView l, View v, int position, long id)  {
		T5Thing clicked = (T5Thing)l.getAdapter().getItem(position);
        Toast toast = Toast.makeText(this,"Subscribers: " + clicked.subscribers, Toast.LENGTH_SHORT);
        toast.show();
	}
	private class getSubs extends AsyncTask<String, Void, List<T5Thing>> {

        protected List<T5Thing> doInBackground(String... subreddit) {
            // Incase I need to pass in a RedditCookie for calls.
            return reddit.GetSubs(cookie);
        }

        protected void onPostExecute(List<T5Thing> result) {
            list = result;
            adapter = new T5ThingAdapter(getApplicationContext(), R.layout.list_item1, list);
            setListAdapter(adapter);
            dialog.cancel();
        }
    }
}
