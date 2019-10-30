package example.club.plu.navbarlayout.model.club;

import android.os.Parcel;
import android.os.Parcelable;

public class Interests implements Parcelable {
    private String name;

    public Interests(String name) {
        this.name = name;
    }

    public Interests() {
    }

    protected Interests(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Interests> CREATOR = new Creator<Interests>() {
        @Override
        public Interests createFromParcel(Parcel in) {
            return new Interests(in);
        }

        @Override
        public Interests[] newArray(int size) {
            return new Interests[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Interests{" +
                "name='" + name + '\'' +
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
        dest.writeString(name);
    }
}
