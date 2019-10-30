package example.club.plu.navbarlayout.adapter.event;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import example.club.plu.navbarlayout.R;

public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView eventName, eventTime, eventLocation, eventClubName;
    public OnEventListener onEventListener;

    public EventViewHolder(@NonNull View itemView, OnEventListener onEventInstance) {
        super(itemView);
        this.onEventListener = onEventInstance;
        eventName = (TextView) itemView.findViewById(R.id.textView_event_name);
        eventTime = (TextView) itemView.findViewById(R.id.textView_event_time);
        eventLocation = (TextView) itemView.findViewById(R.id.textView_event_location);
        eventClubName = (TextView) itemView.findViewById(R.id.textView_event_clubname);

        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        onEventListener.onEventClick(getAdapterPosition());
    }
}
