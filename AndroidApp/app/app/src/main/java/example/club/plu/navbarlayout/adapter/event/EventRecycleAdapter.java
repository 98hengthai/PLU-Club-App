package example.club.plu.navbarlayout.adapter.event;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import example.club.plu.navbarlayout.R;
import example.club.plu.navbarlayout.model.event.Event;
import example.club.plu.navbarlayout.model.event.Home;
import example.club.plu.navbarlayout.model.event.UserEvent;

public class EventRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "EventRecycleAdapter";
    private OnEventListener mOnEventListener;
    //new
    private List<Event> mUserEvents;

    public EventRecycleAdapter(OnEventListener mOnEventListener) {
        this.mOnEventListener = mOnEventListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_event_list_item, viewGroup, false);
        return new EventViewHolder(view, mOnEventListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((EventViewHolder)viewHolder).eventName.setText(mUserEvents.get(i).getName());
        ((EventViewHolder)viewHolder).eventTime.setText(mUserEvents.get(i).getStart_time());
//        ((EventViewHolder)viewHolder).eventTime.setL;
        ((EventViewHolder)viewHolder).eventLocation.setText(mUserEvents.get(i).getLocation());
        ((EventViewHolder)viewHolder).eventClubName.setText(mUserEvents.get(i).getClubName());
    }

    @Override
    public int getItemCount() {
        if (mUserEvents != null) {
            return mUserEvents.size();
        }
        return 0;
    }

    public void setEvents(List<Event> events) {
        mUserEvents = events;
        notifyDataSetChanged();
    }

    public Event getSelectedEvent(int position) {
        if (mUserEvents != null) {
            if (mUserEvents.size() > 0) {
                return mUserEvents.get(position);
            }
        }
        return null;
    }



}