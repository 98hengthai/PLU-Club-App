package example.club.plu.navbarlayout.view.club;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;

import android.view.View;
import android.widget.Button;

import android.widget.TextView;

import example.club.plu.navbarlayout.R;
import example.club.plu.navbarlayout.model.club.Club;

public class ClubHomePageActivity extends AppCompatActivity {
    //tag for testing
    private static final String TAG = "ClubHomePageActivity";
    //UI Components
    private AppCompatImageView mClubImage;
    private TextView mClubTitle, mClubEmail, mClubDescription;
    private Button mJoin, mContact;
    //data
    private Club club;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_homepage);
//        mClubImage = findViewById(R.id.club_image);
        mClubTitle = findViewById(R.id.club_name);
        mClubEmail = findViewById(R.id.club_email);
        mClubDescription = findViewById(R.id.club_description);
        mJoin = findViewById(R.id.button_join_club);
        mContact = findViewById(R.id.button_contact_club);

        //pass selected club from the club fragment
        getIncomingIntent();
        //init UI field
        setClubInfo(club);

    }

    private void setClubInfo(Club club) {
        if(club != null){
            mClubTitle.setText(club.getName());
            mClubEmail.setText(club.getClubEmail());
            mClubDescription.setText(club.getDescription());
            mJoin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO :  post a request to join a club
//                    sendRequest(username)
                }
            });
           mContact.setOnClickListener(new View.OnClickListener(){
               @Override
            public void onClick(View v) {
                sendEmail(v);
            }
           });
        } else {
            Log.d(TAG, "setClubInfo: null value." );
        }
    }


    private void getIncomingIntent(){
        if(getIntent().hasExtra("club")){
            club = getIntent().getParcelableExtra("club");
        } else {
            club = null;
        }
    }

    //send email to the club admin
    private void sendEmail(View v) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { club.getClubEmail() });
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Contacting About " + club.getClubEmail());
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(Intent.createChooser(emailIntent, ""));
    }

}
