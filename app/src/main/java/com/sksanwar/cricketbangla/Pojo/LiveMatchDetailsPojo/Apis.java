package com.sksanwar.cricketbangla.Pojo.LiveMatchDetailsPojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sksho on 26-Nov-17.
 */

public class Apis implements Parcelable {
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Apis> CREATOR = new Parcelable.Creator<Apis>() {
        @Override
        public Apis createFromParcel(Parcel in) {
            return new Apis(in);
        }

        @Override
        public Apis[] newArray(int size) {
            return new Apis[size];
        }
    };
    public String mini;
    public String scorecard;
    public String commentary;

    public Apis(String mini, String scorecard, String commentary) {
        this.mini = mini;
        this.scorecard = scorecard;
        this.commentary = commentary;
    }

    protected Apis(Parcel in) {
        mini = in.readString();
        scorecard = in.readString();
        commentary = in.readString();
    }

    public String getMini() {
        return mini;
    }

    public String getScorecard() {
        return scorecard;
    }

    public String getCommentary() {
        return commentary;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mini);
        dest.writeString(scorecard);
        dest.writeString(commentary);
    }

    @Override
    public String toString() {
        return "Apis{" +
                "mini='" + mini + '\'' +
                ", scorecard='" + scorecard + '\'' +
                ", commentary='" + commentary + '\'' +
                '}';
    }
}
