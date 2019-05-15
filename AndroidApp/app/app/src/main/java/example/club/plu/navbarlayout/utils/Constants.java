package example.club.plu.navbarlayout.utils;

public class Constants {
    public static final String SERVER_URL = "http://10.0.2.2:4567/";
    public static final String Phone_SERVER_URL = "http://152.117.220.160:4567/";
    public static final int NETWORK_TIMEOUT = 3000;
    public static final String testEmail = "Jeremy@plu.edu";
    public static String USER_EMAIL = null;
    public static final String SHARED_DATA = "pluclubapp-sharedData";
    public static final String USER = "pluclubapp-user";

    public static void setUserEmail(String email) {
        USER_EMAIL = email;
    }
}
