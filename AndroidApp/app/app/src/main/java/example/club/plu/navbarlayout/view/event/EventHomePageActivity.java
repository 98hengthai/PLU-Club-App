package example.club.plu.navbarlayout.view.event;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import example.club.plu.navbarlayout.R;
import example.club.plu.navbarlayout.model.event.Event;

public class EventHomePageActivity extends AppCompatActivity {
    private static final String TAG = "EventHomePageActivity";
    private TextView eventTitle, eventLocation, eventStart, eventEnd, eventClub;
    private Button yes, no;
    private Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_homepage);

        eventTitle = findViewById(R.id.eventName);
        eventLocation = findViewById(R.id.location);
        eventStart = findViewById(R.id.startTime);
        eventEnd = findViewById(R.id.endTime);
        eventClub = findViewById(R.id.event_club_name);
        yes = findViewById(R.id.yes_button);
        no = findViewById(R.id.no_Button);


        getIncomingIntent();
        setEventInfo(event);
    }

    private void getIncomingIntent() {
        if(getIntent().hasExtra("event")){
            event = getIntent().getParcelableExtra("event");
        }else{
            event = null;
        }
    }


    private void setEventInfo(Event event) {
        if (event != null) {
            eventTitle.setText(event.getName());
            eventLocation.setText(event.getLocation());
            eventStart.setText(event.getStart_time());
            eventEnd.setText(event.getEnd_time());
            eventClub.setText(event.getClubName());
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO :  post a response to attend event
//                    sendRequest(username)
                }
            });
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO: post a response to no attend event
                }
            });
        } else {
            Log.d(TAG, "setEventInfo: null value.");
        }
    }

}

