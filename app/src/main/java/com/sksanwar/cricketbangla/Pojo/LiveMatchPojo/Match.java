
package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

public class Match {
    private String matchId;
    private String seriesId;
    private String seriesName;
    private Header header;
    private Venue venue;
    private BatTeam batTeam;
    private BowTeam bowTeam;
    private Team1 team1;
    private Team2 team2;

    public Match(String matchId, String seriesId, String seriesName,
                 Header header, Venue venue, BatTeam batTeam,
                 BowTeam bowTeam, Team1 team1, Team2 team2)

    {
        this.matchId = matchId;
        this.seriesId = seriesId;
        this.seriesName = seriesName;
        this.header = header;
        this.venue = venue;
        this.batTeam = batTeam;
        this.bowTeam = bowTeam;
        this.team1 = team1;
        this.team2 = team2;
    }

    public String getMatchId() {
        return matchId;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public Header getHeader() {
        return header;
    }

    public Venue getVenue() {
        return venue;
    }

    public BatTeam getBatTeam() {
        return batTeam;
    }

    public BowTeam getBowTeam() {
        return bowTeam;
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
                "matchId='" + matchId + '\'' +
                ", seriesId='" + seriesId + '\'' +
                ", seriesName='" + seriesName + '\'' +
                ", header=" + header +
                ", venue=" + venue +
                ", batTeam=" + batTeam +
                ", bowTeam=" + bowTeam +
                ", team1=" + team1 +
                ", team2=" + team2 +
                '}';
    }
}
