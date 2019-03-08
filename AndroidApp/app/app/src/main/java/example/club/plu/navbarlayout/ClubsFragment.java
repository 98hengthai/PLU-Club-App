package example.club.plu.navbarlayout;

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
import java.util.Arrays;

public class ClubsFragment extends Fragment {
    private ListView clubsListView;
    private SearchView searchView;
    String[] clubs = {"AbsoLUTE: Christian Fellowship","Anime Club","Art Club","Chemistry Club","Dance Club","Health Sciences Club","Math Club",
            "AbsoLUTE: Christian Fellowship","Anime Club","Art Club","Chemistry Club","Dance Club","Health Sciences Club","Math Club"};


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clubs, container, false);

        Arrays.sort(clubs);
        ListView clubsListView = view.findViewById(R.id.clubs_listView);
        searchView = (SearchView) view.findViewById(R.id.clubs_searchView);

        final ListAdapter clubsListViewAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, clubs);
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
                //TODO:
                Toast.makeText(getActivity(), "This will open the selected club homepage.", Toast.LENGTH_LONG).show();

            }
        });

        return view;
    }




}
