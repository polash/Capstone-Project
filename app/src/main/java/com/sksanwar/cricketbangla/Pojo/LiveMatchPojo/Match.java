
package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

public class Match {
    private String series_id;
    private String series_name;
    private String match_id;

    private Header header;
    private Venue venue;
    private BatTeam bat_team;
    private BowTeam bow_team;
    private Team1 team1;
    private Team2 team2;

    public Match(String series_id, String series_name, String match_id,
                 Header header, Venue venue, BatTeam bat_team, BowTeam bow_team, Team1 team1, Team2 team2) {
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

    public String getSeries_Id() {
        return series_id;
    }

    public String getSeries_Name() {
        return series_name;
    }

    public String getMatch_Id() {
        return match_id;
    }

    public Header getHeader() {
        return header;
    }

    public Venue getVenue() {
        return venue;
    }

    public BatTeam getBatTeam() {
        return bat_team;
    }

    public BowTeam getBowTeam() {
        return bow_team;
    }

    public Team1 getTeam1() {
        return team1;
    }

    public Team2 getTeam2() {
        return team2;
    }

    @Override
    public String toString() {
        return "Match{" +
                "series_Id='" + series_id + '\'' +
                ", series_Name='" + series_name + '\'' +
                ", match_Id='" + match_id + '\'' +
                ", header=" + header +
                ", venue=" + venue +
                ", batTeam=" + bat_team +
                ", bowTeam=" + bow_team +
                ", team1=" + team1 +
                ", team2=" + team2 +
                '}';
    }
}
