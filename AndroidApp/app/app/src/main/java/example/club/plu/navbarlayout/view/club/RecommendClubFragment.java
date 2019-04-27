package example.club.plu.navbarlayout.view.club;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.gson.Gson;

import java.util.ArrayList;

import example.club.plu.navbarlayout.R;
import example.club.plu.navbarlayout.viewModel.ClubFragmentVM;

public class RecommendClubFragment extends Fragment {
    private ClubFragmentVM mClubFragmentVM;

    public RecommendClubFragment() {
    }

    //init view model before onCreateView
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //reference the ClubsFragmentVM class
        mClubFragmentVM = ViewModelProviders.of(this).get(ClubFragmentVM.class);
        mClubFragmentVM.searchAllInterests();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = setupSurveyView(inflater, container);
        return view;
    }


    private View setupSurveyView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container){
        View view = inflater.inflate(R.layout.fragment_club_survey, container, false);
        view.findViewById(R.id.btn_open_survey_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                Intent intent = new Intent(getActivity(), ClubSurveyActivity.class);
                intent.putExtra("interests", gson.toJson(mClubFragmentVM.getAllInterests()));
                startActivity(intent);
            }
        });
        return view;
    }
}
