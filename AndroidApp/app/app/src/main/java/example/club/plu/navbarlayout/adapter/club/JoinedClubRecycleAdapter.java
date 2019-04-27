package example.club.plu.navbarlayout.adapter.club;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import example.club.plu.navbarlayout.R;
import example.club.plu.navbarlayout.model.club.ClubUsers;

public class JoinedClubRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "JoinedClubRecycleAdapter";
    private List<ClubUsers> mClubList;
    private OnClubListener mOnClubListener;

    public JoinedClubRecycleAdapter(OnClubListener mOnClubListener) {
        this.mOnClubListener = mOnClubListener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_joined_club_list_item, viewGroup, false);
        return new ClubUsersViewHolder(view, mOnClubListener);
    }

    //when init the adapter, init the each UI field. In our case, only the club title.
    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: " + ((ClubUsersViewHolder)viewHolder).title );
        ((ClubUsersViewHolder)viewHolder).title.setText(mClubList.get(i).getClubName());
        //TODO:each club homepage
    }

    @Override
    public int getItemCount() {
        if(mClubList != null){
            return mClubList.size();
        }
        return 0;
    }

    public void setClubs(List<ClubUsers> clubs){
        mClubList = clubs;
        notifyDataSetChanged();
    }

    public List<ClubUsers> getClubs(){
        return mClubList;
    }


    public ClubUsers getSelectedClub(int position) {
        if(mClubList != null) {
            if(mClubList.size() > 0) {
                return mClubList.get(position);
            }
        }
        return  null;
    }
}