package example.club.plu.navbarlayout.view.event;


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
import android.support.v7.widget.SearchView;

import java.util.List;

import example.club.plu.navbarlayout.R;
import example.club.plu.navbarlayout.adapter.event.EventRecycleAdapter;
import example.club.plu.navbarlayout.adapter.event.OnEventListener;
import example.club.plu.navbarlayout.model.event.Event;
import example.club.plu.navbarlayout.utils.Testing;
import example.club.plu.navbarlayout.viewModel.EventFragmentVM;

public class EventsFragment extends Fragment implements OnEventListener {
    private EventFragmentVM mEventFragmentVM;
    private RecyclerView mRecyclerView;
    private EventRecycleAdapter mEventRecycleAdapter;
    private static final String TAG = "EventsFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events_list, container, false);
        mEventFragmentVM = ViewModelProviders.of(this).get(EventFragmentVM.class);
        getActivity().setTitle("Events");
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
                    mEventRecycleAdapter.setEvents(events);
                } else {
                    Log.d(TAG, "onChanged: " + "events is null");
                }
            }
        });
    }

    @Override
    public void onEventClick(int position) {
        Intent intent = new Intent(getActivity(), EventHomePageActivity.class);
        intent.putExtra("event", mEventRecycleAdapter.getSelectedEvent(position));
        startActivity(intent);
    }

    @Override
    public void onCategoryClick(String category) {

    }
}
