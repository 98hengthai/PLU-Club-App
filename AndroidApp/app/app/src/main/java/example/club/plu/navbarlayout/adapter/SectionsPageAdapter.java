package example.club.plu.navbarlayout.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import example.club.plu.navbarlayout.view.club.AllClubFragment;
import example.club.plu.navbarlayout.view.club.JoinedClubFragment;
import example.club.plu.navbarlayout.view.club.RecommendClubFragment;

public class SectionsPageAdapter extends FragmentPagerAdapter {
    //list to hold the tab fragments
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    /**
     * Adding Fragment with the title
     * @param fragment
     * @param title
     */
    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

   //constructor
    public SectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    //get fragment given position
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    //get fragment list size
    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    //get title of the fragment
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
