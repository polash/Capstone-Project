package com.sksanwar.cricketbangla.JobService;


import android.os.AsyncTask;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.sksanwar.cricketbangla.FetchData.JsonFetchTask;
import com.sksanwar.cricketbangla.FetchData.ServiceGenerator;
import com.sksanwar.cricketbangla.Pojo.DictonaryPojo;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.LiveMatches;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Match;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by sksho on 05-Dec-17.
 */


public class LiveMatchRefreshJobService extends JobService {

    public static DictonaryPojo dictonary;
    public static ArrayList<Match> matchesList;

    private AsyncTask mAsyncTask;

    @Override
    public boolean onStartJob(JobParameters params) {
        /**
         * For dictonary data fetching
         */
        JsonFetchTask jsonFetchTask = ServiceGenerator.createService(JsonFetchTask.class);
        Call<DictonaryPojo> call = jsonFetchTask.dictonaryForCricket();
        /**
         * AsyncTask for Dictonary
         */
        call.enqueue(new Callback<DictonaryPojo>() {
            @Override
            public void onResponse(Call<DictonaryPojo> call, Response<DictonaryPojo> response) {
                DictonaryPojo dict = response.body();
                if (dict != null) {
                    dictonary = (dict);
                }
            }

            @Override
            public void onFailure(Call<DictonaryPojo> call, Throwable t) {

            }
        });


        /**
         * For Live Match Data Fetching
         */
        Call<LiveMatches> liveMatchesCall = jsonFetchTask.liveMatch();
        liveMatchesCall.enqueue(new Callback<LiveMatches>() {
            @Override
            public void onResponse(Call<LiveMatches> call, Response<LiveMatches> response) {
                LiveMatches liveMatches = response.body();
                if (liveMatches != null) {
                    matchesList = liveMatches.getMatches();
                }
            }

            @Override
            public void onFailure(Call<LiveMatches> call, Throwable t) {

            }
        });
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
