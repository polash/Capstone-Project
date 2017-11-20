package com.sksanwar.cricketbangla.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.sksanwar.cricketbangla.Adapters.AdapterLiveMatches;
import com.sksanwar.cricketbangla.FetchData.JsonFetchTask;
import com.sksanwar.cricketbangla.FetchData.ServiceGenerator;
import com.sksanwar.cricketbangla.Pojo.AsyncListner;
import com.sksanwar.cricketbangla.Pojo.DictonaryPojo;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.LiveMatches;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Match;
import com.sksanwar.cricketbangla.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sksho on 20-Nov-17.
 */

public class MainActivityFragment extends Fragment implements AsyncListner,
        AdapterLiveMatches.ListItemClickListener {

    public static final String LIVE_MATCH_LIST = "live_match_list";
    public static final String POSITION = "position";
    private static final String TAG = MainActivityFragment.class.getSimpleName();
    @BindView(R.id.rv_livematches)
    RecyclerView recyclerViewLiveMatches;

    private List<Match> matchesList;

    private AdapterLiveMatches adapterLiveMatches;

    public MainActivityFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mainactivity_fragment, container, false);
        ButterKnife.bind(this, rootView);

        liveMatchDownloadFromJson();

        return rootView;
    }

    private void liveMatchDownloadFromJson() {
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
                DictonaryPojo dictonaryPojos = response.body();
                Log.d(TAG, "Pojo " + dictonaryPojos);
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
                List<Match> match = liveMatches.getMatches();
                Log.d(TAG, "MatchList " + match);

                loadViews(match);
            }

            @Override
            public void onFailure(Call<LiveMatches> call, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(LIVE_MATCH_LIST)) {
                matchesList = savedInstanceState.getParcelable(LIVE_MATCH_LIST);
            }
        }
        //this condition checks if the recipeList is null thn run the task
        if (matchesList == null) {
            return;
        } else {
            loadViews(matchesList);
        }
    }

    private void loadViews(List<Match> matchList) {
        recyclerViewLiveMatches.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        adapterLiveMatches = new AdapterLiveMatches(this, matchList);

        recyclerViewLiveMatches.setAdapter(adapterLiveMatches);

        SnapHelper snapHelperStart = new GravitySnapHelper(Gravity.START);
        snapHelperStart.attachToRecyclerView(recyclerViewLiveMatches);
    }


    @Override
    public void onListItemClick(int position) {
        Toast.makeText(getContext(), "Item Clicked " + position, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void returnLiveMatchList(List<Match> matchList) {
        matchesList = matchList;
        loadViews(matchesList);
    }
}
