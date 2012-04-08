package com.camsh.reddit.api;

import org.json.JSONObject;

// Thing is the actual name for a link or post.
public class T3Thing {
	public String subreddit;
	public String title;
	public Boolean NSFW;
    public String URL;
    public String author;
    public String domain;
    public String thumbnailURL;
    public int upvotes;
    public int downvotes;
    public int numComments;
  

    public T3Thing(JSONObject object) {
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
