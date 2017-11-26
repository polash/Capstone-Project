package com.sksanwar.cricketbangla.Pojo.LiveMatchDetailsPojo;

import java.util.List;

/**
 * Created by sksho on 26-Nov-17.
 */

public class Team1 {
    private String id;
    private String eng_name;
    private String name;
    private String s_name;
    private String flag;
    private List<Integer> squad;
    private List<Integer> squad_bench;

    public Team1(String id, String eng_name, String name, String s_name, String flag, List<Integer> squad, List<Integer> squad_bench) {
        this.id = id;
        this.eng_name = eng_name;
        this.name = name;
        this.s_name = s_name;
        this.flag = flag;
        this.squad = squad;
        this.squad_bench = squad_bench;
    }

    public String getId() {
        return id;
    }

    public String getEng_name() {
        return eng_name;
    }

    public String getName() {
        return name;
    }

    public String getS_name() {
        return s_name;
    }

    public String getFlag() {
        return flag;
    }

    public List<Integer> getSquad() {
        return squad;
    }

    public List<Integer> getSquad_bench() {
        return squad_bench;
    }

    @Override
    public String toString() {
        return "Team1{" +
                "id='" + id + '\'' +
                ", eng_name='" + eng_name + '\'' +
                ", name='" + name + '\'' +
                ", s_name='" + s_name + '\'' +
                ", flag='" + flag + '\'' +
                ", squad=" + squad +
                ", squad_bench=" + squad_bench +
                '}';
    }
}
