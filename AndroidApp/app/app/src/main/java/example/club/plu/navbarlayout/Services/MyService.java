package example.club.plu.navbarlayout.Services;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import example.club.plu.navbarlayout.Clubs.Clubs;
import example.club.plu.navbarlayout.utils.HttpHelper;

public class MyService extends IntentService {

    public static final String TAG = "MyService";
    public static final String MY_SERVICE_MESSAGE = "Service Message";
    public static final String MY_SERVICE_PAYLOAD = "Service Payload";
    public MyService() {
        super("MyService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Uri uri = intent.getData();
        Log.i(TAG, "onHandleIntent: " + uri.toString());
        String response;
        response = HttpHelper.downloadUrl(uri.toString());

        Gson gson = new Gson();
        Clubs[] club = gson.fromJson(response, Clubs[].class);


        Intent message = new Intent(MY_SERVICE_MESSAGE);
        message.putExtra(MY_SERVICE_PAYLOAD, club);
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(getApplicationContext());
        manager.sendBroadcast(message);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
