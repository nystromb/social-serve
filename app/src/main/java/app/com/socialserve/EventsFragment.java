package app.com.socialserve;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    ListView eventsList;
    private ParseQueryAdapter mainAdapter;
    private DinnerPartyAdapter eventsAdapter;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
     /*   try {
            listener = (RegisterOnClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement LoginInterfaceListener");
        }
        */
    }

    public EventsFragment() {
        // Required empty public constructor
    }
    public static EventsFragment newInstance(int sectionNumber) {
        EventsFragment fragment = new EventsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        mainAdapter = new DinnerPartyAdapter(view.getContext(), "host", ParseUser.getCurrentUser().getEmail());

        //Subclass of Parse Query Adapter

        eventsList = (ListView) view.findViewById(R.id.eventsListView);
        eventsList.setAdapter(mainAdapter);
        return view;
    }


}
