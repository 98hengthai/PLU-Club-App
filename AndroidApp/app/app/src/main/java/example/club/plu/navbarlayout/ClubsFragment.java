package example.club.plu.navbarlayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ClubsFragment extends Fragment {
    private String[] clubsArry;
    private ListView clubsListView;
    private SearchView searchView;



    public void initializeClubsArray(String[] clubs){
//       clubsArry = clubs;
        clubsArry = new String[]{"AbsoLUTE: Christian Fellowship","Anime Club","Art Club","Chemistry Club","Dance Club","Health Sciences Club","Math Club",
                "Computer Science Club", "Scandinavian Club", "Trombone Club", "United Students Association", "World of Anycraft"};
        Arrays.sort(clubsArry);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clubs, container, false);
        ListView clubsListView = view.findViewById(R.id.clubs_listView);
        searchView = (SearchView) view.findViewById(R.id.clubs_searchView);

        final ListAdapter clubsListViewAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, clubsArry);
        clubsListView.setAdapter(clubsListViewAdapter);
        clubsListView.setTextFilterEnabled(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                ((ArrayAdapter) clubsListViewAdapter).getFilter().filter(newText);
                return false;
            }
        });

        clubsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //parent is the list view, view is the clicked item, position is view's index, id
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO: open the club home page view
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ClubHomePageFragment()).addToBackStack(null).commit();
            }
        });

        return view;
    }




}
