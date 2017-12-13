package com.sksanwar.cricketbangla.AppWidget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.sksanwar.cricketbangla.FetchData.JsonFetchTask;
import com.sksanwar.cricketbangla.FetchData.ServiceGenerator;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.LiveMatches;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Match;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sksho on 13-Dec-17.
 */

class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {
    List<Match> matchList = new ArrayList<>();
    Context mContext;
    Intent mIntent;

    public WidgetDataProvider(Context mContext, Intent mIntent) {
        this.mContext = mContext;
        this.mIntent = mIntent;
    }


    private void loadDataFromJson() {
        JsonFetchTask jsonFetchTask = ServiceGenerator.createService(JsonFetchTask.class);
        Call<LiveMatches> liveMatchesCall = jsonFetchTask.liveMatch();
        liveMatchesCall.enqueue(new Callback<LiveMatches>() {
            @Override
            public void onResponse(Call<LiveMatches> call, Response<LiveMatches> response) {
                LiveMatches liveMatches = response.body();
                if (liveMatches != null) {
                    matchList = liveMatches.getMatches();
                }
            }

            @Override
            public void onFailure(Call<LiveMatches> call, Throwable t) {

            }
        });
    }

    @Override
    public void onCreate() {
        loadDataFromJson();
    }


    @Override
    public void onDataSetChanged() {
        loadDataFromJson();
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public int getCount() {
        return matchList.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(),
                android.R.layout.simple_list_item_1);
        remoteViews.setTextViewText(android.R.id.text1, matchList.get(i).getTeam1().getName() + " vs. " +
                matchList.get(i).getTeam2().getName());
        remoteViews.setTextColor(android.R.id.text1, Color.BLACK);
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
