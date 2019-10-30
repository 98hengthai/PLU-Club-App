package entities;

public class UserEvents {
    private String eventidNumber;
    private String clubName;
    private String userEmail;
    private String reply;

    public UserEvents(){

    }

    public UserEvents(String eventid, String cName, String userEmail, String reply){
        this.eventidNumber = eventid;
        this.clubName = cName;
        this.userEmail = userEmail;
        this.reply = reply;
    }

    public String getEventidNumber() {
        return eventidNumber;
    }

    public void setEventidNumber(String eventidNumber) {
        this.eventidNumber = eventidNumber;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
