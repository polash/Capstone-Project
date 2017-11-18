package com.sksanwar.cricketbangla.Pojo;

/**
 * Created by sksho on 18-Nov-17.
 */

class CricketBanglaLiveMatchTeam1 {

    private String id;
    private String eng_name;
    private String name;
    private String s_name;
    private String flag;


    public CricketBanglaLiveMatchTeam1(String id, String eng_name, String name, String s_name, String flag) {
        this.id = id;
        this.eng_name = eng_name;
        this.name = name;
        this.s_name = s_name;
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEng_name() {
        return eng_name;
    }

    public void setEng_name(String eng_name) {
        this.eng_name = eng_name;
    }

    public String getIname() {
        return name;
    }

    public void setIname(String iname) {
        this.name = iname;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "CricketBanglaLiveMatchBatTeam{" +
                "id='" + id + '\'' +
                ", eng_name='" + eng_name + '\'' +
                ", iname='" + name + '\'' +
                ", s_name='" + s_name + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
