
package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class LiveMatches implements Parcelable {

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<LiveMatches> CREATOR = new Parcelable.Creator<LiveMatches>() {
        @Override
        public LiveMatches createFromParcel(Parcel in) {
            return new LiveMatches(in);
        }

        @Override
        public LiveMatches[] newArray(int size) {
            return new LiveMatches[size];
        }
    };
    private ArrayList<Match> matches;

    public LiveMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    protected LiveMatches(Parcel in) {
        if (in.readByte() == 0x01) {
            matches = new ArrayList<Match>();
            in.readList(matches, Match.class.getClassLoader());
        } else {
            matches = null;
        }
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (matches == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(matches);
        }
    }

    @Override
    public String toString() {
        return "LiveMatches{" +
                "matches=" + matches +
                '}';
    }
}
