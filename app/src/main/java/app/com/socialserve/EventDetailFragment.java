package app.com.socialserve;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseQueryAdapter;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventDetailFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    ListView eventsList;
    private ParseQueryAdapter mainAdapter;
    private EventAdapter eventsAdapter;
    private static final String EVENT_KEY = "event_item";
    private Event event;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public EventDetailFragment() {
        // Required empty public constructor
    }

    //Create fragment and pass info into bundle for Efficiency
    public static EventDetailFragment newInstance(Event details) {
        EventDetailFragment fragment = new EventDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(EVENT_KEY, details);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);
        event = (Event) getArguments().getSerializable(EVENT_KEY);

        TextView name = (TextView) view.findViewById(R.id.eventName);
        name.setText(event.getTitle());

        TextView desc = (TextView) view.findViewById(R.id.eventDescription);
        desc.setText(event.getDescription());

        TextView ingredients = (TextView) view.findViewById(R.id.eventIngredients);
        ingredients.setText(event.getIngredients());

        TextView host = (TextView) view.findViewById(R.id.eventHost);
        host.setText("Hosted by " + event.getHost());

        TextView date = (TextView) view.findViewById(R.id.eventDate);
        date.setText("On " + event.getTime());


        return view;
    }


}
