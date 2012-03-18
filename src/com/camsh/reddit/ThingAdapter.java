package com.camsh.reddit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class ThingAdapter extends ArrayAdapter<RedditThing> {

    List<RedditThing> things;

    public ThingAdapter(Context context, int textViewResourceId, List<RedditThing> things) {
        super(context, textViewResourceId, things);
        this.things = things;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.list_item, null);
        }

        RedditThing o = things.get(position);
        if (o != null)
        {
            /*
            TextView nameView = (TextView)v.findViewById(R.id.Name);

            TextView textNameView = (TextView)v.findViewById(R.id.Text);
            SmartImageView profileImageView = (SmartImageView)v.findViewById(R.id.Image);

            if (nameView != null) {
                nameView.setText("@" + o.getUser().getScreenName());
            }
            if (textNameView != null) {
                textNameView.setText(o.getText());
            }
            if (profileImageView != null) {
                profileImageView.setImageUrl(o.getUser().getProfileImageURL().toString());
            }
            */
        }
        return v;
    }
}
