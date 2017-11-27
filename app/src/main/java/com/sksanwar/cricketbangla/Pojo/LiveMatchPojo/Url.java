
package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Url implements Parcelable {

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Url> CREATOR = new Parcelable.Creator<Url>() {
        @Override
        public Url createFromParcel(Parcel in) {
            return new Url(in);
        }

        @Override
        public Url[] newArray(int size) {
            return new Url[size];
        }
    };
    public String match;

    public Url(String match) {
        this.match = match;
    }

    protected Url(Parcel in) {
        match = in.readString();
    }

    public String getMatch() {
        return match;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(match);
    }

    @Override
    public String toString() {
        return "Url{" +
                "match='" + match + '\'' +
                '}';
    }
}
