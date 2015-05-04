package app.com.socialserve;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class AddDinnerParty extends ActionBarActivity {
    EditText eventName, eventAddress, seatsAvail, eventDate, eventDescription, ingredients;
    Button addEventBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_dinner_party);

        //get id's for widgets
        eventName = (EditText) findViewById(R.id.eventName);
        eventAddress = (EditText) findViewById(R.id.location);
        seatsAvail = (EditText) findViewById(R.id.seatsAvailable);
        eventDate = (EditText) findViewById(R.id.date);
        eventDescription = (EditText) findViewById(R.id.description);
        ingredients = (EditText) findViewById(R.id.ingredients);

        addEventBtn = (Button) findViewById(R.id.addParty);

         addEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveParty();
                Intent i = new Intent(AddDinnerParty.this, MainActivity.class);
                startActivity(i);
            }
        });

    }

    public void saveParty() {
        ParseObject events = new ParseObject("events");
        events.put("name", eventName.getText().toString());
        events.put("location", eventAddress.getText().toString());
        events.put("seatsAvailable", Integer.parseInt(seatsAvail.getText().toString()));
        events.put("time", eventDate.getText().toString());
        events.put("description", eventDescription.getText().toString());
        events.put("ingredients", ingredients.getText().toString());
        events.put("host", ParseUser.getCurrentUser().getUsername());
        events.saveInBackground();
        Toast.makeText(getApplicationContext(), "Saved Event!", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_dinner_party, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
