
package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Match implements Parcelable {

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Match> CREATOR = new Parcelable.Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };
    private String series_id;
    private String series_name;
    private String match_id;
    private Header header;
    private Venue venue;
    private BatBowTeam bat_team;
    private BatBowTeam bow_team;
    private Team team1;
    private Team team2;

    public Match(String series_id, String series_name, String match_id,
                 Header header, Venue venue, BatBowTeam bat_team, BatBowTeam bow_team, Team team1, Team team2) {
        this.series_id = series_id;
        this.series_name = series_name;
        this.match_id = match_id;
        this.header = header;
        this.venue = venue;
        this.bat_team = bat_team;
        this.bow_team = bow_team;
        this.team1 = team1;
        this.team2 = team2;
    }

    protected Match(Parcel in) {
        series_id = in.readString();
        series_name = in.readString();
        match_id = in.readString();
        header = (Header) in.readValue(Header.class.getClassLoader());
        venue = (Venue) in.readValue(Venue.class.getClassLoader());
        bat_team = (BatBowTeam) in.readValue(BatBowTeam.class.getClassLoader());
        bow_team = (BatBowTeam) in.readValue(BatBowTeam.class.getClassLoader());
        team1 = (Team) in.readValue(Team.class.getClassLoader());
        team2 = (Team) in.readValue(Team.class.getClassLoader());
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

    public BatBowTeam getBat_team() {
        return bat_team;
    }

    public BatBowTeam getBow_team() {
        return bow_team;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(series_id);
        dest.writeString(series_name);
        dest.writeString(match_id);
        dest.writeValue(header);
        dest.writeValue(venue);
        dest.writeValue(bat_team);
        dest.writeValue(bow_team);
        dest.writeValue(team1);
        dest.writeValue(team2);
    }

    @Override
    public String toString() {
        return "Match{" +
                "series_id='" + series_id + '\'' +
                ", series_name='" + series_name + '\'' +
                ", match_id='" + match_id + '\'' +
                ", header=" + header +
                ", venue=" + venue +
                ", bat_team=" + bat_team +
                ", bow_team=" + bow_team +
                ", team1=" + team1 +
                ", team2=" + team2 +
                '}';
    }
}
