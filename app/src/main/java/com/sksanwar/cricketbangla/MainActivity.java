package com.sksanwar.cricketbangla;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.sksanwar.cricketbangla.FetchData.DictonaryFetchTask;
import com.sksanwar.cricketbangla.Pojo.DictonaryPojo;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private String API_BASE_URL = "http://m.cricbuzz.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.tv_liveMatches);


        /**
         * Making OkHttp client builder
         */
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        /**
         * this is Retrofit Builder
         */
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        /**
         * Building retrofit object
         */
        Retrofit retrofit = builder.client(httpClient.build()).build();
        DictonaryFetchTask dictonaryFetchTask = retrofit.create(DictonaryFetchTask.class);
        Call<DictonaryPojo> call = dictonaryFetchTask.dictonaryForCricket();

        /**
         * AsyncTask
         */
        call.enqueue(new Callback<DictonaryPojo>() {
            @Override
            public void onResponse(Call<DictonaryPojo> call, Response<DictonaryPojo> response) {
                DictonaryPojo dictonaryPojos = response.body();

                textView.setText(dictonaryPojos.getHome());

                Log.d(TAG, "Pojo" + dictonaryPojos);
            }

            @Override
            public void onFailure(Call<DictonaryPojo> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
