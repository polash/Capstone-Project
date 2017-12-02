package com.sksanwar.cricketbangla.Pojo.RecentMatchPojo;


import android.os.Parcel;
import android.os.Parcelable;

import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Match;

import java.util.ArrayList;

/**
 * Created by sksho on 02-Dec-17.
 */

public class RecentMatches implements Parcelable {
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<RecentMatches> CREATOR = new Parcelable.Creator<RecentMatches>() {
        @Override
        public RecentMatches createFromParcel(Parcel in) {
            return new RecentMatches(in);
        }

        @Override
        public RecentMatches[] newArray(int size) {
            return new RecentMatches[size];
        }
    };
    private ArrayList<Match> matches;

    public RecentMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }


    protected RecentMatches(Parcel in) {
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
        return "RecentMatches{" +
                "matches=" + matches +
                '}';
    }
}
