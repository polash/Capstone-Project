package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

/**
 * Created by sksho on 19-Nov-17.
 */

class Innings {
    private String id;
    private String score;
    private String wkts;
    private String overs;

    public Innings(String id, String score, String wkts, String overs) {
        this.id = id;
        this.score = score;
        this.wkts = wkts;
        this.overs = overs;
    }

    public String getId() {
        return id;
    }

    public String getScore() {
        return score;
    }

    public String getWkts() {
        return wkts;
    }

    public String getOvers() {
        return overs;
    }

    @Override
    public String toString() {
        return "Innings{" +
                "id='" + id + '\'' +
                ", score='" + score + '\'' +
                ", wkts='" + wkts + '\'' +
                ", overs='" + overs + '\'' +
                '}';
    }
}
