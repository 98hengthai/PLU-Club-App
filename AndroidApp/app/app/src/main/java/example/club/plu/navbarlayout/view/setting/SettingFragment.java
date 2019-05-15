package example.club.plu.navbarlayout.view.setting;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import example.club.plu.navbarlayout.R;
import example.club.plu.navbarlayout.model.user.User;
import example.club.plu.navbarlayout.view.login.LogInActivity;
import example.club.plu.navbarlayout.viewModel.SettingFragmentVM;

public class SettingFragment extends Fragment {
    private static final String TAG = "SettingFragment";
    private  GoogleSignInOptions gso;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInAccount account;
    private SettingFragmentVM mSettingFragmentVM;
    private ImageView imageView_profilePic;
    private Button btn_edit;
    private TextView textView_username;
    private TextView textView_email;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init VM
        mSettingFragmentVM = ViewModelProviders.of(this).get(SettingFragmentVM.class);
        //init Google
        account = GoogleSignIn.getLastSignedInAccount(getActivity());
        Log.d(TAG, "onCreate: account - url " + account.getPhotoUrl());

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Setting");
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        //
        searchUserInfo();
        //
        initView(view);
        //
        setViewValue();


        return view;
    }

    private void searchUserInfo() {
        mSettingFragmentVM.getUserGivenEmail(account.getEmail());
    }

    private void initView(View view){
        //init profile pic
        imageView_profilePic = (ImageView) view.findViewById(R.id.imageView_deafult);
        //init editText username and email
        textView_username = (TextView) view.findViewById(R.id.textView_setting_username);
        textView_email = (TextView) view.findViewById(R.id.textView_setting_email);

        //init Sign out Button action listener
        view.findViewById(R.id.btn_signOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        //init edit button listener
        btn_edit = (Button) view.findViewById(R.id.btn_setting_editName);
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void setViewValue()  {
        mSettingFragmentVM.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if(user!=null){
                    Log.d(TAG, "setViewValue: user " + user.toString());
                    textView_username.setText(user.getName());
                    textView_email.setText(user.getEmail());
                } else {
                    Log.d(TAG, "setViewValue: user is null" );
                }
            }
        });

        //
        btn_edit.setVisibility(View.GONE);

    }

    private void signOut() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
        mGoogleSignInClient.signOut();
        startActivity(new Intent(getActivity(), LogInActivity.class));
    }
}
