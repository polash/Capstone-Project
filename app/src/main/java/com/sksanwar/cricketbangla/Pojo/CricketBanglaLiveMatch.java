package com.sksanwar.cricketbangla.Pojo;

import java.util.ArrayList;

/**
 * Created by sksho on 18-Nov-17.
 */

public class CricketBanglaLiveMatch {

    /**
     * These are the pojo object for storing json data
     */
    private String match_id;
    private String series_id;
    private String series_name;
    private ArrayList<CricketBanglaLiveMatchHeader> header;
    private ArrayList<CricketBanglaLiveMatchVenue> venue;
    private ArrayList<CricketBanglaLiveMatchBatTeam> bat_team;
    private ArrayList<CricketBanglaLiveMatchBowTeam> bow_team;
    private ArrayList<CricketBanglaLiveMatchTeam1> team1;
    private ArrayList<CricketBanglaLiveMatchTeam2> team2;

    /**
     * Default Constructor
     *
     * @param matchID    this match id is for the live match id
     * @param seriesID   this series id is for live match series id
     * @param seriesName this is for the live match series Name
     * @param header     header that contain details in array
     * @param venue      venue array contains venu information
     * @param batTeam    bating team
     * @param bowTeam    bowling team
     * @param team1      team 1 details
     * @param team2      team 2 details
     */
    public CricketBanglaLiveMatch(String matchID, String seriesID,
                                  String seriesName,
                                  ArrayList<CricketBanglaLiveMatchHeader> header,
                                  ArrayList<CricketBanglaLiveMatchVenue> venue,
                                  ArrayList<CricketBanglaLiveMatchBatTeam> batTeam,
                                  ArrayList<CricketBanglaLiveMatchBowTeam> bowTeam,
                                  ArrayList<CricketBanglaLiveMatchTeam1> team1,
                                  ArrayList<CricketBanglaLiveMatchTeam2> team2) {
        this.match_id = matchID;
        this.series_id = seriesID;
        this.series_name = seriesName;
        this.header = header;
        this.venue = venue;
        this.bat_team = batTeam;
        this.bow_team = bowTeam;
        this.team1 = team1;
        this.team2 = team2;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getSeries_id() {
        return series_id;
    }

    public void setSeries_id(String series_id) {
        this.series_id = series_id;
    }

    public String getSeries_name() {
        return series_name;
    }

    public void setSeries_name(String series_name) {
        this.series_name = series_name;
    }

    public ArrayList<CricketBanglaLiveMatchHeader> getHeader() {
        return header;
    }

    public void setHeader(ArrayList<CricketBanglaLiveMatchHeader> header) {
        this.header = header;
    }

    public ArrayList<CricketBanglaLiveMatchVenue> getVenue() {
        return venue;
    }

    public void setVenue(ArrayList<CricketBanglaLiveMatchVenue> venue) {
        this.venue = venue;
    }

    public ArrayList<CricketBanglaLiveMatchBatTeam> getBat_team() {
        return bat_team;
    }

    public void setBat_team(ArrayList<CricketBanglaLiveMatchBatTeam> bat_team) {
        this.bat_team = bat_team;
    }

    public ArrayList<CricketBanglaLiveMatchBowTeam> getBow_team() {
        return bow_team;
    }

    public void setBow_team(ArrayList<CricketBanglaLiveMatchBowTeam> bow_team) {
        this.bow_team = bow_team;
    }

    public ArrayList<CricketBanglaLiveMatchTeam1> getTeam1() {
        return team1;
    }

    public void setTeam1(ArrayList<CricketBanglaLiveMatchTeam1> team1) {
        this.team1 = team1;
    }

    public ArrayList<CricketBanglaLiveMatchTeam2> getTeam2() {
        return team2;
    }

    public void setTeam2(ArrayList<CricketBanglaLiveMatchTeam2> team2) {
        this.team2 = team2;
    }

    @Override
    public String toString() {
        return "CricketBanglaLiveMatch{" +
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
