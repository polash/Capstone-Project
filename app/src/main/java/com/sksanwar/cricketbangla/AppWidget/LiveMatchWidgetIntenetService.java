package com.sksanwar.cricketbangla.AppWidget;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import com.sksanwar.cricketbangla.R;

/**
 * Created by sksho on 13-Dec-17.
 */

public class LiveMatchWidgetIntenetService extends IntentService {

    public LiveMatchWidgetIntenetService() {
        super(LiveMatchWidgetIntenetService.class.getName());
    }

    /**
     * Sets the remote adapter used to fill in the list items
     *
     * @param views RemoteViews to set the RemoteAdapter
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private static void setRemoteAdapter(Context context, @NonNull final RemoteViews views) {
        views.setRemoteAdapter(R.id.widget_list,
                new Intent(context, WidgetService.class));
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this,
                LiveMatchWidgetProvider.class));

        for (int appWidgetId : appWidgetIds) {
            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(this.getPackageName(), R.layout.live_match_widget);
            // Set up the collection
                setRemoteAdapter(this, views);

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_list);
        }

    }
}
