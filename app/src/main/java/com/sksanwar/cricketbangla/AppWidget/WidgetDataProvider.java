package com.sksanwar.cricketbangla.AppWidget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Match;
import com.sksanwar.cricketbangla.R;

import java.util.ArrayList;
import java.util.List;

import static com.sksanwar.cricketbangla.UI.MainActivityFragment.matchesList;

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


    @Override
    public void onCreate() {
        matchList = matchesList;
    }


    @Override
    public void onDataSetChanged() {
        matchList = matchesList;
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
        remoteViews.setTextViewText(android.R.id.text1, matchList.get(i).getTeam1().getName() + " " +
                mContext.getResources().getString(R.string.vs_string) + " " +
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
