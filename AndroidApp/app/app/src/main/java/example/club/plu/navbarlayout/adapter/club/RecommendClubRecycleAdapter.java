package example.club.plu.navbarlayout.adapter.club;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import example.club.plu.navbarlayout.R;
import example.club.plu.navbarlayout.model.club.Club;
import example.club.plu.navbarlayout.model.club.ClubUsers;

public class RecommendClubRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "RcdClubRecycleAdapter";
    private List<Club> mClubList;
    private List<String> mClubJoined;
    private OnClubListener mOnClubListener;

    public RecommendClubRecycleAdapter(OnClubListener mOnClubListener) {
        this.mOnClubListener = mOnClubListener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_recommend_club_list_item, viewGroup, false);
        return new RecmdClubViewHolder(view, mOnClubListener);
    }

    //when init the adapter, init the each UI field. In our case, only the club title.
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: " + ((RecmdClubViewHolder) viewHolder).title);
        ((RecmdClubViewHolder) viewHolder).title.setText(mClubList.get(i).getName());
        //show joined clubs
        //String list of all club
        if (mClubJoined != null) {
            if (mClubList.get(i) != null) {
                if (mClubJoined.contains(mClubList.get(i).getName()))
                    ((RecmdClubViewHolder) viewHolder).isJoined.setVisibility(View.VISIBLE);
            }
        }
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

    public List<Club> getClubs(){
        return mClubList;
    }


    public Club getSelectedClub(int position) {
        if(mClubList != null) {
            if(mClubList.size() > 0) {
                return mClubList.get(position);
            }
        }
        return  null;
    }

    public void setJoinedClub(List<ClubUsers> clubUsers) {
        mClubJoined = new ArrayList<>();
        for(ClubUsers cu : clubUsers){
            mClubJoined.add(cu.getClubName());
        }
    }

    public boolean isJoinedClub(int position) {
        return mClubJoined.contains(mClubList.get(position).getName());
    }
}