package com.sksanwar.cricketbangla.AppWidget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by sksho on 13-Dec-17.
 */

public class WidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetDataProvider(this, intent);
    }
}
