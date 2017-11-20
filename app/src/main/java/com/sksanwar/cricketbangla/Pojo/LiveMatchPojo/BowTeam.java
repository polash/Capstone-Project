
package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

import java.util.List;

public class BowTeam {

    private String id;
    private List<Inning> innings = null;


    public BowTeam(String id, List<Inning> innings) {
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
        return "BowTeam{" +
                "id='" + id + '\'' +
                ", innings=" + innings +
                '}';
    }
}
