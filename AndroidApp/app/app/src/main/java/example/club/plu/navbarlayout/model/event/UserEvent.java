package example.club.plu.navbarlayout.model.event;

import android.os.Parcel;
import android.os.Parcelable;

public class UserEvent implements Parcelable{




        //declaration
    private String idNumber, clubName, userEmail, reply;
        //Constructor with all var

        //Empty Constructor
    public UserEvent() {
    }

        //Parcelable
    protected UserEvent(Parcel in) {
        idNumber = in.readString();
        clubName = in.readString();
        userEmail = in.readString();
        reply = in.readString();
    }

     public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        @Override
        public example.club.plu.navbarlayout.model.event.Event createFromParcel(Parcel in) {
                return new example.club.plu.navbarlayout.model.event.Event(in);
            }

            @Override
            public example.club.plu.navbarlayout.model.event.Event[] newArray(int size) {
                return new example.club.plu.navbarlayout.model.event.Event[size];
            }
        };
        @Override
        public int describeContents() {
            return 0;
        }
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(idNumber);
            dest.writeString(clubName);
            dest.writeString(userEmail);
            dest.writeString(reply);
        }


    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
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

    public static Creator<Event> getCREATOR() {
        return CREATOR;
    }

    @Override
    public String toString() {
        return "UserEvent{" +
                "idNumber='" + idNumber + '\'' +
                ", clubName='" + clubName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", reply='" + reply + '\'' +
                '}';
    }
}
