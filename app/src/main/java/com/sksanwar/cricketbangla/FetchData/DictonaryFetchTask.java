package com.sksanwar.cricketbangla.FetchData;

import com.sksanwar.cricketbangla.Pojo.DictonaryPojo;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sksho on 18-Nov-17.
 */

public interface DictonaryFetchTask {

    @GET("/cbzvernacular/vernacular-dictionary/bengali")
    Call<DictonaryPojo> dictonaryForCricket();
}
