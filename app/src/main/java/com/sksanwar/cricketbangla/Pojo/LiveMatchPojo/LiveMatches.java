
package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

import java.util.List;

public class LiveMatches {
    public Url url;
    public List<Match> matches;

    public LiveMatches(Url url, List<Match> matches) {
        this.url = url;
        this.matches = matches;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public String toString() {
        return "LiveMatches{" +
                "url=" + url +
                ", matches=" + matches +
                '}';
    }
}
