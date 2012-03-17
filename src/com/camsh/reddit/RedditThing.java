package com.camsh.reddit;

import org.json.JSONObject;
public class RedditThing {
    String subreddit;
    String title;
    Boolean NSFW;
    String URL;

    RedditThing(JSONObject object) {
        try{
            subreddit = object.getString("subreddit");
            title = object.getString("title");
            NSFW = object.getBoolean("over_18");
            URL = object.getString("url");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        

    }
}
