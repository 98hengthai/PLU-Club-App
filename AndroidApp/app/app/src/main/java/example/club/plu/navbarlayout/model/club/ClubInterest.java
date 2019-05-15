package example.club.plu.navbarlayout.model.club;

import android.os.Parcel;
import android.os.Parcelable;

public class ClubInterest implements Parcelable {
    private String interestName;
    private String clubName;

    //Constructors
    public ClubInterest(){
    }

    public ClubInterest(String intName, String cName){
        this.interestName = intName;
        this.clubName = cName;
    }

    protected ClubInterest(Parcel in) {
        interestName = in.readString();
        clubName = in.readString();
    }

    public static final Creator<ClubInterest> CREATOR = new Creator<ClubInterest>() {
        @Override
        public ClubInterest createFromParcel(Parcel in) {
            return new ClubInterest(in);
        }

        @Override
        public ClubInterest[] newArray(int size) {
            return new ClubInterest[size];
        }
    };

    //Getters and Setters
    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
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
        dest.writeString(interestName);
        dest.writeString(clubName);
    }
}
