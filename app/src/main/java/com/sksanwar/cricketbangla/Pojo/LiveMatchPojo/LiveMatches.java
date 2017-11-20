
package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

import java.util.List;

public class LiveMatches {
    private Url url;
    private List<Match> matches = null;

    public LiveMatches(Url url, List<Match> matches) {
        this.url = url;
        this.matches = matches;
    }

    public Url getUrl() {
        return url;
    }

    public List<Match> getMatches() {
        return matches;
    }

    @Override
    public String toString() {
        return "LiveMatches{" +
                "url=" + url +
                ", matches=" + matches +
                '}';
    }
}
