package com.sksanwar.cricketbangla.JobService;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;


/**
 * Created by sksho on 05-Dec-17.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class LiveMatchRefreshJobService extends JobService {

    private AsyncTask mAsyncTask;

    @Override
    public boolean onStartJob(JobParameters params) {
        mAsyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                Context context
                        = LiveMatchRefreshJobService.this;


                return null;
            }
        };
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
