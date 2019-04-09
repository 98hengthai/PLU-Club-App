package example.club.plu.navbarlayout;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import java.util.Arrays;
import java.util.List;
import example.club.plu.navbarlayout.adapter.ClubRecycleAdapter;
import example.club.plu.navbarlayout.adapter.OnClubListener;
import example.club.plu.navbarlayout.model.Club;
import example.club.plu.navbarlayout.utils.Testing;
import example.club.plu.navbarlayout.viewModel.ClubFragmentVM;

public class ClubsFragment extends Fragment implements OnClubListener {
    private ClubFragmentVM mClubFragmentVM;
    private RecyclerView mRecyclerView;
    private ClubRecycleAdapter mClubRecycleAdapter;



    private List<Club> clubsList;
    private String[] clubsArry;
    private ListView clubsListView;
    private SearchView searchView;
    private static final String TAG = "ClubsFragment";


    public void initializeClubsArray(String[] clubs){
//       clubsArry = clubs;
        clubsArry = new String[]{"AbsoLUTE: Christian Fellowship","Anime Club","Art Club","Chemistry Club","Dance Club","Health Sciences Club","Math Club",
                "Computer Science Club", "Scandinavian Club", "Trombone Club", "United Students Association", "World of Anycraft"};
        Arrays.sort(clubsArry);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club_list, container, false);
        //reference the ClubsFragmentVM class
        mClubFragmentVM = ViewModelProviders.of(this).get(ClubFragmentVM.class);
        //observer list of club in VM
        subscribeObservers();
        //mRecyclerView
        mRecyclerView = view.findViewById(R.id.club_recycleList);
        initRecycleView(view);
        testRetrofitRquest();
        initSearchView(view);








//        ListView clubsListView = view.findViewById(R.id.clubs_listView);
//        searchView = (SearchView) view.findViewById(R.id.clubs_searchView);
//
//        final ListAdapter clubsListViewAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, clubsArry);
//        clubsListView.setAdapter(clubsListViewAdapter);
//        clubsListView.setTextFilterEnabled(true);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                ((ArrayAdapter) clubsListViewAdapter).getFilter().filter(newText);
//                return false;
//            }
//        });
//
//        clubsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            //parent is the list view, view is the clicked item, position is view's index, id
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                //TODO: open the club home page view
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ClubHomePageFragment()).addToBackStack(null).commit();
//            }
//        });
//
//        view.findViewById(R.id.testButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                testRetrofitRquest();
//            }
//        });

        return view;
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



    private void testRetrofitRquest(){
        searchClubsApi();
    }

    private void searchClubsApi() {
        mClubFragmentVM.searchClubsApi();
    }

    private void initRecycleView(View view){
        mClubRecycleAdapter = new ClubRecycleAdapter(this);
        mRecyclerView.setAdapter(mClubRecycleAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    //search toolbar
    private void initSearchView(View view){
        final SearchView searchView = view.findViewById(R.id.search_view_fragment_club);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mClubFragmentVM.searchClubsApi();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onClubClick(int position) {

    }

    @Override
    public void onCategoryClick(String category) {

    }
}
