package com.sksanwar.cricketbangla.FetchData;

import com.sksanwar.cricketbangla.Pojo.DictonaryPojo;
import com.sksanwar.cricketbangla.Pojo.LiveMatchDetailsPojo.LiveMatchDetails;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.LiveMatches;
import com.sksanwar.cricketbangla.Pojo.RecentMatchPojo.RecentMatchDetails;
import com.sksanwar.cricketbangla.Pojo.RecentMatchPojo.RecentMatches;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by sksho on 18-Nov-17.
 */

public interface JsonFetchTask {

    @GET("cbzvernacular/vernacular-dictionary/bengali/")
    Call<DictonaryPojo> dictonaryForCricket();

    @GET("cbzvernacular/bengali/match/livematches/")
    Call<LiveMatches> liveMatch();

    @GET("cbzvernacular/bengali/match/recent/")
    Call<RecentMatches> recentMatches();

    @GET("cbzvernacular/bengali/match/{match_id}/")
    Call<LiveMatchDetails> liveMatchDetails(
            @Path("match_id") String match_id
    );

    @GET("cbzvernacular/bengali/match/recent/{match_id}/")
    Call<RecentMatchDetails> recentMatchDetails(
            @Path("match_id") String match_id
    );

}
