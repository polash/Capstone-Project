package com.sksanwar.cricketbangla.Pojo;

import java.util.ArrayList;

/**
 * Created by sksho on 18-Nov-17.
 */

class CricketBanglaLiveMatchBowTeam {

    private String id;
    private ArrayList<CricketBanglaLiveMatchBatTeamInnings> innings;

    public CricketBanglaLiveMatchBowTeam(String id, ArrayList<CricketBanglaLiveMatchBatTeamInnings> innings) {
        this.id = id;
        this.innings = innings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<CricketBanglaLiveMatchBatTeamInnings> getInnings() {
        return innings;
    }

    public void setInnings(ArrayList<CricketBanglaLiveMatchBatTeamInnings> innings) {
        this.innings = innings;
    }

    @Override
    public String toString() {
        return "CricketBanglaLiveMatchBowTeam{" +
                "id='" + id + '\'' +
                ", innings=" + innings +
                '}';
    }
}
