package example.club.plu.navbarlayout.model.event;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {
    //declaration
    private String idNumber, name, location, start_time, end_time, repeat, clubName;
    //Constructor with all var
    public Event(String id, String name, String location, String start_time, String endTime, String repeating, String clubName) {
        this.idNumber = id;
        this.name = name;
        this.location = location;
        this.start_time = start_time;
        this.end_time = endTime;
        this.repeat = repeating;
        this.clubName = clubName;
    }

    //Empty Constructor
    public Event(int i, String new_event, String morken, String start_time, String endTime, boolean b, String capstone_club) {
    }

    //Parcelable
    protected Event(Parcel in) {
        idNumber = in.readString();
        name = in.readString();
        location = in.readString();
        start_time = in.readString();
        end_time = in.readString();
        repeat = in.readString();
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
        dest.writeString(idNumber);
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(start_time);
        dest.writeString(end_time);
        dest.writeString(repeat);
        dest.writeString(clubName);
    }

    //Getter and Setter for all var
    public String getId() {
        return idNumber;
    }

    public void setId(String id) {
        this.idNumber = id;
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

    @Override
    public String toString() {
        return "Event{" +
                "idNumber='" + idNumber + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", repeat='" + repeat + '\'' +
                ", clubName='" + clubName + '\'' +
                '}';
    }
}
