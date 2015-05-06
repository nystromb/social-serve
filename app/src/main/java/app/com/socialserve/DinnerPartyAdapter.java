package app.com.socialserve;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class DinnerPartyAdapter extends ParseQueryAdapter<Dinner> {

    public DinnerPartyAdapter(Context context) {
        // Use the QueryFactory to construct a PQA that will only show
        // Todos marked as high-pri
        super(context, new ParseQueryAdapter.QueryFactory<Dinner>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("events");
                // query.whereEqualTo("highPri", true);
                return query;
            }
        });
    }



    // Customize the layout by overriding getItemView
    @Override
    public View getItemView(Dinner dinner, View v, ViewGroup parent) {
        if (v == null) {
            v = View.inflate(getContext(), R.layout.urgent_item, null);
        }

        super.getItemView(dinner, v, parent);

        // Add and download the image
      /*  ParseImageView todoImage = (ParseImageView) v.findViewById(R.id.icon);
        ParseFile imageFile = object.getParseFile("image");
        if (imageFile != null) {
            todoImage.setParseFile(imageFile);
            todoImage.loadInBackground();
        }
*/
        // Add the title view
        TextView titleTextView = (TextView) v.findViewById(R.id.text1);
        titleTextView.setText(dinner.getString("title"));

        // Add a reminder of how long this item has been outstanding
        TextView timestampView = (TextView) v.findViewById(R.id.timestamp);
        timestampView.setText(dinner.getCreatedAt().toString());
        return v;
    }

}
