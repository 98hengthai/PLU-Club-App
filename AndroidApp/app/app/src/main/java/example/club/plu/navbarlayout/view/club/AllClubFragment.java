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
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import example.club.plu.navbarlayout.R;
import example.club.plu.navbarlayout.adapter.ClubRecycleAdapter;
import example.club.plu.navbarlayout.adapter.OnClubListener;
import example.club.plu.navbarlayout.model.Club;
import example.club.plu.navbarlayout.utils.Testing;
import example.club.plu.navbarlayout.viewModel.ClubFragmentVM;

public class AllClubFragment extends Fragment  implements OnClubListener {
    private ClubFragmentVM mClubFragmentVM;
    private RecyclerView mRecyclerView;
    private ClubRecycleAdapter mClubRecycleAdapter;
    private static final String TAG = "MainClubFragment";

    //empty constructor
    public AllClubFragment() {
    }

    //init view model before onCreateView
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //reference the ClubsFragmentVM class
        mClubFragmentVM = ViewModelProviders.of(this).get(ClubFragmentVM.class);
    private List<Club> clubsList;
    private String[] clubsArry;
    private ListView clubsListView;
    private SearchView searchView;
    private static final String TAG = "ClubsFragment";


    //init UI components
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_club_list, container, false);
        //observer list of club in VM
        subscribeObservers();
        //mRecyclerView
        mRecyclerView = view.findViewById(R.id.club_recycleList);
        initRecycleView(view);
        testRetrofitRquest();
        initSearchView(view);
        return view;
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
        mClubFragmentVM.getClubs().observe(this, new Observer<List<Club>>() {
            @Override
            public void onChanged(@Nullable List<Club> clubs) {
                if (clubs != null) {
                    Testing.printClubs(clubs, TAG);
                    mClubRecycleAdapter.setClubs(clubs);
                } else {
                    Log.d(TAG, "onChanged: " + "clubs is null");
                }
            }
        });
    }

    //method for testing server request for clubs
    private void testRetrofitRquest(){
        searchClubsApi();
    }
    private void searchClubsApi() {
        mClubFragmentVM.searchClubsApi();
    }

    //init recycle view with the adapter
    private void initRecycleView(View view){
        mClubRecycleAdapter = new ClubRecycleAdapter(this);
        mRecyclerView.setAdapter(mClubRecycleAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    //search toolbar
    private void initSearchView(View view){
        final SearchView searchView = view.findViewById(R.id.search_view_fragment_all_club);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mClubRecycleAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }


    @Override
    public void onCategoryClick(String category) {

    }
}
