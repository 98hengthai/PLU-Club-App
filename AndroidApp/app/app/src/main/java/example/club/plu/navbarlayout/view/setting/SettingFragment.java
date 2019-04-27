package example.club.plu.navbarlayout.view.setting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import example.club.plu.navbarlayout.R;

public class SettingFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        getActivity().setTitle("Setting");
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
//        TextView test = view.findViewById(R.id.setting_testing_dataholer);
//        test.setText("Hello" + DataHolder.getInstance().getEmail());
        return view;
    }
}
