package example.club.plu.navbarlayout.adapter.club;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

import example.club.plu.navbarlayout.R;
import example.club.plu.navbarlayout.model.club.Club;
import example.club.plu.navbarlayout.model.club.ClubUsers;
import example.club.plu.navbarlayout.request.club.ClubApiClient;

public class ClubRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    private static final String TAG = "ClubRecycleAdapter";
    private List<String> mClubJoined;
    private List<Club> mClubList;
    private List<Club> mClubListFull; //a copied of themClubList that uses for filter.
    private OnClubListener mOnClubListener;

    public ClubRecycleAdapter(OnClubListener mOnClubListener) {
        this.mOnClubListener = mOnClubListener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_all_club_list_item, viewGroup, false);
        return new ClubViewHolder(view, mOnClubListener);
    }

    //when init the adapter, init the each UI field. In our case, only the club title.
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: " + ((ClubViewHolder)viewHolder).title );
        ((ClubViewHolder)viewHolder).title.setText(mClubList.get(i).getName());
        //show joined clubs
        //String list of all club

        if( mClubList != null && mClubJoined !=null) {
            if (mClubList.get(i) != null) {
                Log.d(TAG, "onBindViewHolder: mClubJoined - " + mClubJoined);
                Log.d(TAG, "onBindViewHolder: mClubList.get(i - " + mClubList.get(i));
                if (mClubJoined.contains(mClubList.get(i).getName())) {
                    ((ClubViewHolder) viewHolder).isJoined.setVisibility(View.VISIBLE);
                }
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
        //make a copied of the club list for filter
        mClubListFull = new ArrayList<>();
        mClubListFull.addAll(clubs);
        notifyDataSetChanged();
    }

    public void setJoinedClub(List<ClubUsers> clubUsers) {
        mClubJoined = new ArrayList<>();
        for(ClubUsers cu : clubUsers){
            mClubJoined.add(cu.getClubName());
        }
    }

    public List<Club> getClubs(){
        return mClubList;
    }

    //filter clubs depending on user search input
    //only use the copied of the club list, so no changed makes to the original list.
    @Override
    public Filter getFilter() {
        return clubFilter;
    }
    private Filter clubFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            Log.d(TAG, "performFiltering: mClubFull " + mClubListFull.size());
            List<Club> filteredClubList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0) {
                filteredClubList.addAll(mClubListFull);
                Log.d(TAG, "performFiltering: addin " + filteredClubList.size());
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(Club club : mClubListFull){
                    if(club.getName().toLowerCase().contains(filterPattern)){
                        filteredClubList.add(club);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredClubList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if(mClubList != null) {
                mClubList.clear();
            } else {
                Log.d(TAG, "publishResults: " + "null mClubList. (Possibly no connection).");
            }
            if(results.values != null) {
                mClubList.addAll((List<Club>) results.values);
            } else {

            }
            notifyDataSetChanged();
        }
    };

    public Club getSelectedClub(int position) {
        if(mClubList != null) {
            if(mClubList.size() > 0) {
                return mClubList.get(position);
            }

        }
        return  null;
    }


    public boolean isJoinedClub(int position) {
        return mClubJoined.contains(mClubList.get(position).getName());
    }
}
