
package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class BatTeam implements Parcelable {
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<BatTeam> CREATOR = new Parcelable.Creator<BatTeam>() {
        @Override
        public BatTeam createFromParcel(Parcel in) {
            return new BatTeam(in);
        }

        @Override
        public BatTeam[] newArray(int size) {
            return new BatTeam[size];
        }
    };
    public String id;
    public List<Inning> innings;

    public BatTeam(String id, List<Inning> innings) {
        this.id = id;
        this.innings = innings;
    }

    protected BatTeam(Parcel in) {
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
        return "BatTeam{" +
                "id='" + id + '\'' +
                ", innings=" + innings +
                '}';
    }
}
