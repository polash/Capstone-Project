package com.sksanwar.cricketbangla.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.sksanwar.cricketbangla.FetchData.JsonFetchTask;
import com.sksanwar.cricketbangla.FetchData.ServiceGenerator;
import com.sksanwar.cricketbangla.Pojo.DictonaryPojo;
import com.sksanwar.cricketbangla.R;
import com.sksanwar.cricketbangla.UI.MainActivityFragment;
import com.sksanwar.cricketbangla.UI.RecentMatchActivityFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static DictonaryPojo dictonary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView recentResult = findViewById(R.id.recent_matches_text);


        if (savedInstanceState == null) {
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
                    DictonaryPojo pojo = response.body();
                    dictonary = pojo;
                    recentResult.setText(dictonary.getRecent_results());
                }

                @Override
                public void onFailure(Call<DictonaryPojo> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            });


            FragmentManager fragmentManager = getSupportFragmentManager();
            MainActivityFragment liveMatchFragment = new MainActivityFragment();
            RecentMatchActivityFragment recentMatchActivityFragment = new RecentMatchActivityFragment();

            fragmentManager.beginTransaction()
                    .add(R.id.live_match_layout, liveMatchFragment)
                    .commit();

            fragmentManager.beginTransaction()
                    .add(R.id.recent_match_layout, recentMatchActivityFragment)
                    .commit();
        }
    }
}
