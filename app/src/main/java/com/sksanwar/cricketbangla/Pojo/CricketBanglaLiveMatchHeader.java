package com.sksanwar.cricketbangla.Pojo;

/**
 * Created by sksho on 18-Nov-17.
 */

class CricketBanglaLiveMatchHeader {

    private String start_time;
    private String match_desc;
    private String toss;
    private String type;
    private String state;
    private String state_title;
    private String status;

    public CricketBanglaLiveMatchHeader(String start_time, String match_desc, String toss, String type, String state, String state_title, String status) {
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

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getMatch_desc() {
        return match_desc;
    }

    public void setMatch_desc(String match_desc) {
        this.match_desc = match_desc;
    }

    public String getToss() {
        return toss;
    }

    public void setToss(String toss) {
        this.toss = toss;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState_title() {
        return state_title;
    }

    public void setState_title(String state_title) {
        this.state_title = state_title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CricketBanglaLiveMatchHeader{" +
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
