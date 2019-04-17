package example.club.plu.navbarlayout.utils;
import android.util.Log;
import java.util.List;
import example.club.plu.navbarlayout.model.Club;
import example.club.plu.navbarlayout.model.Event;


public class Testing {
    public static void printClubs(List<Club> list, String tag){
        for (Club club : list) {
            Log.d(tag, "onChanged: " + club.getName());
        }
    }

    public static void printEvents(List<Event> list, String tag) {
        for(Event ev : list) {
            Log.d(tag, "onChanged: " + ev.getName());
        }
    }
}
