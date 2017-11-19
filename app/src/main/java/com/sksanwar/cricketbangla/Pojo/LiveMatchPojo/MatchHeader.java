package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

/**
 * Created by sksho on 19-Nov-17.
 */

class MatchHeader {
    private String start_time;
    private String match_desc;
    private String toss;
    private String type;
    private String state;
    private String state_title;
    private String status;

    public MatchHeader(String start_time, String match_desc,
                       String toss, String type, String state,
                       String state_title, String status) {
        this.start_time = start_time;
        this.match_desc = match_desc;
        this.toss = toss;
        this.type = type;
        this.state = state;
        this.state_title = state_title;
        this.status = status;
    }

    public String getStart_time() {
        return start_time;
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

    @Override
    public String toString() {
        return "MatchHeader{" +
                "start_time='" + start_time + '\'' +
                ", match_desc='" + match_desc + '\'' +
                ", toss='" + toss + '\'' +
                ", type='" + type + '\'' +
                ", state='" + state + '\'' +
                ", state_title='" + state_title + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
