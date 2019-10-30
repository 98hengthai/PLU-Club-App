package example.club.plu.navbarlayout.view.club;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import java.util.List;

import example.club.plu.navbarlayout.R;
import example.club.plu.navbarlayout.model.club.Club;
import example.club.plu.navbarlayout.model.club.ClubUsers;
import example.club.plu.navbarlayout.request.club.ClubApiClient;
import example.club.plu.navbarlayout.utils.Constants;
import example.club.plu.navbarlayout.utils.Testing;
import example.club.plu.navbarlayout.viewModel.ClubFragmentVM;

public class ClubHomePageActivity extends AppCompatActivity {
    //tag for testing
    private static final String TAG = "ClubHomePageActivity";
    //UI Components
    private TextView mClubTitle, mClubEmail, mClubDescription;
    private Button mJoin, mContact;
    //data
    private Club mClub;
    private boolean isJoinedClub;
    private ClubFragmentVM mClubFragmentVM;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_homepage);
        //
        mClubFragmentVM = ViewModelProviders.of(this).get(ClubFragmentVM.class);
        mClub = new Club();
        //
        initView();
        //pass selected mClub from the mClub fragment
        getIncomingIntent();
        //init UI field
        setViewValue();


    }

    private void setViewValue() {
        Log.d(TAG, "setViewValue: mClub - " + mClub.toString() );
        //
        if(mClub != null){
            mClubTitle.setText(mClub.getName());
            mClubEmail.setText(mClub.getClubEmail());
            mClubDescription.setText(mClub.getDescription());
        } else {
            Log.d(TAG, "setClubInfo: null value." );
        }
        //handle joined mClub
        if(isJoinedClub) {
            mJoin.setBackgroundColor(Color.GRAY);
            mJoin.setClickable(false);
            mJoin.setAlpha(.5f);
            mJoin.setText("Joined");
        }
        //action listener
        mJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clubName = mClub.getName();
                String userEmail = Constants.USER_EMAIL;
                String role = "Member";
                ClubUsers clubUsers = new ClubUsers(clubName,userEmail,role);
                //post joined club
                ClubApiClient.postJoinedClub(clubUsers);
                ClubApiClient.getInstance().refreshClubSearch();

                mJoin.setBackgroundColor(Color.GRAY);
                mJoin.setClickable(false);
                mJoin.setAlpha(.5f);
                mJoin.setText("Joined");

            }
        });

        mContact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                sendEmail(v);
            }
        });

    }

    private void initView(){
        mClubTitle = findViewById(R.id.club_name);
        mClubEmail = findViewById(R.id.club_email);
        mClubDescription = findViewById(R.id.club_description);
        mJoin = findViewById(R.id.button_join_club);
        mContact = findViewById(R.id.button_contact_club);
    }


    private void getIncomingIntent(){
        if(getIntent().hasExtra("club")){
            mClub = getIntent().getParcelableExtra("club");
            Log.d(TAG, "getIncomingIntent: from all fragment -  " + mClub.toString());
        }

        if(getIntent().hasExtra("isJoined")){
            isJoinedClub = getIntent().getBooleanExtra("isJoined", false);
            Log.d(TAG, "getIncomingIntent: isJoined - " + isJoinedClub);
        }

        if(getIntent().hasExtra("joinedClub")){
            isJoinedClub = true;
            String clubname = getIntent().getStringExtra("joinedClub");
            mClubFragmentVM.searchClub(clubname);
            Log.d(TAG, "getIncomingIntent: joinedClub - " + clubname);


            mClubFragmentVM.getClub().observe(this, new Observer<Club>() {
                @Override
                public void onChanged(@Nullable Club club) {
                    mClub = club;
                    setViewValue();
                }
            });
        }
    }

    //send email to the mClub admin
    private void sendEmail(View v) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { mClub.getClubEmail() });
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contacting About " + mClub.getClubEmail());
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(Intent.createChooser(emailIntent, ""));
    }

}
