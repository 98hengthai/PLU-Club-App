package example.club.plu.navbarlayout.utils;
import android.util.Log;
import java.util.List;
import example.club.plu.navbarlayout.model.club.Club;
import example.club.plu.navbarlayout.model.club.ClubUsers;
import example.club.plu.navbarlayout.model.event.Event;


public class Testing {
    public static void printClubs(List<Club> list, String tag){
        for (Club club : list) {
            Log.d(tag, "onChanged: " + club.getName());
        }
    }

    public static void printClubUsers(List<ClubUsers> list, String tag){
        for (ClubUsers club : list) {
            Log.d(tag, "onChanged: " + club.getClubName());
        }
    }

    public static void printEvents(List<Event> list, String tag) {
        for(Event ev : list) {
            Log.d(tag, "onChanged: " + ev.getName());
        }
    }
}
