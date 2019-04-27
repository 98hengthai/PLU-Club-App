package example.club.plu.navbarlayout.model.club;
import android.os.Parcel;
import android.os.Parcelable;

public class Club implements Parcelable {
    private String name;
    private String location;
    private String clubEmail;
    private String description;

    public Club() {
    }

    public Club(String name, String location, String clubEmail, String description) {
        this.name = name;
        this.location = location;
        this.clubEmail = clubEmail;
        this.description = description;
    }

    //Parcelable
    protected Club(Parcel in) {
        name = in.readString();
        location = in.readString();
        clubEmail = in.readString();
        description = in.readString();
    }
    public static final Creator<Club> CREATOR = new Creator<Club>() {
        @Override
        public Club createFromParcel(Parcel in) {
            return new Club(in);
        }
        @Override
        public Club[] newArray(int size) {
            return new Club[size];
        }
    };
    //Parcelable Override
    @Override
    public int describeContents() {
        return 0;
    }
    @Override

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(clubEmail);
        dest.writeString(description);
    }

    //Getter and Setter
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

    public String getClubEmail() {
        return clubEmail;
    }

    public void setClubEmail(String clubEmail) {
        this.clubEmail = clubEmail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //toString

    @Override
    public String toString() {
        return "Club{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", clubEmail='" + clubEmail + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
