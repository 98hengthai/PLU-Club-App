package example.club.plu.navbarlayout.adapter.event;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import example.club.plu.navbarlayout.R;

public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView eventTitle, startTime;
    OnEventListener onEventListener;

    public EventViewHolder(@NonNull View itemView, OnEventListener onEventInstance) {
        super(itemView);
        this.onEventListener = onEventInstance;
        eventTitle = itemView.findViewById(R.id.event_title);
        startTime = itemView.findViewById(R.id.event_start);
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        onEventListener.onEventClick(getAdapterPosition());
    }
}
