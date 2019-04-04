package example.club.plu.navbarlayout.Clubs;

import android.os.Parcel;
import android.os.Parcelable;

public class Clubs implements Parcelable {

    private String name;
    private String location;
    private String clubEmail;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Clubs withName(String name) {
        this.name = name;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Clubs withLocation(String location) {
        this.location = location;
        return this;
    }

    public String getClubEmail() {
        return clubEmail;
    }

    public void setClubEmail(String clubEmail) {
        this.clubEmail = clubEmail;
    }

    public Clubs withClubEmail(String clubEmail) {
        this.clubEmail = clubEmail;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Clubs withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.location);
        dest.writeString(this.clubEmail);
        dest.writeString(this.description);
    }

    public Clubs() {
    }

    protected Clubs(Parcel in) {
        this.name = in.readString();
        this.location = in.readString();
        this.clubEmail = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<Clubs> CREATOR = new Parcelable.Creator<Clubs>() {
        @Override
        public Clubs createFromParcel(Parcel source) {
            return new Clubs(source);
        }

        @Override
        public Clubs[] newArray(int size) {
            return new Clubs[size];
        }
    };
}