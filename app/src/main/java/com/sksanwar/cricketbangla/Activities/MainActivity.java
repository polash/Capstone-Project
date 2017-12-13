package com.sksanwar.cricketbangla.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.sksanwar.cricketbangla.R;
import com.sksanwar.cricketbangla.UI.MainActivityFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            MainActivityFragment liveMatchFragment = new MainActivityFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.live_match_layout, liveMatchFragment)
                    .commit();
        }
    }

}
