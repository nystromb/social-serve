package app.com.socialserve;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.parse.ParseUser;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        checkSession();


    }
    public void checkSession(){
        //determine if user needs to login
        // or if user goes to logged in activity if currently logged in

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // show the logged in screen
            Intent loggedIn = new Intent(this, LandingFeedActivity.class);
            startActivity(loggedIn);
        } else {
            // show the signup or login screen
            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
        }

    }


    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        checkSession();


    }

}
