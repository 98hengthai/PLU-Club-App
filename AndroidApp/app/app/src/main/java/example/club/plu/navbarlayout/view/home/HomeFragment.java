package example.club.plu.navbarlayout.view.home;

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
import android.widget.TextView;

import java.util.List;

import example.club.plu.navbarlayout.R;
import example.club.plu.navbarlayout.adapter.home.HomeRecycleAdapter;
import example.club.plu.navbarlayout.adapter.home.OnHomeListener;
import example.club.plu.navbarlayout.model.event.Event;
import example.club.plu.navbarlayout.utils.Constants;
import example.club.plu.navbarlayout.viewModel.EventFragmentVM;

public class HomeFragment extends Fragment implements OnHomeListener {
    private EventFragmentVM mEventFragmentVM;
    private RecyclerView mRecyclerView;
    //    private List<UserEvent> mEventList;
    private HomeRecycleAdapter mEventRecycleAdapter;
    private static final String TAG = "HomeFragment";
    private TextView txtView_noEventHint;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_list, container, false);
        getActivity().setTitle("Home");
        txtView_noEventHint = (TextView) view.findViewById(R.id.textView_home_no_activity_hint);
        //init VM
        mEventFragmentVM = ViewModelProviders.of(this).get(EventFragmentVM.class);
        //get request for initial user events
        searchTodayEvent();
        //init recycle view
        mRecyclerView = view.findViewById(R.id.home_recycleList);
        //
        initRecycleView(view);
        //observe live data of club list
        subscribeObservers();

        return view;
    }

    private void subscribeObservers() {
        mEventFragmentVM.getTodayEvent().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(@Nullable List<Event> events) {
                if(events != null) {
                    Log.d(TAG, "onChanged: subscribeObservers - " + events);
                    mEventRecycleAdapter.setEvents(events);
                    txtView_noEventHint.setVisibility(View.GONE);
                } else if (events .isEmpty()){
                    txtView_noEventHint.setVisibility(View.VISIBLE);
                } else {
                    txtView_noEventHint.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    private void initRecycleView(View view) {
        mEventRecycleAdapter = new HomeRecycleAdapter(this);
        mRecyclerView.setAdapter(mEventRecycleAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    private void searchTodayEvent() {
        mEventFragmentVM.searchTodayEvent(Constants.USER_EMAIL);
    }


    @Override
    public void onEventClick(int position) {

    }

    @Override
    public void onCategoryClick(String category) {

    }
}
