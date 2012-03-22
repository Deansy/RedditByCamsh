package com.camsh.reddit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// TODO: Check if the DEV_MODE constant is true before logging or printing a stack trace
// TODO: Add warnings or crash the app on exceptions, No crashes on DEV_MODE

public class RedditSession {

    RedditCookie GetLoginCookie(String username, String password) {
    	HttpClient httpclient = new DefaultHttpClient();
        String URL = Constants.LOGIN_URL;
        HttpPost httppost = new HttpPost(URL);

            try {
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
                nameValuePairs.add(new BasicNameValuePair("user", username));
                nameValuePairs.add(new BasicNameValuePair("passwd", password));
                nameValuePairs.add(new BasicNameValuePair("api_type", "json"));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse response = httpclient.execute(httppost);
                if (Constants.DEV_MODE) Log.d(Constants.TAG, "Login: " + response.getStatusLine().toString());

                HttpEntity entity = response.getEntity();
                BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent()));
                String returnedJSONString = in.readLine();
                in.close();

                if (response.getStatusLine().toString().equals("HTTP/1.1 200 OK")) {
                    if (Constants.DEV_MODE) Log.d(Constants.TAG, "Login: " + returnedJSONString);
                }

                // TODO: Add error checking
                try {
                    JSONObject rootObject = new JSONObject(returnedJSONString);
                    JSONObject jsonObject = rootObject.getJSONObject("json");
                    JSONObject dataObject = jsonObject.getJSONObject("data");

                    return new RedditCookie(dataObject.getString("cookie"));
                }
                catch (JSONException e)
                {
                    if (Constants.DEV_MODE) e.printStackTrace();
                    return null;
                }

            } catch (Exception e) {
                if (Constants.DEV_MODE) e.printStackTrace();
                return null;
            }
    }

    void GetMe(RedditCookie cookie) {
    	try {
	        HttpClient httpclient = new DefaultHttpClient();
	        HttpGet get = new HttpGet("http://www.reddit.com/api/me.json");
            if (Constants.DEV_MODE) Log.d(Constants.TAG, "GetMe: " + cookie.string);
	        get.setHeader("Cookie", cookie.string);

	        HttpResponse response = httpclient.execute(get);

	        HttpEntity entity = response.getEntity();
	        BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent()));
	        String returnedJSONString = in.readLine();
	        in.close();

            if (Constants.DEV_MODE) Log.d(Constants.TAG, "GetMe: " + returnedJSONString);


	        // TODO: Do something with the JSON
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    }

    List<Thing> GetRoot() {
        try {
            final HttpClient httpClient = new DefaultHttpClient();
            final HttpGet get = new HttpGet("http://www.reddit.com/.json");

            HttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent()));
            String returnedJSONString = in.readLine();
            in.close();

            if (Constants.DEV_MODE) Log.d(Constants.TAG, "GetRoot: " + returnedJSONString);

            JSONArray thingArray = new JSONObject(returnedJSONString).getJSONObject("data").getJSONArray("children");

            List<Thing> thingList = new ArrayList<Thing>();

            for (int i = 0; i < thingArray.length(); i++) {
                thingList.add(new Thing(thingArray.getJSONObject(i).getJSONObject("data")));
            }

            return thingList;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    List<Thing> GetSubreddit(String subreddit) {
        // TODO: Add error checking to ensure it is a subreddit
        try {
            final HttpClient httpClient = new DefaultHttpClient();
            final HttpGet get = new HttpGet("http://www.reddit.com/r/" + subreddit + "/.json");

            HttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent()));
            String returnedJSONString = in.readLine();
            in.close();

            if (Constants.DEV_MODE) Log.d(Constants.TAG, "GetSubreddit: " + returnedJSONString);

            JSONArray thingArray = new JSONObject(returnedJSONString).getJSONObject("data").getJSONArray("children");

            List<Thing> thingList = new ArrayList<Thing>();

            for (int i = 0; i < thingArray.length(); i++) {
                thingList.add(new Thing(thingArray.getJSONObject(i).getJSONObject("data")));
            }

            return thingList;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

