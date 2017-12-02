package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sksho on 02-Dec-17.
 */

public class Score implements Parcelable {

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Score> CREATOR = new Parcelable.Creator<Score>() {
        @Override
        public Score createFromParcel(Parcel in) {
            return new Score(in);
        }

        @Override
        public Score[] newArray(int size) {
            return new Score[size];
        }
    };
    private String team_id;
    private String score;
    private String wkts;
    private String overs;

    public Score(String team_id, String score, String wkts, String overs) {
        this.team_id = team_id;
        this.score = score;
        this.wkts = wkts;
        this.overs = overs;
    }

    protected Score(Parcel in) {
        team_id = in.readString();
        score = in.readString();
        wkts = in.readString();
        overs = in.readString();
    }

    public String getTeam_id() {
        return team_id;
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
        dest.writeString(team_id);
        dest.writeString(score);
        dest.writeString(wkts);
        dest.writeString(overs);
    }

    @Override
    public String toString() {
        return "Score{" +
                "team_id='" + team_id + '\'' +
                ", score='" + score + '\'' +
                ", wkts='" + wkts + '\'' +
                ", overs='" + overs + '\'' +
                '}';
    }
}
