
package com.sksanwar.cricketbangla.Pojo.LiveMatchPojo;

public class Url {

    private String match;

    public Url(String match) {
        this.match = match;
    }

    public String getMatch() {
        return match;
    }

    @Override
    public String toString() {
        return "Url{" +
                "match='" + match + '\'' +
                '}';
    }
}
