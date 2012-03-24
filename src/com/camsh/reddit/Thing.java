package com.camsh.reddit;

import org.json.JSONObject;

// Thing is the actual name for a link or post.
public class Thing {
    String subreddit;
    String title;
    Boolean NSFW;
    String URL;
    String author;
    String domain;
    String thumbnailURL;
    int upvotes;
    int downvotes;
    int numComments;

    Thing(JSONObject object) {
        try{
            domain = object.getString("domain");
            subreddit = object.getString("subreddit");
            title = object.getString("title");
            NSFW = object.getBoolean("over_18");
            URL = object.getString("url");
            thumbnailURL = object.getString("thumbnail");
            author = object.getString("author");
            numComments = object.getInt("num_comments");
            downvotes = object.getInt("downs");
            upvotes = object.getInt("ups");
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        

    }
}
