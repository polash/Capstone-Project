package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

/**
 * Created by sksho on 19-Nov-17.
 */

class MatchTeam2 {
    private String id;
    private String eng_name;
    private String name;
    private String s_name;
    private String flag;

    public MatchTeam2(String id, String eng_name, String name, String s_name, String flag) {
        this.id = id;
        this.eng_name = eng_name;
        this.name = name;
        this.s_name = s_name;
        this.flag = flag;
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

    @Override
    public String toString() {
        return "MatchTeam2{" +
                "id='" + id + '\'' +
                ", eng_name='" + eng_name + '\'' +
                ", name='" + name + '\'' +
                ", s_name='" + s_name + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
