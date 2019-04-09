package example.club.plu.navbarlayout.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {
    //declaration
    private String id, name, location, startTime, endTime, repeating, clubName;
    //Constructor with all var
    public Event(String id, String name, String location, String startTime, String endTime, String repeating, String clubName) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.repeating = repeating;
        this.clubName = clubName;
    }
    //Empty Constructor
    public Event() {
    }

    //Parcelable
    protected Event(Parcel in) {
        id = in.readString();
        name = in.readString();
        location = in.readString();
        startTime = in.readString();
        endTime = in.readString();
        repeating = in.readString();
        clubName = in.readString();
    }
    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(startTime);
        dest.writeString(endTime);
        dest.writeString(repeating);
        dest.writeString(clubName);
    }

    //Getter and Setter for all var
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
        return "Event{" +
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
