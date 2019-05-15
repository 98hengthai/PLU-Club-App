package example.club.plu.navbarlayout.view.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import example.club.plu.navbarlayout.R;
import example.club.plu.navbarlayout.model.user.User;
import example.club.plu.navbarlayout.utils.Constants;
import example.club.plu.navbarlayout.view.MainActivity;

public class LogInActivity extends AppCompatActivity {
    private static final String TAG = "LogInActivity";
    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;
    private SignInButton signInButton;
    private TextView instruction;



    @Override
    protected void onStart() {
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null) {
            Constants.setUserEmail(account.getEmail());
            Log.d(TAG, "onStart: setEmail - " + Constants.USER_EMAIL);
            Log.d(TAG, "handleSignInResult: account email - " + account.getEmail());
            Log.d(TAG, "handleSignInResult: account name - " + account.getDisplayName());
            Log.d(TAG, "handleSignInResult: account photo url - " + account.getPhotoUrl());
            startActivity(new Intent(LogInActivity.this, MainActivity.class));

        }
        super.onStart();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        initViewAndGoogleSignIn();
    }

    //init activity's view
    private void initViewAndGoogleSignIn() {
        signInButton = findViewById(R.id.sign_in_button);
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestScopes(new Scope(Scopes.PROFILE))
                .requestEmail().requestProfile()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //sign in btn action listener
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        //sign in instruction default setting
        instruction = (TextView) findViewById(R.id.txtView_signIn_instruction);
        instruction.setTextColor(Color.GRAY);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            if (checkPLUemail(account) ) {
                //print out account info
                Log.d(TAG, "handleSignInResult: account email - " + account.getEmail());
                Log.d(TAG, "handleSignInResult: account name - " + account.getDisplayName());
                Log.d(TAG, "handleSignInResult: account photo url - " + account.getPhotoUrl());

                //TODO: post a new user account
                //set User email to static value
                Constants.setUserEmail(account.getEmail());
                Log.d(TAG, "handleSignInResult: Constants.setUserEmail - " +Constants.USER_EMAIL);
                // Signed in successfully, show authenticated UI.
                startActivity(new Intent(LogInActivity.this, MainActivity.class));
            } else {
                signOut();
                instruction.setTextColor(Color.RED);
            }
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Google Sign In Error", "signInResult:failed code = " + e.getStatusCode());
        }
    }

    //return true if the email is a PLU email. Otherwise false.
    private boolean checkPLUemail(GoogleSignInAccount account) {
        if(account.getEmail().contains("@plu.edu"))
            return true;
        return false;
    }

    //
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                    }
                });
    }


}
