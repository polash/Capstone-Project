package com.sksanwar.cricketbangla.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sksanwar.cricketbangla.FetchData.JsonFetchTask;
import com.sksanwar.cricketbangla.FetchData.ServiceGenerator;
import com.sksanwar.cricketbangla.Pojo.LiveMatchDetailsPojo.LiveMatchDetails;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Match;
import com.sksanwar.cricketbangla.R;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sksho on 26-Nov-17.
 */

public class LiveMatchDetailsFragment extends Fragment {

    private static final String TAG = LiveMatchDetailsFragment.class.getSimpleName();
    private ArrayList<Match> matchList;
    private ArrayList<LiveMatchDetails> liveMatchDetailList = new ArrayList<>();
    private int index;

    public LiveMatchDetailsFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.live_match_details_fragment, container, false);

        //getting extra data into matchdetails list with the position
        matchList = getActivity().getIntent().getParcelableArrayListExtra(MainActivityFragment.LIVE_MATCH_LIST);
        index = getActivity().getIntent().getExtras().getInt(MainActivityFragment.POSITION);

        String match_id = matchList.get(index).getMatch_id();

        JsonFetchTask jsonFetchTask = ServiceGenerator.createService(JsonFetchTask.class);
        retrofit2.Call<LiveMatchDetails> liveMatchDetailsCall = jsonFetchTask.liveMatchDetails(match_id);

        liveMatchDetailsCall.enqueue(new Callback<LiveMatchDetails>() {
            @Override
            public void onResponse(retrofit2.Call<LiveMatchDetails> call, Response<LiveMatchDetails> response) {
                LiveMatchDetails liveMatchDetails = response.body();

                if (liveMatchDetails != null) {
                    liveMatchDetailList.add(index, liveMatchDetails);
                    Log.d(TAG, "Details Data : " + liveMatchDetailList);
                } else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<LiveMatchDetails> call, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });


        return rootView;
    }
}
