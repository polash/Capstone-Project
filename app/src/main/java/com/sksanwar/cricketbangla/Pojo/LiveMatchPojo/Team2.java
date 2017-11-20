
package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

public class Team2 {
    private String id;
    private String engName;
    private String name;
    private String sName;
    private String flag;


    public Team2(String id, String engName, String name, String sName, String flag) {
        this.id = id;
        this.engName = engName;
        this.name = name;
        this.sName = sName;
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public String getEngName() {
        return engName;
    }

    public String getName() {
        return name;
    }

    public String getsName() {
        return sName;
    }

    public String getFlag() {
        return flag;
    }

    @Override
    public String toString() {
        return "Team2{" +
                "id='" + id + '\'' +
                ", engName='" + engName + '\'' +
                ", name='" + name + '\'' +
                ", sName='" + sName + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
