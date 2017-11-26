package com.sksanwar.cricketbangla.Pojo.LiveMatchDetailsPojo;

/**
 * Created by sksho on 26-Nov-17.
 */

public class Referee {
    private String id;
    private String name;

    public Referee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Referee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
