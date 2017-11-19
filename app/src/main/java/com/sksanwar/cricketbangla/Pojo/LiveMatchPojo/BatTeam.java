package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

import java.util.List;

/**
 * Created by sksho on 19-Nov-17.
 */

class BatTeam {
    private String id;
    private List<Innings> innings;

    public BatTeam(String id, List<Innings> innings) {
        this.id = id;
        this.innings = innings;
    }

    public String getId() {
        return id;
    }

    public List<Innings> getInnings() {
        return innings;
    }

    @Override
    public String toString() {
        return "BatTeam{" +
                "id='" + id + '\'' +
                ", innings=" + innings +
                '}';
    }
}
