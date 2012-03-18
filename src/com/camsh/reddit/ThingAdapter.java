package com.camsh.reddit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ThingAdapter extends ArrayAdapter<Thing> {

    List<Thing> things;

    public ThingAdapter(Context context, int textViewResourceId, List<Thing> things) {
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

        Thing o = things.get(position);
        if (o != null)
        {
            TextView nameView = (TextView)v.findViewById(R.id.Name);
            TextView textView = (TextView)v.findViewById(R.id.Text);
            // Just testing to make sure it is working.
            if (nameView != null) {
                nameView.setText(o.title);
            }
            if (textView != null) {
                textView.setText(o.subreddit);
            }
        }
        return v;
    }
}
