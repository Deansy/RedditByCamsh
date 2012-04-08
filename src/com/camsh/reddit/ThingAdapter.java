package com.camsh.reddit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import com.camsh.reddit.api.T3Thing;

public class ThingAdapter extends ArrayAdapter<T3Thing> {

    List<T3Thing> things;

    public ThingAdapter(Context context, int textViewResourceId, List<T3Thing> things) {
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

        T3Thing o = things.get(position);
        if (o != null)
        {
            TextView titleTextView = (TextView)v.findViewById(R.id.Title);
            TextView authorTextView = (TextView)v.findViewById(R.id.Author);
            TextView subbredditTextView = (TextView)v.findViewById(R.id.Subreddit);

            if (titleTextView != null) {
                titleTextView.setText(o.title);
            }
            if (authorTextView != null) {
                authorTextView.setText(o.author);
            }
            if (subbredditTextView != null) {
                subbredditTextView.setText(o.subreddit);
            }
        }
        return v;
    }
}
