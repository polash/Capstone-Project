package com.sksanwar.cricketbangla;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.sksanwar.cricketbangla.FetchData.JsonFetchTask;
import com.sksanwar.cricketbangla.FetchData.ServiceGenerator;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.LiveMatches;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.tv_liveMatches);

        /**
         * For dictonary data fetching
         */
        JsonFetchTask jsonFetchTask = ServiceGenerator.createService(JsonFetchTask.class);
        Call<LiveMatches> call = jsonFetchTask.liveMatch();
        /**
         * AsyncTask for Dictonary
         */
        call.enqueue(new Callback<LiveMatches>() {
            @Override
            public void onResponse(Call<LiveMatches> call, Response<LiveMatches> response) {
                LiveMatches dictonaryPojos = response.body();
                Log.d(TAG, "Pojo" + dictonaryPojos);
            }

            @Override
            public void onFailure(Call<LiveMatches> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
