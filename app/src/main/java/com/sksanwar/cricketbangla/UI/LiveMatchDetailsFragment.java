package com.sksanwar.cricketbangla.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sksanwar.cricketbangla.FetchData.JsonFetchTask;
import com.sksanwar.cricketbangla.FetchData.ServiceGenerator;
import com.sksanwar.cricketbangla.Pojo.LiveMatchDetailsPojo.LiveMatchDetails;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Match;
import com.sksanwar.cricketbangla.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sksho on 26-Nov-17.
 */

public class LiveMatchDetailsFragment extends Fragment {

    private static final String TAG = LiveMatchDetailsFragment.class.getSimpleName();
    @BindView(R.id.team1_flag)
    ImageView team1_flag;
    @BindView(R.id.series_name)
    TextView series_name;
    private ArrayList<Match> matchList;
    private ArrayList<LiveMatchDetails> liveMatchDetailList = new ArrayList<>();
    private int index;
    private String matchId;
    private LiveMatchDetails liveMatchDetails;

    public LiveMatchDetailsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.live_match_details_fragment, container, false);
        ButterKnife.bind(this, rootView);

        //getting extra data into matchdetails list with the position
        matchList = getActivity().getIntent().getParcelableArrayListExtra(MainActivityFragment.LIVE_MATCH_LIST);
        index = getActivity().getIntent().getExtras().getInt(MainActivityFragment.POSITION);

        matchId = matchList.get(index).getMatch_id();
        liveMatchDetailsDownloadFromJson();

        return rootView;
    }

    private void liveMatchDetailsDownloadFromJson() {

        JsonFetchTask jsonFetchTask = ServiceGenerator.createService(JsonFetchTask.class);
        Call<LiveMatchDetails> liveMatchDetailsCall = jsonFetchTask.liveMatchDetails(matchId);
        liveMatchDetailsCall.enqueue(new Callback<LiveMatchDetails>() {
            @Override
            public void onResponse(Call<LiveMatchDetails> call, Response<LiveMatchDetails> response) {
                liveMatchDetails = response.body();
                if (liveMatchDetails != null) {
                    liveMatchDetailList.add(index, liveMatchDetails);
                    setData();
                } else {
                    Toast.makeText(getContext(), "Error Geting data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LiveMatchDetails> call, Throwable t) {
                Toast.makeText(getContext(), "Error Getting Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setData() {
        series_name.setText(liveMatchDetailList.get(index).getSeries_name());
    }
}
