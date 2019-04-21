package example.club.plu.navbarlayout.request.response;

public class EventResponseWork {
    private String id, name, location, startTime, endTime, repeating, clubName;


    public EventResponseWork() {
    }

    public EventResponseWork(String id, String name, String location, String startTime, String endTime, String repeating, String clubName) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.clubName = clubName;
        this.startTime = startTime;
        this.repeating = repeating;
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRepeating() {
        return repeating;
    }

    public void setRepeating(String repeating) {
        this.repeating = repeating;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    //toString


    @Override
    public String toString() {
        return "EventResponseWork{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", repeating='" + repeating + '\'' +
                ", clubName='" + clubName + '\'' +
                '}';
    }
}
