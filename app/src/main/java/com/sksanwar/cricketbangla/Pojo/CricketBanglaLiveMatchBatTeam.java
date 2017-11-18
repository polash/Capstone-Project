package com.sksanwar.cricketbangla.Pojo;

import java.util.ArrayList;

/**
 * Created by sksho on 18-Nov-17.
 */

class CricketBanglaLiveMatchBatTeam {


    private String id;
    private ArrayList<CricketBanglaLiveMatchBowTeamInnings> innings;

    public CricketBanglaLiveMatchBatTeam(String id, ArrayList<CricketBanglaLiveMatchBowTeamInnings> innings) {
        this.id = id;
        this.innings = innings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<CricketBanglaLiveMatchBowTeamInnings> getInnings() {
        return innings;
    }

    public void setInnings(ArrayList<CricketBanglaLiveMatchBowTeamInnings> innings) {
        this.innings = innings;
    }

    @Override
    public String toString() {
        return "CricketBanglaLiveMatchBatTeam{" +
                "id='" + id + '\'' +
                ", innings=" + innings +
                '}';
    }
}
