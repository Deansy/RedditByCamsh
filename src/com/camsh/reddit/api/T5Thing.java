package com.camsh.reddit.api;

import org.json.JSONObject;

public class T5Thing {
	public String display_name;
	public String header_img;
	public String title;
	public String url;
	public String description;
	public int created;
	public int created_utc;
	public Boolean over18;
	public int subscribers;
	public String header_title;
	public String id;
	public String name;
	
	public T5Thing(JSONObject object) {
		try {
			display_name = object.getString("display_name");
			header_img = object.getString("header_img");
			title = object.getString("title");
			url = object.getString("url");
			description = object.getString("description");
			created = object.getInt("created");
			created_utc = object.getInt("created_utc");
			over18 = object.getBoolean("over18");
			subscribers = object.getInt("subscribers");
			header_title = object.getString("header_title");
			id = object.getString("id");
			name = object.getString("name");
		}
        catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}
