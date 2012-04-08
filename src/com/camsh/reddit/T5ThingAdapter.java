package com.camsh.reddit;

import java.util.List;

import com.camsh.reddit.api.T3Thing;
import com.camsh.reddit.api.T5Thing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class T5ThingAdapter extends ArrayAdapter<T5Thing> {
	
	List<T5Thing> things;
	
    public T5ThingAdapter(Context context, int textViewResourceId, List<T5Thing> things) {
        super(context, textViewResourceId, things);
        this.things = things;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.list_item1, null);
        }

        T5Thing o = things.get(position);
        if (o != null)
        {
            TextView URLTextView = (TextView)v.findViewById(R.id.URLTextView);

            if (URLTextView != null) {
            	URLTextView.setText(o.url);
            }
        }
        return v;
    }

}
