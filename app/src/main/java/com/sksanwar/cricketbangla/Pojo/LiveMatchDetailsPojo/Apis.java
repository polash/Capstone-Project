package com.sksanwar.cricketbangla.Pojo.LiveMatchDetailsPojo;

/**
 * Created by sksho on 26-Nov-17.
 */

public class Apis {
    public String mini;
    public String scorecard;
    public String commentary;

    public Apis(String mini, String scorecard, String commentary) {
        this.mini = mini;
        this.scorecard = scorecard;
        this.commentary = commentary;
    }

    public String getMini() {
        return mini;
    }

    public String getScorecard() {
        return scorecard;
    }

    public String getCommentary() {
        return commentary;
    }

    @Override
    public String toString() {
        return "Apis{" +
                "mini='" + mini + '\'' +
                ", scorecard='" + scorecard + '\'' +
                ", commentary='" + commentary + '\'' +
                '}';
    }
}
