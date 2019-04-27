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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import example.club.plu.navbarlayout.R;
import example.club.plu.navbarlayout.adapter.club.JoinedClubRecycleAdapter;
import example.club.plu.navbarlayout.adapter.club.OnClubListener;
import example.club.plu.navbarlayout.model.club.ClubUsers;
import example.club.plu.navbarlayout.utils.Constants;
import example.club.plu.navbarlayout.utils.Testing;
import example.club.plu.navbarlayout.viewModel.ClubFragmentVM;

public class JoinedClubFragment extends Fragment implements OnClubListener {
    private ClubFragmentVM mClubFragmentVM;
    private RecyclerView mRecyclerView;
    private JoinedClubRecycleAdapter mClubRecycleAdapter;
    private static final String TAG = "JoinedClubFragment";

    public JoinedClubFragment() {
    }

    //init view model before onCreateView
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //reference the ClubsFragmentVM class
        mClubFragmentVM = ViewModelProviders.of(this).get(ClubFragmentVM.class);
    }

    //init UI components
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joined_club_list, container, false);
        //init club list
        searchJoinedClubApi();

        //init recycle view
        mRecyclerView = view.findViewById(R.id.joined_club_recycleList);
        initRecycleView(view);
        //observe live data of club list
        subscribeObservers();
        return view;
    }

    //API GET: setting livedata of List<UserClubs>
    private void searchJoinedClubApi() {
        mClubFragmentVM.searchJoinedClubsApi(Constants.testEmail);
    }

    //create a new activity intent along with club object
    @Override
    public void onClubClick(int position) {
        Intent intent = new Intent(getActivity(), ClubHomePageActivity.class);
        intent.putExtra("club", mClubRecycleAdapter.getSelectedClub(position));
        startActivity(intent);
    }


    //method to observe the VM club list
    private void subscribeObservers(){
        mClubFragmentVM.getJoinedClubs().observe(this, new Observer<List<ClubUsers>>() {
            @Override
            public void onChanged(@Nullable List<ClubUsers> clubUsers) {
                if(clubUsers != null) {
                    Testing.printClubUsers(clubUsers, TAG);
                    mClubRecycleAdapter.setClubs(clubUsers);
                } else {
                    Log.d(TAG, "onChanged: " + "clubs is null");
                }
            }
        });
    }


    //init recycle view with the adapter
    private void initRecycleView(View view){
        mClubRecycleAdapter = new JoinedClubRecycleAdapter(this);
        mRecyclerView.setAdapter(mClubRecycleAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }


    @Override
    public void onCategoryClick(String category) {

    }
}
