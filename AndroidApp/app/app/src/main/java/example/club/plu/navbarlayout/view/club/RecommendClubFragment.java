package example.club.plu.navbarlayout.view.club;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import example.club.plu.navbarlayout.R;
import example.club.plu.navbarlayout.adapter.club.JoinedClubRecycleAdapter;
import example.club.plu.navbarlayout.adapter.club.OnClubListener;
import example.club.plu.navbarlayout.adapter.club.RecommendClubRecycleAdapter;
import example.club.plu.navbarlayout.model.club.Club;
import example.club.plu.navbarlayout.model.club.ClubUsers;
import example.club.plu.navbarlayout.model.club.UserInterest;
import example.club.plu.navbarlayout.utils.Constants;
import example.club.plu.navbarlayout.viewModel.ClubFragmentVM;

public class RecommendClubFragment extends Fragment implements OnClubListener {
    private ClubFragmentVM mClubFragmentVM;
    private RecyclerView mRecyclerView;
    private RecommendClubRecycleAdapter mClubRecycleAdapter;
    private static final String TAG = "RecommendClubFragment";
    private TextView txtView_surveyHint;
    private Button btn_take_survey;

    public RecommendClubFragment() {
    }

    //init view model before onCreateView
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //reference the ClubsFragmentVM class
        mClubFragmentVM = ViewModelProviders.of(this).get(ClubFragmentVM.class);
        //search all interests
        mClubFragmentVM.searchAllInterests();
        //search club given user's interest
        mClubFragmentVM.searchClubRelatedToUserInterests(Constants.USER_EMAIL);
        //seach user joined club
        mClubFragmentVM.searchJoinedClubsApi(Constants.USER_EMAIL);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend_club_list, container, false);
        txtView_surveyHint = (TextView) view.findViewById(R.id.textView_recommend_taking_survey);
        view.findViewById(R.id.button_take_survey).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                Intent intent = new Intent(getActivity(), ClubSurveyActivity.class);
                intent.putExtra("interests", gson.toJson(mClubFragmentVM.getAllInterests()));
                startActivity(intent);
            }
        });

        //init recycle view
        mRecyclerView = view.findViewById(R.id.recommend_club_recycleList);
        initRecycleView(view);
        //set data to recycle view
        observeData();



        return view;
    }

    private void observeData() {
        mClubFragmentVM.getClubsOfUserInterest().observe(this, new Observer<List<Club>>() {
            @Override
            public void onChanged(@Nullable List<Club> clubs) {
                if(clubs != null){
                    mClubRecycleAdapter.setClubs(clubs);
                    txtView_surveyHint.setVisibility(View.GONE);

                } else {
                    txtView_surveyHint.setVisibility(View.VISIBLE);
                }
            }
        });

        mClubFragmentVM.searchJoinedClubsApi(Constants.USER_EMAIL);
        mClubFragmentVM.getJoinedClubs().observe(this, new Observer<List<ClubUsers>>() {
            @Override
            public void onChanged(@Nullable List<ClubUsers> clubUsers) {
                if(clubUsers != null) {
                    mClubRecycleAdapter.setJoinedClub(clubUsers);
                }
            }
        });
    }

    //init recycle view with the adapter
    private void initRecycleView(View view){
        mClubRecycleAdapter = new RecommendClubRecycleAdapter(this);
        mRecyclerView.setAdapter(mClubRecycleAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }


    @Override
    public void onClubClick(int position) {
        String clubName = mClubRecycleAdapter.getSelectedClub(position).getName();
        Intent intent = new Intent(getActivity(), ClubHomePageActivity.class);
        intent.putExtra("club", clubName);
        startActivity(intent);
    }

    @Override
    public void onCategoryClick(String category) {

    }
}
