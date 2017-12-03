package com.sksanwar.cricketbangla.UI;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sksanwar.cricketbangla.Activities.LiveMatchDetailsActivity;
import com.sksanwar.cricketbangla.Adapters.AdapterLiveMatches;
import com.sksanwar.cricketbangla.FetchData.JsonFetchTask;
import com.sksanwar.cricketbangla.FetchData.ServiceGenerator;
import com.sksanwar.cricketbangla.Pojo.AsyncListner;
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

public class MainActivityFragment extends Fragment implements AsyncListner,
        AdapterLiveMatches.ListItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    public static final String LIVE_MATCH_LIST = "live_match_list";
    public static final String POSITION = "position";
    public static final String DICTONARPOJO = "dictonary";
    private static final String TAG = MainActivityFragment.class.getSimpleName();

    public ArrayList<Match> matchesList;
    @BindView(R.id.rv_livematches)
    RecyclerView recyclerViewLiveMatches;
    @BindView(R.id.swip_to_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.no_network)
    TextView no_network;
    private DictonaryPojo dictonary;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDictonaryDatabaseReference;
    private DatabaseReference mMatchListDatabaseReference;

    private AdapterLiveMatches adapterLiveMatches;

    public MainActivityFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mainactivity_fragment, container, false);
        ButterKnife.bind(this, rootView);

//        mFirebaseDatabase = FirebaseDatabase.getInstance();
//        mFirebaseDatabase.setPersistenceEnabled(true);
//        mDictonaryDatabaseReference = mFirebaseDatabase.getReference().child("Dictonary");
//        mMatchListDatabaseReference = mFirebaseDatabase.getReference().child("recentMatchList");


        no_network.setVisibility(View.GONE);
        swipeRefreshLayout.setOnRefreshListener(this);

        liveMatchDownloadFromJson();
        networkCheck();

//        mDictonaryDatabaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
//                    DictonaryPojo dictonaryPojo = dataSnapshot1.getValue(DictonaryPojo.class);
//
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        return rootView;
    }

    //Network Checks
    private boolean networkCheck() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public void liveMatchDownloadFromJson() {
        if (networkCheck()) {
            swipeRefreshLayout.setRefreshing(true);

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
                    dictonary = response.body();
                }

                @Override
                public void onFailure(Call<DictonaryPojo> call, Throwable t) {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
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
                    matchesList = liveMatches.getMatches();
                    if (matchesList != null) {
//                        mMatchListDatabaseReference.push().setValue(matchesList);
                        loadViews(matchesList);
                    }
                    Log.d(TAG, "Match List: " + matchesList);
                }

                @Override
                public void onFailure(Call<LiveMatches> call, Throwable t) {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            no_network.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(LIVE_MATCH_LIST)) {
                matchesList = savedInstanceState.getParcelable(LIVE_MATCH_LIST);
            }
        }
        //this condition checks if the matchesList is null thn run the task
        if (matchesList != null && dictonary != null) {
            loadViews(matchesList);
        } else {
            return;
        }
    }

    private void loadViews(ArrayList<Match> matchList) {
        recyclerViewLiveMatches.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerViewLiveMatches.setHasFixedSize(true);
        adapterLiveMatches = new AdapterLiveMatches(this, matchList, dictonary);
        swipeRefreshLayout.setRefreshing(false);
        recyclerViewLiveMatches.setAdapter(adapterLiveMatches);

        // add pager behavior
        SnapHelper snapHelper = new GravitySnapHelper(Gravity.START);
        snapHelper.attachToRecyclerView(recyclerViewLiveMatches);
    }


    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(getContext(), LiveMatchDetailsActivity.class);
        intent.putParcelableArrayListExtra(LIVE_MATCH_LIST, matchesList);
        intent.putExtra(DICTONARPOJO, dictonary);
        intent.putExtra(POSITION, clickedItemIndex);

        startActivity(intent);

        Toast.makeText(getContext(), "Match id: " + matchesList.get(clickedItemIndex).getMatch_id(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void returnMatchList(ArrayList<Match> matchList) {
        matchesList = matchList;
        loadViews(matchesList);
    }

    @Override
    public void returnDictonary(DictonaryPojo dictonarypojo) {
        dictonary = dictonarypojo;
    }


    @Override
    public void onRefresh() {
        liveMatchDownloadFromJson();
    }
}
