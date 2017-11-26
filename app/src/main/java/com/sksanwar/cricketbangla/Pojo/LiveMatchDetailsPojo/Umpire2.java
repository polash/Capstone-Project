package com.sksanwar.cricketbangla.Pojo.LiveMatchDetailsPojo;

/**
 * Created by sksho on 26-Nov-17.
 */

public class Umpire2 {
    private String id;
    private String name;

    public Umpire2(String id, String name) {
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
        return "Umpire2{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
