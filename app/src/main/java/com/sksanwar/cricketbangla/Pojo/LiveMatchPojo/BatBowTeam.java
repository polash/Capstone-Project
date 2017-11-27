
package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class BatBowTeam implements Parcelable {
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<BatBowTeam> CREATOR = new Parcelable.Creator<BatBowTeam>() {
        @Override
        public BatBowTeam createFromParcel(Parcel in) {
            return new BatBowTeam(in);
        }

        @Override
        public BatBowTeam[] newArray(int size) {
            return new BatBowTeam[size];
        }
    };

    private String id;
    private List<Inning> innings;

    public BatBowTeam(String id, List<Inning> innings) {
        this.id = id;
        this.innings = innings;
    }

    protected BatBowTeam(Parcel in) {
        id = in.readString();
        if (in.readByte() == 0x01) {
            innings = new ArrayList<Inning>();
            in.readList(innings, Inning.class.getClassLoader());
        } else {
            innings = null;
        }
    }

    public String getId() {
        return id;
    }

    public List<Inning> getInnings() {
        return innings;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        if (innings == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(innings);
        }
    }

    @Override
    public String toString() {
        return "BatBowTeam{" +
                "id='" + id + '\'' +
                ", innings=" + innings +
                '}';
    }
}
