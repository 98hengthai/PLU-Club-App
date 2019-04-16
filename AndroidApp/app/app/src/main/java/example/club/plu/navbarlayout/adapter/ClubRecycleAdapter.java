package example.club.plu.navbarlayout.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import example.club.plu.navbarlayout.R;
import example.club.plu.navbarlayout.model.Club;

public class ClubRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Club> mClubList;
    private  OnClubListener mOnClubListener;

    public ClubRecycleAdapter(OnClubListener mOnClubListener) {
        this.mOnClubListener = mOnClubListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_clubs_list_item, viewGroup, false);
        return new ClubViewHolder(view, mOnClubListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ClubViewHolder)viewHolder).title.setText(mClubList.get(i).getName());
        //TODO:each club homepage
    }

    @Override
    public int getItemCount() {
        if(mClubList != null){
            return mClubList.size();
        }
        return 0;
    }

    public void setClubs(List<Club> clubs){
        mClubList = clubs;
        notifyDataSetChanged();
    }
}
