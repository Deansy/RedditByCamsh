package com.camsh.reddit;

/**
 * Created by IntelliJ IDEA.
 * User: cdeansy
 * Date: 17/03/2012
 * Time: 02:16
 * To change this template use File | Settings | File Templates.
 */
public class ThingAdapter extends ArrayAdapter<RedditThing>{

    List<RedditThing> things;

    public ThingAdapter(Context context, int textViewResourceId, List<Status> things) {
        super(context, textViewResourceId, things);
        this.things = things;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //v = vi.inflate(R.layout.list_item, null);
            v = vi.inflate(R.layout.)
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
