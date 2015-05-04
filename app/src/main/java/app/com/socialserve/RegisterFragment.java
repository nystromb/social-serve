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
public class RegisterFragment extends Fragment {

    RegisterOnClickListener listener;

    EditText username, name, email, password, storeid;
    Button register;
    public interface RegisterOnClickListener{
        public void registerUser(String username, String name, String email, String password);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            listener = (RegisterOnClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement LoginInterfaceListener");
        }
    }

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        username = (EditText) view.findViewById(R.id.first_name_reg);
        name = (EditText) view.findViewById(R.id.last_name_reg);
        email = (EditText) view.findViewById(R.id.email_reg);
        password = (EditText) view.findViewById(R.id.password_reg);

        register = (Button) view.findViewById(R.id.do_register_button);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                listener.registerUser(
                        username.getText().toString(),
                        name.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString()
                        );
            }
        });
        return view;
    }


}
