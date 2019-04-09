package example.club.plu.navbarlayout.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import example.club.plu.navbarlayout.R;

public class ClubViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView title;
    OnClubListener onClubListener;

    public ClubViewHolder(@NonNull View itemView, OnClubListener onClubListener) {
        super(itemView);
        this.onClubListener = onClubListener;
        title = itemView.findViewById(R.id.club_title);

        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        onClubListener.onClubClick(getAdapterPosition());
    }
}
