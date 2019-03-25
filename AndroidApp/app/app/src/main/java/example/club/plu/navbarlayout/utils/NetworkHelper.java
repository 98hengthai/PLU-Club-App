package example.club.plu.navbarlayout.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkHelper {

    public static boolean hasNetworkAccess(Context context){
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        try{
            return activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
