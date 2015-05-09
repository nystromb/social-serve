package app.com.socialserve;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.ParseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyEventsFragment extends ListFragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    ListView eventsList;
    private EventAdapter mainAdapter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public MyEventsFragment() {
        // Required empty public constructor
    }

    public static MyEventsFragment newInstance(int sectionNumber) {
        MyEventsFragment fragment = new MyEventsFragment();
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

        //set up the adapter and apply to the ListView
        mainAdapter = new EventAdapter(view.getContext(), "host", ParseUser.getCurrentUser().getEmail(),1);
        eventsList = (ListView) view.findViewById(android.R.id.list);
        eventsList.setAdapter(mainAdapter);

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Event item = (Event) getListView().getItemAtPosition(position);

        Fragment fragment = EventDetailFragment.newInstance(item);
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment)
                .commit();
    }
}
