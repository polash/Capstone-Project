
package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

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
    public Url url;
    public List<Match> matches;

    public LiveMatches(Url url, List<Match> matches) {
        this.url = url;
        this.matches = matches;
    }

    protected LiveMatches(Parcel in) {
        url = (Url) in.readValue(Url.class.getClassLoader());
        if (in.readByte() == 0x01) {
            matches = new ArrayList<Match>();
            in.readList(matches, Match.class.getClassLoader());
        } else {
            matches = null;
        }
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(url);
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
                "url=" + url +
                ", matches=" + matches +
                '}';
    }
}
