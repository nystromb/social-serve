package app.com.socialserve;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

public class DinnerPartyAdapter extends ParseQueryAdapter<Dinner> {

    //query to get all events
    public DinnerPartyAdapter(Context context) {
        super(context, new ParseQueryAdapter.QueryFactory<Dinner>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("events");
                return query;
            }
        });
    }

    //query to get events with the specified name
    public DinnerPartyAdapter(Context context, final String name, final Object obj) {
        super(context, new ParseQueryAdapter.QueryFactory<Dinner>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("events");

                if(!name.isEmpty()){
                    query.whereEqualTo(name, obj);
                }

                return query;
            }
        });
    }

    // Customize the layout by overriding getItemView
    @Override
    public View getItemView(Dinner dinner, View v, ViewGroup parent) {

        v = View.inflate(getContext(), R.layout.urgent_item, null);

        super.getItemView(dinner, v, parent);

        // Add all data to the layout
        TextView titleTextView = (TextView) v.findViewById(R.id.list_name);
        titleTextView.setText(dinner.getTitle());

        TextView date = (TextView) v.findViewById(R.id.list_date);
        date.setText("on " + dinner.getTime());

        TextView description = (TextView) v.findViewById(R.id.list_description);
        description.setText(dinner.getDescription());

        TextView attending = (TextView) v.findViewById(R.id.eventAttending);
        attending.setText( " " + dinner.getSeatsSold() + " / " + dinner.getSeats());

        return v;
    }

}
