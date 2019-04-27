package example.club.plu.navbarlayout.view.club;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toolbar;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import example.club.plu.navbarlayout.R;
import example.club.plu.navbarlayout.model.club.Interests;
import example.club.plu.navbarlayout.request.club.ClubApi;
import example.club.plu.navbarlayout.request.club.ClubApiClient;

public class ClubSurveyActivity extends AppCompatActivity {
    private List<Interests> mInterests;
    private List<Interests> mCheckedInterests;
    private Button mDoneButton;

    private static final String TAG = "ClubSurveyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Club Survey");
        setContentView(R.layout.activity_club_survey);
        getIncomingIntent();
        initTestingField();
        initView();

    }

    private void initTestingField() {
        mCheckedInterests = new ArrayList<>();


//        mInterests = new ArrayList<>();
//       for(int i=0; i<15;i++){
//           mInterests.add(new Interests(""+i));
//       }
    }

    private void getIncomingIntent() {
        if(getIntent().hasExtra("interests")){
            Gson gson = new Gson();
            String jsonStr = getIntent().getStringExtra("interests");
            mInterests = gson.fromJson(jsonStr, new TypeToken<List<Interests>>(){}.getType());
            Log.d(TAG, "getIncomingIntent: " + getIntent().getStringExtra("interests"));

        } else {
            mInterests = null;
        }
    }

    private void initView() {

        LinearLayout linearLayout = findViewById(R.id.scrollview_lineaLayout);
        for(final Interests s: mInterests){
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(s.getName());
            checkBox.setTextSize(16);
            checkBox.setTextColor(Color.BLACK);
            linearLayout.addView(checkBox);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        mCheckedInterests.add(s);
                    } else {
                        mCheckedInterests.remove(s);
                    }
                }
            });
        }
        mDoneButton = findViewById(R.id.btn_survey_done);
        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToDo: back to recommend fragment
            }
        });

    }
}
