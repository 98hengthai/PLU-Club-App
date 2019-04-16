package example.club.plu.navbarlayout;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
<<<<<<< HEAD
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
=======
>>>>>>> cb198f05b50fced86c34d98e20dd279187f8455d
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.support.v7.widget.SearchView;
import java.util.Arrays;
import java.util.List;
import example.club.plu.navbarlayout.adapter.EventRecycleAdapter;
import example.club.plu.navbarlayout.adapter.OnEventListener;
import example.club.plu.navbarlayout.model.Event;
import example.club.plu.navbarlayout.utils.Testing;
import example.club.plu.navbarlayout.viewModel.EventFragmentVM;

<<<<<<< HEAD
public class EventsFragment extends Fragment implements OnEventListener{
    private EventFragmentVM mEventFragmentVM;
    private RecyclerView mRecyclerView;
    private EventRecycleAdapter mEventRecycleAdapter;

    private List<Event> eventsList;
    private String[] eventArray;
    private ListView eventsListView;
    private SearchView searchView;


=======
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import example.club.plu.navbarlayout.model.Event;
import example.club.plu.navbarlayout.request.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsFragment extends Fragment {
>>>>>>> cb198f05b50fced86c34d98e20dd279187f8455d
    private static final String TAG = "EventsFragment";
    
    @Nullable
    @Override
<<<<<<< HEAD
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events_list, container, false);
        mEventFragmentVM = ViewModelProviders.of(this).get(EventFragmentVM.class);
        subscribeObservers();
        mRecyclerView = view.findViewById(R.id.event_recycleList);
        initRecycleView(view);
        testRetrofitRquest();
        initSearchView(view);






        return view;

    }

    private void initSearchView(View view) {
        final SearchView searchView = view.findViewById(R.id.search_view_fragment_events);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mEventFragmentVM.searchEventsApi();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) { return false; }
        });

    }

    private void testRetrofitRquest() { searchEventsApi();}

    private void searchEventsApi() {
        mEventFragmentVM.searchEventsApi();
    }

    private void initRecycleView(View view) {
        mEventRecycleAdapter = new EventRecycleAdapter(this);
        mRecyclerView.setAdapter(mEventRecycleAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    private void subscribeObservers() {
        mEventFragmentVM.getEvents().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(@Nullable List<Event> events) {
                if(events != null){
                    Testing.printEvents(events, TAG);
                } else {
                    Log.d(TAG, "onChanged: " + "events is null");
                }
            }
        });
    }

    @Override
    public void onEventClick(int position) {

    }

    @Override
    public void onCategoryClick(String category) {
=======
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_events, container, false);
>>>>>>> cb198f05b50fced86c34d98e20dd279187f8455d

    }
}
