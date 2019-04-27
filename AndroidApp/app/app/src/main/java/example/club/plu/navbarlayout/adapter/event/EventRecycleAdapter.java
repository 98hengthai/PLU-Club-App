package example.club.plu.navbarlayout.adapter.event;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.List;

import example.club.plu.navbarlayout.R;
import example.club.plu.navbarlayout.model.event.Event;

public class EventRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "EventRecycleAdapter";
    private List<Event> mEventList;
    private OnEventListener mOnEventListener;

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
        Log.d(TAG, "onBindViewHolder: " + mEventList.get(i).getName());
        Log.d(TAG, "onBindViewHolder: " + ((EventViewHolder)viewHolder).eventTitle );
//        Log.d(TAG, "onBindViewHolder: " + );
        ((EventViewHolder)viewHolder).eventTitle.setText(mEventList.get(i).getName());
        ((EventViewHolder)viewHolder).startTime.setText(mEventList.get(i).getStart_time());
    }

    @Override
    public int getItemCount() {
        if(mEventList != null){
            return mEventList.size();
        }
        return 0;
    }

    public void setEvents(List<Event> events){
        mEventList = events;
        notifyDataSetChanged();
    }
    public Event getSelectedEvent(int position) {
        if(mEventList != null) {
            if(mEventList.size() > 0) {
                return mEventList.get(position);
            }

        }
        return  null;
    }


}
