package example.club.plu.navbarlayout.utils;

import android.util.Log;

import java.util.List;

import example.club.plu.navbarlayout.model.Club;
<<<<<<< HEAD
import example.club.plu.navbarlayout.model.Event;
=======
>>>>>>> cb198f05b50fced86c34d98e20dd279187f8455d

public class Testing {
    public static void printClubs(List<Club> list, String tag){
        for (Club club : list) {
            Log.d(tag, "onChanged: " + club.getName());
        }
    }
<<<<<<< HEAD

    public static void printEvents(List<Event> list, String tag) {
        for(Event ev : list) {
            Log.d(tag, "onChanged: " + ev.getName());
        }
    }
=======
>>>>>>> cb198f05b50fced86c34d98e20dd279187f8455d
}
