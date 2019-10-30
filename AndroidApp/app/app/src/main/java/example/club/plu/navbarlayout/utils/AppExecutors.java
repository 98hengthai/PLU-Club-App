package example.club.plu.navbarlayout.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {
    private static  AppExecutors instance; //singleton

    public static  AppExecutors getInstance(){
        if(instance == null){
            instance = new AppExecutors();
        }
        return  instance;
    }

    //schedule command after a delayed with ThreadPool
    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService networkIO() {
       return mNetworkIO;
    }
}
