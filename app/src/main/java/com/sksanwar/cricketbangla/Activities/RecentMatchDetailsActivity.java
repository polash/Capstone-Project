package com.sksanwar.cricketbangla.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.sksanwar.cricketbangla.R;
import com.sksanwar.cricketbangla.UI.RecentMatchDetailsFragment;

/**
 * Created by sksho on 26-Nov-17.
 */

public class RecentMatchDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_match_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            RecentMatchDetailsFragment detailActivityFragment = new RecentMatchDetailsFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.detail_activity_layout, detailActivityFragment)
                    .commit();

        }
    }
}
