
package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

import java.util.List;

public class BatTeam {
    private String id;
    private List<Inning> innings = null;

    public BatTeam(String id, List<Inning> innings) {
        this.id = id;
        this.innings = innings;
    }

    public String getId() {
        return id;
    }

    public List<Inning> getInnings() {
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