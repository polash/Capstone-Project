package com.sksanwar.cricketbangla.Pojo.LiveMatchDetailsPojo;

/**
 * Created by sksho on 26-Nov-17.
 */

public class Official {
    public Umpire1 umpire1;
    public Umpire2 umpire2;
    public Umpire3 umpire3;
    public Referee referee;

    public Official(Umpire1 umpire1, Umpire2 umpire2, Umpire3 umpire3, Referee referee) {
        this.umpire1 = umpire1;
        this.umpire2 = umpire2;
        this.umpire3 = umpire3;
        this.referee = referee;
    }

    public Umpire1 getUmpire1() {
        return umpire1;
    }

    public Umpire2 getUmpire2() {
        return umpire2;
    }

    public Umpire3 getUmpire3() {
        return umpire3;
    }

    public Referee getReferee() {
        return referee;
    }

    @Override
    public String toString() {
        return "Official{" +
                "umpire1=" + umpire1 +
                ", umpire2=" + umpire2 +
                ", umpire3=" + umpire3 +
                ", referee=" + referee +
                '}';
    }
}
