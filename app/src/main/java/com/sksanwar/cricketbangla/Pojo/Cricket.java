package com.sksanwar.cricketbangla.Pojo;

import android.support.v7.widget.RecyclerView;

/**
 * Created by sksho on 14-Nov-17.
 */

public class Cricket extends RecyclerView.ViewHolder {
    public  String uniqueID;
    private String description;
    private String title;

    public Cricket(String uniqueID, String description, String title) {
        this.uniqueID = uniqueID;
        this.description = description;
        this.title = title;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
}
