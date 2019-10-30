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
import example.club.plu.navbarlayout.model.event.UserEvent;
import example.club.plu.navbarlayout.utils.Constants;
import example.club.plu.navbarlayout.viewModel.EventFragmentVM;

public class EventsFragment extends Fragment implements OnEventListener {
    private EventFragmentVM mEventFragmentVM;
    private RecyclerView mRecyclerView;
//    private List<UserEvent> mEventList;
    private EventRecycleAdapter mEventRecycleAdapter;
    private static final String TAG = "EventsFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Events");
        View view = inflater.inflate(R.layout.fragment_events_list, container, false);
        //init VM
        mEventFragmentVM = ViewModelProviders.of(this).get(EventFragmentVM.class);
        //get request for initial user events
        searchEventsApi();
        //init recycle view
        mRecyclerView = view.findViewById(R.id.event_recycleList);
        //
        initRecycleView(view);
        //observe live data of club list
        subscribeObservers();

        return view;
    }

    private void subscribeObservers() {
       mEventFragmentVM.getUserEvents().observe(this, new Observer<List<Event>>() {
           @Override
           public void onChanged(@Nullable List<Event> events) {
               if(events != null){
                   mEventRecycleAdapter.setEvents(events);
               }
           }
       });
    }

    //new
    private void searchEventsApi() {
       mEventFragmentVM.searchUserEvent(Constants.USER_EMAIL);

    }

    private void initRecycleView(View view) {
        mEventRecycleAdapter = new EventRecycleAdapter(this);
        mRecyclerView.setAdapter(mEventRecycleAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

    }

    @Override
    public void onEventClick(int position) {
//        Intent intent = new Intent(getActivity(), EventHomePageActivity.class);
//        intent.putExtra("event", mEventRecycleAdapter.getSelectedEvent(position));
//        startActivity(intent);
    }

    @Override
    public void onCategoryClick(String category) {

    }




}
