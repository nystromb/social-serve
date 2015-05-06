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

    public DinnerPartyAdapter(Context context) {
        // Use the QueryFactory to construct a PQA that will only show
        // Todos marked as high-pri
        super(context, new ParseQueryAdapter.QueryFactory<Dinner>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("events");

                return query;
            }
        });
    }



    // Customize the layout by overriding getItemView
    @Override
    public View getItemView(Dinner dinner, View v, ViewGroup parent) {

        v = View.inflate(getContext(), R.layout.urgent_item, null);


        super.getItemView(dinner, v, parent);

        // Add the title view
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
