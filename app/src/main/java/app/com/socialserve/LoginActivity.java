package app.com.socialserve;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class LoginActivity extends FragmentActivity
                            implements LoginFragment.LoginOnClickListener,
                                       RegisterFragment.RegisterOnClickListener {

    LoginFragment loginView;
    RegisterFragment registerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //create instances of child fragments (register and login views)
        loginView = new LoginFragment();
        registerView = new RegisterFragment();

        //onCreate must create a transaction
        //to determine which fragment to show
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //show the login screen by default
        transaction.add(R.id.fragment_container, loginView).commit();
    }

    @Override
    public void doOnLoginClicked(String username, String password) {
        ParseUser.logInInBackground(username,password, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    // Hooray! The user is logged in.
                    Toast.makeText(getApplicationContext(), "Welcome, " + user.getCurrentUser().getUsername(), Toast.LENGTH_LONG).show();
                    Intent loggedIn = new Intent(LoginActivity.this, LandingFeedActivity.class);
                    startActivity(loggedIn);
                } else {
                    // Signup failed.
                    // send output to the user:
                    Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void doOnRegisterClicked(){
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.fragment_container, registerView);
        tx.addToBackStack(null);
        tx.commit();
    }

    @Override
    public void registerUser(final String username, final String name, final String email, final String password) {
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        // other fields can be set just like with ParseObject
        user.put("name", name);

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    FragmentTransaction registered = getSupportFragmentManager().beginTransaction();
                    registered.replace(R.id.fragment_container, loginView);
                    registered.commit();
                    Toast.makeText(getApplicationContext(),"You have registered successfully", Toast.LENGTH_LONG).show();
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Toast.makeText(
                            getApplicationContext(),
                            e.getMessage(),
                            Toast.LENGTH_LONG
                    ).show();
                }
            }
        });
    }
}
