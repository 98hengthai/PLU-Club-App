package example.club.plu.navbarlayout.adapter.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import example.club.plu.navbarlayout.R;

public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView eventName, eventTime, eventLocation, eventClubName;
    public OnHomeListener onEventListener;

    public HomeViewHolder(@NonNull View itemView, OnHomeListener onEventInstance) {
        super(itemView);
        this.onEventListener = onEventInstance;
        eventName = (TextView) itemView.findViewById(R.id.textView_home_event_name);
        eventTime = (TextView) itemView.findViewById(R.id.textView_home_event_time);
        eventLocation = (TextView) itemView.findViewById(R.id.textView_home_event_location);
        eventClubName = (TextView) itemView.findViewById(R.id.textView_home_event_clubname);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onEventListener.onEventClick(getAdapterPosition());
    }
}
