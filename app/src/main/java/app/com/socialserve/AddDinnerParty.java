package app.com.socialserve;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class AddDinnerParty extends Fragment {
    EditText eventName, eventAddress, seatsAvail, eventDate, eventDescription, ingredients;
    Button addEventBtn;
    CreateEventListener listener;

    public interface CreateEventListener {
        public void createEvent(String name, String address, int seatsAvail, String date, String desc, String ingredients, String host);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            listener = (CreateEventListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement CreateEventListener");
        }
    }

    public AddDinnerParty() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_dinner_party, container, false);
        eventName = (EditText)rootView.findViewById(R.id.eventName);
        eventAddress = (EditText) rootView.findViewById(R.id.location);
        seatsAvail = (EditText) rootView.findViewById(R.id.seatsAvailable);
        eventDate = (EditText) rootView.findViewById(R.id.date);
        eventDescription = (EditText) rootView.findViewById(R.id.description);
        ingredients = (EditText) rootView.findViewById(R.id.ingredients);

        addEventBtn = (Button) rootView.findViewById(R.id.addParty);

        addEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = eventName.getText().toString();
                String addr = eventAddress.getText().toString();
                int seats = Integer.parseInt(seatsAvail.getText().toString());
                String date = eventDate.getText().toString();
                String desc = eventDescription.getText().toString();
                String ing = ingredients.getText().toString();
                String host = ParseUser.getCurrentUser().getEmail();
                listener.createEvent(name, addr, seats, date, desc, ing, host);

            }
        });


        return rootView;
    }

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static AddDinnerParty newInstance(int sectionNumber){
        AddDinnerParty fragment = new AddDinnerParty();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
  /*   public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_add_dinner_party, container, false);
            return rootView;
        }
    }
    */
}
