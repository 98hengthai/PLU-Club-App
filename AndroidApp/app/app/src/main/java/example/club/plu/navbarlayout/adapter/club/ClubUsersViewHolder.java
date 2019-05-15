package example.club.plu.navbarlayout.adapter.club;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import example.club.plu.navbarlayout.R;

public class ClubUsersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView title;
    public OnClubListener onClubListener;

    public ClubUsersViewHolder(@NonNull View itemView, OnClubListener onClubListener) {
        super(itemView);
        this.onClubListener = onClubListener;
        title = (TextView) itemView.findViewById(R.id.joined_club_title);
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        onClubListener.onClubClick(getAdapterPosition());
    }
}
