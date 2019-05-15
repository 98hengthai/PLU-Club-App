package example.club.plu.navbarlayout.adapter.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import example.club.plu.navbarlayout.R;
import example.club.plu.navbarlayout.model.event.Event;


public class HomeRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "EventRecycleAdapter";
    private OnHomeListener mOnEventListener;
    //new
    private List<Event> mUserEvents;

    public HomeRecycleAdapter(OnHomeListener mOnEventListener) {
        this.mOnEventListener = mOnEventListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_home_list_item, viewGroup, false);
        return new HomeViewHolder(view, mOnEventListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: mUserEvents - " + mUserEvents.get(i));

        ((HomeViewHolder)viewHolder).eventName.setText(mUserEvents.get(i).getName());
        ((HomeViewHolder)viewHolder).eventTime.setText(mUserEvents.get(i).getStart_time());
        ((HomeViewHolder)viewHolder).eventLocation.setText(mUserEvents.get(i).getLocation());
        ((HomeViewHolder)viewHolder).eventClubName.setText(mUserEvents.get(i).getClubName());
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