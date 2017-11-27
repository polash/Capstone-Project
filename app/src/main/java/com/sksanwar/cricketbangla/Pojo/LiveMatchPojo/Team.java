
package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

import android.os.Parcel;
import android.os.Parcelable;

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
    public String id;
    public String eng_name;
    public String name;
    public String s_name;
    public String flag;

    public Team(String id, String eng_name, String name, String s_name, String flag) {
        this.id = id;
        this.eng_name = eng_name;
        this.name = name;
        this.s_name = s_name;
        this.flag = flag;
    }

    protected Team(Parcel in) {
        id = in.readString();
        eng_name = in.readString();
        name = in.readString();
        s_name = in.readString();
        flag = in.readString();
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
    }

    @Override
    public String toString() {
        return "Team{" +
                "id='" + id + '\'' +
                ", eng_name='" + eng_name + '\'' +
                ", name='" + name + '\'' +
                ", s_name='" + s_name + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
