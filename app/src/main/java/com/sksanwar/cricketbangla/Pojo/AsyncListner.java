package com.sksanwar.cricketbangla.Pojo;

import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Match;

import java.util.ArrayList;

/**
 * Created by sksho on 20-Nov-17.
 */

public interface AsyncListner {
    void returnLiveMatchList(ArrayList<Match> matchesList);
}
