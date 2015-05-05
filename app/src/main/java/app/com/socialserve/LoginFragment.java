package app.com.socialserve;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    LoginOnClickListener listener;

    EditText username, password;
    Button login_button, register_button;
    public interface LoginOnClickListener {
        public void doOnLoginClicked(String username, String password);
        public void doOnRegisterClicked();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            listener = (LoginOnClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement LoginInterfaceListener");
        }
    }

    public LoginFragment() {
        // Required empty public constructor
    }
    public static LoginFragment newInstance(int sectionNumber){
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        username = (EditText) view.findViewById(R.id.userNameInput);
        password = (EditText) view.findViewById(R.id.userPassInput);
        login_button = (Button) view.findViewById(R.id.login_button);
        register_button = (Button) view.findViewById(R.id.register_button);

        login_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listener.doOnLoginClicked(username.getText().toString(), password.getText().toString());
            }
        });

        register_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listener.doOnRegisterClicked();
            }
        });

        return view;
    }


}
