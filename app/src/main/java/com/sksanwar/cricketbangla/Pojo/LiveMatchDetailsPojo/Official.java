package com.sksanwar.cricketbangla.Pojo.LiveMatchDetailsPojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sksho on 26-Nov-17.
 */

public class Official implements Parcelable {
    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Official> CREATOR = new Parcelable.Creator<Official>() {
        @Override
        public Official createFromParcel(Parcel in) {
            return new Official(in);
        }

        @Override
        public Official[] newArray(int size) {
            return new Official[size];
        }
    };
    private Umpire umpire1;
    private Umpire umpire2;
    private Umpire umpire3;
    private Umpire referee;

    public Official(Umpire umpire1, Umpire umpire2, Umpire umpire3, Umpire referee) {
        this.umpire1 = umpire1;
        this.umpire2 = umpire2;
        this.umpire3 = umpire3;
        this.referee = referee;
    }

    protected Official(Parcel in) {
        umpire1 = (Umpire) in.readValue(Umpire.class.getClassLoader());
        umpire2 = (Umpire) in.readValue(Umpire.class.getClassLoader());
        umpire3 = (Umpire) in.readValue(Umpire.class.getClassLoader());
        referee = (Umpire) in.readValue(Umpire.class.getClassLoader());
    }

    public Umpire getUmpire() {
        return umpire1;
    }

    public Umpire getUmpire2() {
        return umpire2;
    }

    public Umpire getUmpire3() {
        return umpire3;
    }

    public Umpire getReferee() {
        return referee;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(umpire1);
        dest.writeValue(umpire2);
        dest.writeValue(umpire3);
        dest.writeValue(referee);
    }

    @Override
    public String toString() {
        return "Official{" +
                "umpire1=" + umpire1 +
                ", umpire2=" + umpire2 +
                ", umpire3=" + umpire3 +
                ", referee=" + referee +
                '}';
    }
}
