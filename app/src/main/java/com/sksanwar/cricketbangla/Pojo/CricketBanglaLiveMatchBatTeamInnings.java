package com.sksanwar.cricketbangla.Pojo;

/**
 * Created by sksho on 18-Nov-17.
 */

class CricketBanglaLiveMatchBatTeamInnings {


    private String id;
    private String score;
    private String wkts;
    private String overs;

    public CricketBanglaLiveMatchBatTeamInnings(String id, String score, String wkts, String overs) {
        this.id = id;
        this.score = score;
        this.wkts = wkts;
        this.overs = overs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getWkts() {
        return wkts;
    }

    public void setWkts(String wkts) {
        this.wkts = wkts;
    }

    public String getOvers() {
        return overs;
    }

    public void setOvers(String overs) {
        this.overs = overs;
    }

    @Override
    public String toString() {
        return "CricketBanglaLiveMatchBatTeamInnings{" +
                "id='" + id + '\'' +
                ", score='" + score + '\'' +
                ", wkts='" + wkts + '\'' +
                ", overs='" + overs + '\'' +
                '}';
    }
}
