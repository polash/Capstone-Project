
package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

public class Team2 {
    public String id;
    public String eng_name;
    public String name;
    public String s_name;
    public String flag;


    public Team2(String id, String eng_name, String name, String s_name, String flag) {
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

    public String getsName() {
        return s_name;
    }

    public String getFlag() {
        return flag;
    }

    @Override
    public String toString() {
        return "Team2{" +
                "id='" + id + '\'' +
                ", eng_name='" + eng_name + '\'' +
                ", name='" + name + '\'' +
                ", sName='" + s_name + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
