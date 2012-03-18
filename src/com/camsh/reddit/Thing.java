package com.camsh.reddit;

import org.json.JSONObject;

// Thing is the actual name for a link or post.
public class Thing {
    String subreddit;
    String title;
    Boolean NSFW;
    String URL;

    Thing(JSONObject object) {
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
