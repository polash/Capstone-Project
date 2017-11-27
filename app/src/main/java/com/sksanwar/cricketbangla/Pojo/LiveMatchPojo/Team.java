package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by sksho on 26-Nov-17.
 */

public class Team implements Parcelable {
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Team> CREATOR = new Parcelable.Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };
    private String id;
    private String eng_name;
    private String name;
    private String s_name;
    private String flag;
    private ArrayList<Integer> squad;
    private ArrayList<Integer> squad_bench;

    public Team(String id, String eng_name, String name,
                String s_name, String flag, ArrayList<Integer> squad, ArrayList<Integer> squad_bench) {
        this.id = id;
        this.eng_name = eng_name;
        this.name = name;
        this.s_name = s_name;
        this.flag = flag;
        this.squad = squad;
        this.squad_bench = squad_bench;
    }

    protected Team(Parcel in) {
        id = in.readString();
        eng_name = in.readString();
        name = in.readString();
        s_name = in.readString();
        flag = in.readString();
        if (in.readByte() == 0x01) {
            squad = new ArrayList<Integer>();
            in.readList(squad, Integer.class.getClassLoader());
        } else {
            squad = null;
        }
        if (in.readByte() == 0x01) {
            squad_bench = new ArrayList<Integer>();
            in.readList(squad_bench, Integer.class.getClassLoader());
        } else {
            squad_bench = null;
        }
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

    public ArrayList<Integer> getSquad() {
        return squad;
    }

    public ArrayList<Integer> getSquad_bench() {
        return squad_bench;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(eng_name);
        dest.writeString(name);
        dest.writeString(s_name);
        dest.writeString(flag);
        if (squad == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(squad);
        }
        if (squad_bench == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(squad_bench);
        }
    }

    @Override
    public String toString() {
        return "Team{" +
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
