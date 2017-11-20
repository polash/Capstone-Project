
package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

public class Header {

    private String startTime;
    private String endTime;
    private String matchDesc;
    private String toss;
    private String type;
    private String state;
    private String stateTitle;
    private String status;
    private Integer winningTeamId;
    private Integer dn;

    public Header(String startTime, String endTime, String matchDesc,
                  String toss, String type, String state, String stateTitle,
                  String status, Integer winningTeamId, Integer dn)

    {
        this.startTime = startTime;
        this.endTime = endTime;
        this.matchDesc = matchDesc;
        this.toss = toss;
        this.type = type;
        this.state = state;
        this.stateTitle = stateTitle;
        this.status = status;
        this.winningTeamId = winningTeamId;
        this.dn = dn;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getMatchDesc() {
        return matchDesc;
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

    public String getStateTitle() {
        return stateTitle;
    }

    public String getStatus() {
        return status;
    }

    public Integer getWinningTeamId() {
        return winningTeamId;
    }

    public Integer getDn() {
        return dn;
    }

    @Override
    public String toString() {
        return "Header{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", matchDesc='" + matchDesc + '\'' +
                ", toss='" + toss + '\'' +
                ", type='" + type + '\'' +
                ", state='" + state + '\'' +
                ", stateTitle='" + stateTitle + '\'' +
                ", status='" + status + '\'' +
                ", winningTeamId=" + winningTeamId +
                ", dn=" + dn +
                '}';
    }
}
