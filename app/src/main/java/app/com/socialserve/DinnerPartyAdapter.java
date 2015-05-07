package app.com.socialserve;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseFile;
import com.parse.ParseImageView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;


public class DinnerPartyAdapter extends ParseQueryAdapter<Dinner> implements OnItemClickListener  {
    //query to get all events
    public int flag = 0;
    public DinnerPartyAdapter(Context context) {
        super(context, new ParseQueryAdapter.QueryFactory<Dinner>() {
            public ParseQuery create() {
                ParseQuery query = new ParseQuery("events");
                return query;
            }
        });
    }


    //query to get events with the specified name
    public DinnerPartyAdapter(Context context, final String name, final String obj, int flag1) {
        super(context, new ParseQueryAdapter.QueryFactory<Dinner>() {
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
    public View getItemView(Dinner dinner, View v, ViewGroup parent) {

        if(flag == 1) {
            v = View.inflate(getContext(), R.layout.urgent_item, null);
            super.getItemView(dinner, v, parent);

            // Add all data to the layout
            TextView titleTextView = (TextView) v.findViewById(R.id.list_name);
            titleTextView.setText(dinner.getTitle());

            TextView date = (TextView) v.findViewById(R.id.list_date);
            date.setText("on " + dinner.getTime());

            TextView attending = (TextView) v.findViewById(R.id.eventAttending);
            attending.setText( " " + dinner.getSeatsSold() + " / " + dinner.getSeats());

        }
        else {
            v = View.inflate(getContext(), R.layout.urgent_item1, null);
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

        }


        return v;
    }

 /* On Item Click Method */
 public void onItemClick(AdapterView<?> parent, View view,
                         int position, long id) {
     Log.d("Item CLicked:", Integer.toString(position));

    // ParseObject parseObject = .getItem(position);

     // Use the parseobject here
     Context context = getContext();
     Toast toast = Toast.makeText(context, "Clicked!", Toast.LENGTH_SHORT);
     toast.show();


 }


}
