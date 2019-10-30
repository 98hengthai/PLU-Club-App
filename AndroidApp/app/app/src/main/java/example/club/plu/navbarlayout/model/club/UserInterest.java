package example.club.plu.navbarlayout.model.club;

import android.os.Parcel;
import android.os.Parcelable;

public class UserInterest implements Parcelable {
    private String userEmail;
    private String interestName;

    //Constructors
    public UserInterest(){}

    public UserInterest(String userEmail, String intName){
        this.userEmail = userEmail;
        this.interestName = intName;
    }

    protected UserInterest(Parcel in) {
        userEmail = in.readString();
        interestName = in.readString();
    }

    public static final Creator<UserInterest> CREATOR = new Creator<UserInterest>() {
        @Override
        public UserInterest createFromParcel(Parcel in) {
            return new UserInterest(in);
        }

        @Override
        public UserInterest[] newArray(int size) {
            return new UserInterest[size];
        }
    };

    //Getters and Setters
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
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
        dest.writeString(userEmail);
        dest.writeString(interestName);
    }
}
