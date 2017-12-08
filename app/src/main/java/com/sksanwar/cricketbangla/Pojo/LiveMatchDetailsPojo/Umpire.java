package com.sksanwar.cricketbangla.Pojo.LiveMatchDetailsPojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sksho on 26-Nov-17.
 */

public class Umpire implements Parcelable {
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Umpire> CREATOR = new Parcelable.Creator<Umpire>() {
        @Override
        public Umpire createFromParcel(Parcel in) {
            return new Umpire(in);
        }

        @Override
        public Umpire[] newArray(int size) {
            return new Umpire[size];
        }
    };
    private String id;
    private String name;

    public Umpire() {
    }

    public Umpire(String id, String name) {
        this.id = id;
        this.name = name;
    }

    protected Umpire(Parcel in) {
        id = in.readString();
        name = in.readString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
    }

    @Override
    public String toString() {
        return "Umpire{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
