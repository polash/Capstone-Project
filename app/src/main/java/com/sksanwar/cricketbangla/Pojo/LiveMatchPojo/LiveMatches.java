package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

import java.util.List;

/**
 * Created by sksho on 18-Nov-17.
 */

public class LiveMatches {

    private List<Matches> matches;

    public LiveMatches(List<Matches> matches) {
        this.matches = matches;
    }

    public List<Matches> getMatches() {
        return matches;
    }

}
