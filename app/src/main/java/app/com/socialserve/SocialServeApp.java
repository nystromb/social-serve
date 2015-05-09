package app.com.socialserve;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by brian on 5/4/2015.
 */
public class SocialServeApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        //Register Sub Classes
        ParseObject.registerSubclass(Event.class);

        //initialize parse with keys

        Parse.initialize(this, getString(R.string.parseKey), getString(R.string.parseSecret));
    }
}
