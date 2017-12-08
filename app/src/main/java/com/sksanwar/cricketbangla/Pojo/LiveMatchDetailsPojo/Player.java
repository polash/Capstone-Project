package com.sksanwar.cricketbangla.Pojo.LiveMatchDetailsPojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sksho on 26-Nov-17.
 */

public class Player implements Parcelable {
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };
    private String id;
    private String f_name;
    private String name;
    private String image;

    public Player() {
    }

    public Player(String id, String f_name, String name, String image) {
        this.id = id;
        this.f_name = f_name;
        this.name = name;
        this.image = image;
    }

    protected Player(Parcel in) {
        id = in.readString();
        f_name = in.readString();
        name = in.readString();
        image = in.readString();
    }

    public String getId() {
        return id;
    }

    public String getF_name() {
        return f_name;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(f_name);
        dest.writeString(name);
        dest.writeString(image);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", f_name='" + f_name + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
