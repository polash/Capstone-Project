
package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

public class Header {

    private String start_time;
    private String end_time;
    private String match_desc;
    private String toss;
    private String type;
    private String state;
    private String state_title;
    private String status;
    private Integer winning_team_id;

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
