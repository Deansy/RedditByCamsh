package com.camsh.reddit;

import android.app.Activity;
import android.os.Bundle;

public class Main extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       
        RedditSession reddit = new RedditSession();
        RedditCookie loginCookie;
        if (Constants.DEV_MODE)
        {
        	loginCookie = reddit.GetLoginCookie(Constants.DEV_USERNAME, Constants.DEV_PASSWORD);
        }
        else 
        {
        	// TODO: Get login details in-app
        	loginCookie = reddit.GetLoginCookie(" ", " ");
        }
        if (loginCookie != null)
        {
        	//reddit.GetMe(loginCookie);
            reddit.GetRoot(loginCookie);
        }
        
    }
}