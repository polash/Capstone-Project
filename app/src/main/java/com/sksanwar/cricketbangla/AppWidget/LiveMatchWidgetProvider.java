package com.sksanwar.cricketbangla.AppWidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.sksanwar.cricketbangla.UI.MainActivityFragment;

/**
 * Implementation of App Widget functionality.
 */
public class LiveMatchWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        context.startService(new Intent(context, LiveMatchWidgetIntenetService.class));
    }


    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        context.startService(new Intent(context, LiveMatchWidgetIntenetService.class));
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (MainActivityFragment.ACTION_DATA_UPDATED.equals(intent.getAction())) {
            context.startService(new Intent(context, LiveMatchWidgetIntenetService.class));
        }
    }
}

