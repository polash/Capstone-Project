package com.sksanwar.cricketbangla.FetchData;

import com.sksanwar.cricketbangla.Pojo.DictonaryPojo;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.LiveMatches;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sksho on 18-Nov-17.
 */

public interface JsonFetchTask {

    @GET("cbzvernacular/vernacular-dictionary/bengali")
    Call<DictonaryPojo> dictonaryForCricket();

    @GET("cbzvernacular/bengali/match/livematches")
    Call<LiveMatches> liveMatch();
}
