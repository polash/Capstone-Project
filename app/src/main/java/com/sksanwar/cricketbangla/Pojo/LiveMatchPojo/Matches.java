package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

/**
 * Created by sksho on 19-Nov-17.
 */

class Matches {
    MatchHeader header;
    Venue venue;
    BatTeam bat_team;
    BowTeam bow_team;
    MatchTeam1 team1;
    MatchTeam2 team2;
    /**
     * These are the pojo object for storing json data
     */
    private String match_id;
    private String series_id;
    private String series_name;

    public Matches(String match_id, String series_id, String series_name,
                   MatchHeader header, Venue venue, BatTeam bat_team,
                   BowTeam bow_team, MatchTeam1 team1, MatchTeam2 team2) {
        this.match_id = match_id;
        this.series_id = series_id;
        this.series_name = series_name;
        this.header = header;
        this.venue = venue;
        this.bat_team = bat_team;
        this.bow_team = bow_team;
        this.team1 = team1;
        this.team2 = team2;
    }

    public String getMatch_id() {
        return match_id;
    }

    public String getSeries_id() {
        return series_id;
    }

    public String getSeries_name() {
        return series_name;
    }

    public MatchHeader getHeader() {
        return header;
    }

    public Venue getVenue() {
        return venue;
    }

    public BatTeam getBat_team() {
        return bat_team;
    }

    public BowTeam getBow_team() {
        return bow_team;
    }

    public MatchTeam1 getTeam1() {
        return team1;
    }

    public MatchTeam2 getTeam2() {
        return team2;
    }

    @Override
    public String toString() {
        return "Matches{" +
                "match_id='" + match_id + '\'' +
                ", series_id='" + series_id + '\'' +
                ", series_name='" + series_name + '\'' +
                ", header=" + header +
                ", venue=" + venue +
                ", bat_team=" + bat_team +
                ", bow_team=" + bow_team +
                ", team1=" + team1 +
                ", team2=" + team2 +
                '}';
    }
}
