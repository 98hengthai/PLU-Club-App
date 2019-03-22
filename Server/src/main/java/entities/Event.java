package entities;

public class Event {
    private String idNumber;
    private String name;
    private String location;
    private String start_time;
    private String end_time;
    private String repeat;
    private String clubName;

    public Event(){
    }

    //Constructors
    public Event(String id, String name, String loc, String st_time, String end_time, String repeat, String cName){
        this.idNumber = id;
        this.name = name;
        this.location = loc;
        this.start_time = st_time;
        this.end_time = end_time;
        this.repeat = repeat;
        this.clubName = cName;
    }

    //Getters and Setters
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
}
