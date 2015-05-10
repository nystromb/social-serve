package app.com.socialserve;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;


public class EventAdapter extends ParseQueryAdapter<Event>  {
    //query to get all events
    public int flag = 0;
    public EventAdapter(Context context) {
        super(context, new ParseQueryAdapter.QueryFactory<Event>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("events");
                query.addDescendingOrder("createdAt");
                return query;
            }
        });
    }


    //query to get events with the specified name
    public EventAdapter(Context context, final String name, final String obj, int flag1) {
        super(context, new ParseQueryAdapter.QueryFactory<Event>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("events");

                if(!name.isEmpty()){
                    query.whereEqualTo(name, obj);
                }
                return query;
            }
        });
        flag = flag1;
    }

    // Customize the layout by overriding getItemView
   // @Override
    public View getItemView(Event event, View v, ViewGroup parent) {

        if(flag == 1) {
            v = View.inflate(getContext(), R.layout.my_events_row, null);
            super.getItemView(event, v, parent);

            // Add all data to the layout
            TextView titleTextView = (TextView) v.findViewById(R.id.list_name);
            titleTextView.setText(event.getTitle());

            TextView date = (TextView) v.findViewById(R.id.list_date);
            date.setText("on " + event.getTime());

            TextView attending = (TextView) v.findViewById(R.id.eventAttending);
            attending.setText( " " + event.getSeatsSold() + " / " + event.getSeats());

        }
        else {
            v = View.inflate(getContext(), R.layout.home_row, null);
            super.getItemView(event, v, parent);

            // Add all data to the layout
            TextView titleTextView = (TextView) v.findViewById(R.id.list_name);
            titleTextView.setText(event.getTitle());

            TextView date = (TextView) v.findViewById(R.id.list_date);
            date.setText("on " + event.getTime());

            TextView description = (TextView) v.findViewById(R.id.list_description);
            description.setText(event.getDescription());

            TextView attending = (TextView) v.findViewById(R.id.eventAttending);
            attending.setText( " " + event.getSeatsSold() + " / " + event.getSeats());

        }


        return v;
    }
}
