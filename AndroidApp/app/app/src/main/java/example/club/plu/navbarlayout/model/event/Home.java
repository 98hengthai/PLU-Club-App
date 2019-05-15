package example.club.plu.navbarlayout.model.event;

import android.os.Parcel;
import android.os.Parcelable;

public class Home implements Parcelable {



        //declaration
        private String name, start_time;
        //Constructor with all var

        //Empty Constructor
        public Home() {
        }

        //Parcelable
        protected Home(Parcel in) {
           name = in.readString();
           start_time = in.readString();
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

    public Home(String name, String s) {
        this.name = name;
        this.start_time = s;
    }

    @Override
        public int describeContents() {
            return 0;
        }
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeString(start_time);
        }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public static Creator<Event> getCREATOR() {
        return CREATOR;
    }

    @Override
    public String toString() {
        return "Home{" +
                "name='" + name + '\'' +
                ", start_time='" + start_time + '\'' +
                '}';
    }
}
