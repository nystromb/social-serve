package app.com.socialserve;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ListView;
import android.widget.Toast;
import com.parse.ParseException;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class LandingFeedActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
                   CreateEventFragment.CreateEventListener{
    Fragment f;

    @Override
    public void createEvent(String name, String address, int seatsAvail, String date, String desc, String ingredients, String host) {
        Event events = new Event();
        events.setTitle(name);
        events.setLocation(address);
        events.setSeats(seatsAvail);
        events.setTime(date);
        events.setDescription(desc);
        events.setIngredients(ingredients);
        events.setHost(host);
        events.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Toast.makeText(getApplicationContext(), "Event Created", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #//restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_feed);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;

        switch(position){
            case 0:
                fragment = LandingFeedFragment.newInstance(position + 1);
                mTitle = getString(R.string.title_section1);
                break;
            case 1:
                fragment = MyEventsFragment.newInstance(position + 1);
                mTitle = getString(R.string.title_section2);
                break;
            case 2:
                fragment = CreateEventFragment.newInstance(position + 1);
                mTitle = getString(R.string.title_section3);
                break;
            case 3:
                ParseUser.logOut();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;
        }

        if(ParseUser.getCurrentUser() != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
    }

   public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.landing_feed, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_example) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = "Hey check out SocialServe! It's an A+ Dinner discovery app! http://www.SocialServeApp.com";
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Tell your friends about us!"));
            return true;
        }
        else if(id == R.id.action_burger){
            f = LandingFeedFragment.newInstance(2, "ingredients", "Burgers");
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, f)
                    .commit();
            Toast.makeText(getApplicationContext(), "Searching for Burgers", Toast.LENGTH_LONG).show();

        }
        else if(id== R.id.action_bbq)
            Toast.makeText(getApplicationContext(), "Search for BBQ", Toast.LENGTH_LONG).show();
        else if(id == R.id.spaghetti)
            Toast.makeText(getApplicationContext(), "Search for Spaghetti", Toast.LENGTH_LONG).show();


        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class LandingFeedFragment extends ListFragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static String SORT = "";
        private static String QUERY = "";

        private ParseQueryAdapter mainAdapter;
        ListView eventsList;

        public LandingFeedFragment() {

        }


        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static LandingFeedFragment newInstance(int sectionNumber) {
            LandingFeedFragment fragment = new LandingFeedFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        //Returns Sorted Fragment
        public static LandingFeedFragment newInstance(int sectionNumber, String sortName, String query) {
            LandingFeedFragment fragment = new LandingFeedFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putString(SORT, sortName);
            args.putString(QUERY, query);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_events, container, false);
            eventsList = (ListView) view.findViewById(android.R.id.list);

            Bundle b = getArguments();
            String s = b.getString("sort");
            String q = b.getString("query");

            if (s != null)
                mainAdapter = new EventAdapter(view.getContext(), s, q, 1);
            else //Load Original Adapter
                mainAdapter = new EventAdapter(view.getContext());

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
 }

