package com.sksanwar.cricketbangla.Pojo.LiveMatchDetailsPojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Header;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Team;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Venue;

import java.util.ArrayList;

/**
 * Created by sksho on 26-Nov-17.
 */

public class LiveMatchDetails implements Parcelable {
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<LiveMatchDetails> CREATOR = new Parcelable.Creator<LiveMatchDetails>() {
        @Override
        public LiveMatchDetails createFromParcel(Parcel in) {
            return new LiveMatchDetails(in);
        }

        @Override
        public LiveMatchDetails[] newArray(int size) {
            return new LiveMatchDetails[size];
        }
    };
    private Official official;
    private ArrayList<Object> audio;
    private String alerts;
    private Team team1;
    private Team team2;
    private ArrayList<Player> players;
    private Apis apis;
    private String series_id;
    private String series_name;
    private String match_id;
    private Header header;
    private Venue venue;

    public LiveMatchDetails(Official official, ArrayList<Object> audio, String alerts, Team team1, Team team2,
                            ArrayList<Player> players, Apis apis, String series_id,
                            String series_name, String match_id, Header header, Venue venue) {
        this.official = official;
        this.audio = audio;
        this.alerts = alerts;
        this.team1 = team1;
        this.team2 = team2;
        this.players = players;
        this.apis = apis;
        this.series_id = series_id;
        this.series_name = series_name;
        this.match_id = match_id;
        this.header = header;
        this.venue = venue;
    }

    protected LiveMatchDetails(Parcel in) {
        official = (Official) in.readValue(Official.class.getClassLoader());
        if (in.readByte() == 0x01) {
            audio = new ArrayList<Object>();
            in.readList(audio, Object.class.getClassLoader());
        } else {
            audio = null;
        }
        alerts = in.readString();
        team1 = (Team) in.readValue(Team.class.getClassLoader());
        team2 = (Team) in.readValue(Team.class.getClassLoader());
        if (in.readByte() == 0x01) {
            players = new ArrayList<Player>();
            in.readList(players, Player.class.getClassLoader());
        } else {
            players = null;
        }
        apis = (Apis) in.readValue(Apis.class.getClassLoader());
        series_id = in.readString();
        series_name = in.readString();
        match_id = in.readString();
        header = (Header) in.readValue(Header.class.getClassLoader());
        venue = (Venue) in.readValue(Venue.class.getClassLoader());
    }

    public Official getOfficial() {
        return official;
    }

    public ArrayList<Object> getAudio() {
        return audio;
    }

    public String getAlerts() {
        return alerts;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Apis getApis() {
        return apis;
    }

    public String getSeries_id() {
        return series_id;
    }

    public String getSeries_name() {
        return series_name;
    }

    public String getMatch_id() {
        return match_id;
    }

    public Header getHeader() {
        return header;
    }

    public Venue getVenue() {
        return venue;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(official);
        if (audio == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(audio);
        }
        dest.writeString(alerts);
        dest.writeValue(team1);
        dest.writeValue(team2);
        if (players == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(players);
        }
        dest.writeValue(apis);
        dest.writeString(series_id);
        dest.writeString(series_name);
        dest.writeString(match_id);
        dest.writeValue(header);
        dest.writeValue(venue);
    }

    @Override
    public String toString() {
        return "LiveMatchDetails{" +
                "official=" + official +
                ", audio=" + audio +
                ", alerts='" + alerts + '\'' +
                ", team1=" + team1 +
                ", team2=" + team2 +
                ", players=" + players +
                ", apis=" + apis +
                ", series_id='" + series_id + '\'' +
                ", series_name='" + series_name + '\'' +
                ", match_id='" + match_id + '\'' +
                ", header=" + header +
                ", venue=" + venue +
                '}';
    }
}
