package example.club.plu.navbarlayout.utils;

import android.util.Log;

import java.util.List;

import example.club.plu.navbarlayout.model.Club;

public class Testing {
    public static void printClubs(List<Club> list, String tag){
        for (Club club : list) {
            Log.d(tag, "onChanged: " + club.getName());
        }
    }
}
