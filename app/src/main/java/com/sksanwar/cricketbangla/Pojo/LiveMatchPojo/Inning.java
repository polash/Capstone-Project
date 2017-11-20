
package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

public class Inning {

    private String id;
    private String score;
    private String wkts;
    private String overs;

    public Inning(String id, String score, String wkts, String overs) {
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
        return "Inning{" +
                "id='" + id + '\'' +
                ", score='" + score + '\'' +
                ", wkts='" + wkts + '\'' +
                ", overs='" + overs + '\'' +
                '}';
    }
}