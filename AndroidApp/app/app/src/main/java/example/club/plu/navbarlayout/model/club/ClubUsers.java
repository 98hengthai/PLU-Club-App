package example.club.plu.navbarlayout.model.club;

import android.os.Parcel;
import android.os.Parcelable;

public class ClubUsers implements Parcelable {
    private String clubName;
    private String userEmail;
    private String role;

    public ClubUsers(){

    }

    public ClubUsers(String clubName, String userEmail, String role){
        this.clubName = clubName;
        this.userEmail = userEmail;
        this.role = role;
    }

    protected ClubUsers(Parcel in) {
        clubName = in.readString();
        userEmail = in.readString();
        role = in.readString();
    }

    public static final Creator<ClubUsers> CREATOR = new Creator<ClubUsers>() {
        @Override
        public ClubUsers createFromParcel(Parcel in) {
            return new ClubUsers(in);
        }

        @Override
        public ClubUsers[] newArray(int size) {
            return new ClubUsers[size];
        }
    };

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "ClubUsers{" +
                "clubName='" + clubName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(clubName);
        dest.writeString(userEmail);
        dest.writeString(role);
    }
}

