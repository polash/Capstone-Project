package com.sksanwar.cricketbangla.Pojo.LiveMatchDetailsPojo;

import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Header;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Team1;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Team2;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Url;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Venue;

import java.util.List;

/**
 * Created by sksho on 26-Nov-17.
 */

public class LiveMatchDetails {

    public Official official;
    public List<Object> audio;
    public String alerts;
    public Team1 team1;
    public Team2 team2;
    public List<Player> players;
    public Apis apis;
    public Url url;
    private String series_id;
    private String series_name;
    private String match_id;
    private Header header;
    private Venue venue;

    public LiveMatchDetails(String series_id, String series_name, String match_id, Header header, Venue venue,
                            Official official, List<Object> audio, String alerts, Team1 team1, Team2 team2, List<Player> players, Apis apis, Url url) {
        this.series_id = series_id;
        this.series_name = series_name;
        this.match_id = match_id;
        this.header = header;
        this.venue = venue;
        this.official = official;
        this.audio = audio;
        this.alerts = alerts;
        this.team1 = team1;
        this.team2 = team2;
        this.players = players;
        this.apis = apis;
        this.url = url;
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

    public Official getOfficial() {
        return official;
    }

    public List<Object> getAudio() {
        return audio;
    }

    public String getAlerts() {
        return alerts;
    }

    public Team1 getTeam1() {
        return team1;
    }

    public Team2 getTeam2() {
        return team2;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Apis getApis() {
        return apis;
    }

    public Url getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "LiveMatchDetails{" +
                "series_id='" + series_id + '\'' +
                ", series_name='" + series_name + '\'' +
                ", match_id='" + match_id + '\'' +
                ", header=" + header +
                ", venue=" + venue +
                ", official=" + official +
                ", audio=" + audio +
                ", alerts='" + alerts + '\'' +
                ", team1=" + team1 +
                ", team2=" + team2 +
                ", players=" + players +
                ", apis=" + apis +
                ", url=" + url +
                '}';
    }
}
