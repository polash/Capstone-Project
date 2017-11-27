
package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Inning implements Parcelable {

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Inning> CREATOR = new Parcelable.Creator<Inning>() {
        @Override
        public Inning createFromParcel(Parcel in) {
            return new Inning(in);
        }

        @Override
        public Inning[] newArray(int size) {
            return new Inning[size];
        }
    };

    private String id;
    private String score;
    private String wkts;
    private String overs;

    public Inning(String id, String score, String wkts, String overs) {
        this.id = id;
        this.score = score;
        this.wkts = wkts;
        this.overs = overs;
    }

    protected Inning(Parcel in) {
        id = in.readString();
        score = in.readString();
        wkts = in.readString();
        overs = in.readString();
    }

    public String getId() {
        return id;
    }

    public String getScore() {
        return score;
    }

    public String getWkts() {
        return wkts;
    }

    public String getOvers() {
        return overs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(score);
        dest.writeString(wkts);
        dest.writeString(overs);
    }

    @Override
    public String toString() {
        return "Inning{" +
                "id='" + id + '\'' +
                ", score='" + score + '\'' +
                ", wkts='" + wkts + '\'' +
                ", overs='" + overs + '\'' +
                '}';
    }
}
