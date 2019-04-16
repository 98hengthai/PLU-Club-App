package example.club.plu.navbarlayout.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import example.club.plu.navbarlayout.R;
import example.club.plu.navbarlayout.request.ClubApi;
import example.club.plu.navbarlayout.request.ServiceGenerator;
import example.club.plu.navbarlayout.request.response.ClubSearchResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().setTitle("Home");
       return view;
    }


}
