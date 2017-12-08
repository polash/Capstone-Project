
package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Header implements Parcelable {

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Header> CREATOR = new Parcelable.Creator<Header>() {
        @Override
        public Header createFromParcel(Parcel in) {
            return new Header(in);
        }

        @Override
        public Header[] newArray(int size) {
            return new Header[size];
        }
    };
    private String start_time;
    private String end_time;
    private String match_desc;
    private String toss;
    private String type;
    private String state;
    private String state_title;
    private String status;
    private Integer winning_team_id;

    public Header() {
    }

    public Header(String start_time, String end_time, String match_desc,
                  String toss, String type, String state, String state_title, String status, Integer winning_team_id) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.match_desc = match_desc;
        this.toss = toss;
        this.type = type;
        this.state = state;
        this.state_title = state_title;
        this.status = status;
        this.winning_team_id = winning_team_id;
    }

    protected Header(Parcel in) {
        start_time = in.readString();
        end_time = in.readString();
        match_desc = in.readString();
        toss = in.readString();
        type = in.readString();
        state = in.readString();
        state_title = in.readString();
        status = in.readString();
        winning_team_id = in.readByte() == 0x00 ? null : in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(start_time);
        dest.writeString(end_time);
        dest.writeString(match_desc);
        dest.writeString(toss);
        dest.writeString(type);
        dest.writeString(state);
        dest.writeString(state_title);
        dest.writeString(status);
        if (winning_team_id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(winning_team_id);
        }
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getMatch_desc() {
        return match_desc;
    }

    public String getToss() {
        return toss;
    }

    public String getType() {
        return type;
    }

    public String getState() {
        return state;
    }

    public String getState_title() {
        return state_title;
    }

    public String getStatus() {
        return status;
    }

    public Integer getWinning_team_id() {
        return winning_team_id;
    }

    @Override
    public String toString() {
        return "Header{" +
                "start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", match_desc='" + match_desc + '\'' +
                ", toss='" + toss + '\'' +
                ", type='" + type + '\'' +
                ", state='" + state + '\'' +
                ", state_title='" + state_title + '\'' +
                ", status='" + status + '\'' +
                ", winning_team_id=" + winning_team_id +
                '}';
    }
}
