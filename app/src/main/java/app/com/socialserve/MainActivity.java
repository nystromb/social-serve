package app.com.socialserve;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.Parse;
import com.parse.ParseUser;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize parse with keys
        Parse.initialize(this, "6K0xKg6n05u4nq1sMCnAFRtXFYNJiLuSRc1Ma6cc", "JxAELcOmc4Am3BYg7tKLqUhnP7SxJ8E0AdoeBJgz");

        //determine if user needs to login
        // or if user goes to logged in activity if currently logged in

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // show the logged in screen
            Intent loggedIn = new Intent(this, LandingFeed.class);
            startActivity(loggedIn);
        } else {
            // show the signup or login screen
            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
        }

    }

}
