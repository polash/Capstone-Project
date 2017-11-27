
package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Venue implements Parcelable {

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Venue> CREATOR = new Parcelable.Creator<Venue>() {
        @Override
        public Venue createFromParcel(Parcel in) {
            return new Venue(in);
        }

        @Override
        public Venue[] newArray(int size) {
            return new Venue[size];
        }
    };
    private String name;
    private String location;
    private String timezone;

    public Venue(String name, String location, String timezone) {
        this.name = name;
        this.location = location;
        this.timezone = timezone;
    }

    protected Venue(Parcel in) {
        name = in.readString();
        location = in.readString();
        timezone = in.readString();
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getTimezone() {
        return timezone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(timezone);
    }

    @Override
    public String toString() {
        return "Venue{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", timezone='" + timezone + '\'' +
                '}';
    }
}
