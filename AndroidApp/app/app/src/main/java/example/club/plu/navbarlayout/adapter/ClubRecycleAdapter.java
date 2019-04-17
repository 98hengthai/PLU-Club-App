package example.club.plu.navbarlayout.adapter;

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
import example.club.plu.navbarlayout.model.Club;

import static android.support.constraint.Constraints.TAG;

public class ClubRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    private static final String TAG = "ClubRecycleAdapter";
    private List<Club> mClubList;
    private List<Club> mClubListFull; //a copied of themClubList that uses for filter.
    private  OnClubListener mOnClubListener;

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
        //make a copied of the club list for filter
        mClubListFull = new ArrayList<>();
        mClubListFull.addAll(clubs);
        notifyDataSetChanged();
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
}
