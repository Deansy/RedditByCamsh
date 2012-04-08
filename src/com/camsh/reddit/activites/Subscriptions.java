package com.camsh.reddit.activites;

import java.util.ArrayList;
import java.util.List;

import com.camsh.reddit.R;
import com.camsh.reddit.api.RedditCookie;
import com.camsh.reddit.api.RedditSession;
import com.camsh.reddit.api.T5Thing;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;


public class Subscriptions extends ListActivity {

	RedditCookie cookie = null;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	Bundle extras = getIntent().getExtras();
		if(extras !=null) {
			cookie = new RedditCookie(extras.getString("login_cookie"));
		}
		
		RedditSession reddit = new RedditSession();
		
		List<T5Thing> list =  reddit.GetSubs(cookie);
        Toast toast = Toast.makeText(getApplication(),list.get(1).url, Toast.LENGTH_SHORT);
        toast.show();
        
        ArrayList<String> stringList = new ArrayList<String>();
        
        for (int i = 0; i < list.size(); i++) {
        	stringList.add(list.get(i).url);
        	
        }
        
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item1, stringList));
        
        
        
    }
    
}
