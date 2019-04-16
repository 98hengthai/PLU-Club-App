package common;

public class References {
    //Message codes -----------------------------------------

    /**
     * Use this URL to access the DB when you are off campus and have SSH tunnel open
     */
    public static final String OFF_CAMPUS_DB_URL = "jdbc:mysql://localhost:2000/clubs_499_2019";

    /**
     * Use this URL to access the DB when you are on campus
     */
    public static final String ON_CAMPUS_DB_URL = "jdbc:mysql://mal.cs.plu.edu:3306/clubs_499_2019";

    /**
     * Username for the PLU MySQL database access
     */
    public static final String USERNAME = "clubuser_499";

    /**
     * Password for the PLU MySQL database access
     */
    public static final String PASSWORD = "changeme";
    /**
     * Outdated DB URL for a local SQLite database
     */
    public static final String LOCAL_OLD_SQLITE_DB_URL = "jdbc:sqlite:/D:/SQLServer/ClubDatabase";

    /**
     * These are the error codes for returning to in-progress work
     */
    public static final String ERROR_403_EDIT = "ERROR 403 - Edit Failed";

    public static final String ERROR_403_DELETE = "ERROR 403 - Delete Failed";

    public static final String ERROR_403_CREATE = "ERROR 403 - Create Failed";

    public static final String ERROR_CODE_503 = "ERROR 503 - Service Unavailable";

    public static final String ERROR_CODE_404 = "ERROR 404 - Resource Not Found";

    public static final String API_CODE_200 = "Code 200 - Successful";

    public static final String API_CODE_201 = "Code 201 - Successfully Created";

}
