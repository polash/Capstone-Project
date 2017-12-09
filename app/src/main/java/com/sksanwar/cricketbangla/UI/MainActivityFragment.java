package com.sksanwar.cricketbangla.UI;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sksanwar.cricketbangla.Activities.LiveMatchDetailsActivity;
import com.sksanwar.cricketbangla.Adapters.AdapterLiveMatches;
import com.sksanwar.cricketbangla.FetchData.JsonFetchTask;
import com.sksanwar.cricketbangla.FetchData.ServiceGenerator;
import com.sksanwar.cricketbangla.Pojo.DictonaryPojo;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.LiveMatches;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Match;
import com.sksanwar.cricketbangla.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by sksho on 20-Nov-17.
 */

public class MainActivityFragment extends Fragment implements
        AdapterLiveMatches.ListItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    public static final String LIVE_MATCH_LIST = "live_match_list";
    public static final String POSITION = "position";
    public static final String DICTONARPOJO = "dictonary";

    private static final String TAG = MainActivityFragment.class.getSimpleName();
    public ArrayList<Match> matchesList;
    public DictonaryPojo dictonary;

    @BindView(R.id.rv_livematches)
    RecyclerView recyclerViewLiveMatches;
    @BindView(R.id.swip_to_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.no_network)
    TextView no_network;
    Handler handler = new Handler();
    int delay = 35000;//15 seconds
    Runnable runnable;
    private AdapterLiveMatches adapterLiveMatches;

    //Firebase references
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDictonaryDatabaseReference;
    private DatabaseReference mMatchListDatabaseReference;


    public MainActivityFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mainactivity_fragment, container, false);
        ButterKnife.bind(this, rootView);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseDatabase.setPersistenceEnabled(true);

        mDictonaryDatabaseReference = mFirebaseDatabase.getReference().child("Dictonary");
        mDictonaryDatabaseReference.keepSynced(true);
        mMatchListDatabaseReference = mFirebaseDatabase.getReference().child("MatchList");
        mMatchListDatabaseReference.keepSynced(true);


        no_network.setVisibility(View.GONE);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setRefreshing(true);

        networkCheck();
        liveMatchDownloadFromJson();
        loadDataFromFirebaseDB();

        return rootView;
    }

    private void loadDataFromFirebaseDB() {
        //Dictonary database from firebase real-time database
        mDictonaryDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    DictonaryPojo dictonaryPojo = dataSnapshot1.getValue(DictonaryPojo.class);
                    if (dictonaryPojo != null) {
                        dictonary = dictonaryPojo;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //matchlist data base from firebase real-time db;
        mMatchListDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    LiveMatches liveMatches = dataSnapshot1.getValue(LiveMatches.class);
                    matchesList = liveMatches.getMatches();
                    loadViews(matchesList);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //do nothing
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(LIVE_MATCH_LIST, matchesList);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(LIVE_MATCH_LIST)) {
                matchesList = savedInstanceState.getParcelableArrayList(LIVE_MATCH_LIST);
            }
        }
        if (matchesList != null) {
            loadViews(matchesList);
        } else {
            liveMatchDownloadFromJson();
        }
    }

    //Network Checks
    private boolean networkCheck() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    /**
     * Continue to run on UI thread
     * <p>
     * https://stackoverflow.com/questions/11434056/how-to-run-a-method-every-x-seconds
     */

    @Override
    public void onResume() {
        handler.postDelayed(new Runnable() {
            public void run() {
                liveMatchDownloadFromJson();
                runnable = this;
                handler.postDelayed(runnable, delay);
            }
        }, delay);

        super.onResume();
    }

    @Override
    public void onPause() {
        handler.removeCallbacks(runnable); //stop handler when activity not visible
        super.onPause();
    }



    //Load json data
    public void liveMatchDownloadFromJson() {
        if (networkCheck()) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            /**
             * For dictonary data fetching
             */
            final JsonFetchTask jsonFetchTask = ServiceGenerator.createService(JsonFetchTask.class);
            Call<DictonaryPojo> call = jsonFetchTask.dictonaryForCricket();
            /**
             * AsyncTask for Dictonary
             */
            call.enqueue(new Callback<DictonaryPojo>() {
                @Override
                public void onResponse(Call<DictonaryPojo> call, Response<DictonaryPojo> response) {
                    DictonaryPojo dict = response.body();
                    if (dict != null) {
                        mDictonaryDatabaseReference.push().setValue(dict);
                    }
                }
                @Override
                public void onFailure(Call<DictonaryPojo> call, Throwable t) {

                }
            });


            /**
             * For Live Match Data Fetching
             */
            Call<LiveMatches> liveMatchesCall = jsonFetchTask.liveMatch();
            liveMatchesCall.enqueue(new Callback<LiveMatches>() {
                @Override
                public void onResponse(Call<LiveMatches> call, Response<LiveMatches> response) {
                    LiveMatches liveMatches = response.body();
                    if (liveMatches != null) {
                        mMatchListDatabaseReference.push().setValue(liveMatches);
                    }
                }

                @Override
                public void onFailure(Call<LiveMatches> call, Throwable t) {

                }
            });
        }
    }

    public void loadViews(ArrayList<Match> matchList) {
        recyclerViewLiveMatches.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerViewLiveMatches.setHasFixedSize(true);
        adapterLiveMatches = new AdapterLiveMatches(this, matchList, dictonary);
        swipeRefreshLayout.setRefreshing(false);
        recyclerViewLiveMatches.setAdapter(adapterLiveMatches);
    }


    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(getContext(), LiveMatchDetailsActivity.class);
        intent.putParcelableArrayListExtra(LIVE_MATCH_LIST, matchesList);
        intent.putExtra(DICTONARPOJO, dictonary);
        intent.putExtra(POSITION, clickedItemIndex);

        if (networkCheck()) {
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), "Please Check Your Internet Connectivity", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRefresh() {
        liveMatchDownloadFromJson();
    }
}
