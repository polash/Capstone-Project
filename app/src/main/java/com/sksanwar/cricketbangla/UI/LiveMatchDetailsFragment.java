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
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Inning;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Match;
import com.sksanwar.cricketbangla.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sksanwar.cricketbangla.UI.MainActivityFragment.dictonary;

/**
 * Created by sksho on 26-Nov-17.
 */

public class LiveMatchDetailsFragment extends Fragment {

    private static final String TAG = LiveMatchDetailsFragment.class.getSimpleName();
    @BindView(R.id.team1_flag)
    ImageView team1_flag;
    @BindView(R.id.team2_flag)
    ImageView team2_flag;
    @BindView(R.id.series_name)
    TextView series_name;
    @BindView(R.id.matches_desc)
    TextView match_desc;
    @BindView(R.id.match_type)
    TextView match_type;
    @BindView(R.id.team1_country_name)
    TextView team1_country_name;
    @BindView(R.id.team2_country_name)
    TextView team2_country_name;
    @BindView(R.id.team1_score)
    TextView team1_score;
    @BindView(R.id.team2_score)
    TextView team2_score;
    @BindView(R.id.team1_wkt)
    TextView team1_wkt;
    @BindView(R.id.team2_wkt)
    TextView team2_wkt;
    @BindView(R.id.team1_over)
    TextView team1_over;
    @BindView(R.id.team2_over)
    TextView team2_over;
    @BindView(R.id.matchstatus)
    TextView matchstatus;
    @BindView(R.id.toss_match)
    TextView toss_match;
    @BindView(R.id.toss_details)
    TextView toss_details;
    @BindView(R.id.back_slash_score1)
    TextView back_slash_score1;
    @BindView(R.id.back_slash_score2)
    TextView back_slash_score2;
    @BindView(R.id.overText2)
    TextView overText2;
    @BindView(R.id.overText1)
    TextView overText1;


    private ArrayList<Match> matchList;
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
        String imageUrlTeam1 = "http://i.cricketcb.com/cbzvernacular/flags/" + liveMatchDetails.getTeam1().getFlag();
        String imageUrlTeam2 = "http://i.cricketcb.com/cbzvernacular/flags/" + liveMatchDetails.getTeam2().getFlag();
        Picasso
                .with(getContext())
                .load(imageUrlTeam1)
                .into(team1_flag);

        Picasso
                .with(getContext())
                .load(imageUrlTeam2)
                .into(team2_flag);

        series_name.setText(liveMatchDetails.getSeries_name());
        match_desc.setText(liveMatchDetails.getHeader().getMatch_desc());
        team1_country_name.setText(liveMatchDetails.getTeam1().getName());
        team2_country_name.setText(liveMatchDetails.getTeam2().getName());

        String match_types = liveMatchDetails.getHeader().getType();
        switch (match_types) {
            case "TEST":
                match_type.setText(dictonary.getTest());
                break;
            case "ODI":
                match_type.setText(dictonary.getOdi());
                break;
            case "T20":
                match_type.setText(dictonary.getT20());
                break;
            default:
                break;
        }
        matchstatus.setText(liveMatchDetails.getHeader().getStatus());
        toss_match.setText(dictonary.getToss() + ": ");
        toss_details.setText(liveMatchDetails.getHeader().getToss());

        //show score
        if (matchList.get(index).getBat_team() != null || matchList.get(index).getBow_team() != null) {
            List<Inning> batTeamInnings = matchList.get(index).getBat_team().getInnings();
            List<Inning> bowTeamInnings = matchList.get(index).getBow_team().getInnings();

            String team1ID = matchList.get(index).getTeam1().getId();
            String team2ID = matchList.get(index).getTeam2().getId();

            if (matchList.get(index).getBat_team().getId().equals(team1ID) ||
                    matchList.get(index).getBat_team().getId().equals(team2ID)) {
                //show team1 1 score
                for (int i = 0; i < batTeamInnings.size(); i++) {
                    if (batTeamInnings.get(i).getScore() != null) {
                        team1_score.setText(batTeamInnings.get(i).getScore());
                    } else {
                        back_slash_score1.setVisibility(View.GONE);
                        overText1.setVisibility(View.GONE);
                        team1_score.setText(View.GONE);
                    }
                    if (batTeamInnings.get(i).getWkts() != null) {
                        team1_wkt.setText(batTeamInnings.get(i).getWkts());
                    } else {
                        back_slash_score1.setVisibility(View.GONE);
                        overText1.setVisibility(View.GONE);
                        team1_wkt.setText(View.GONE);
                    }

                    if (batTeamInnings.get(i).getOvers() != null) {
                        team1_over.setText(batTeamInnings.get(i).getOvers());
                    } else {
                        back_slash_score1.setVisibility(View.GONE);
                        overText1.setVisibility(View.GONE);
                        team1_over.setText(View.GONE);
                    }
                }

            } else {
                for (int i = 0; i < batTeamInnings.size(); i++) {
                    team1_score.setText(View.GONE);
                    team1_wkt.setText(View.GONE);
                    team1_over.setText(View.GONE);
                }
            }

            if (matchList.get(index).getBow_team().getId().equals(team1ID) ||
                    matchList.get(index).getBow_team().getId().equals(team2ID)) {
                //show team1 2 score
                for (int i = 0; i < bowTeamInnings.size(); i++) {
                    if (bowTeamInnings.get(i).getScore() != null) {
                        team2_score.setText(bowTeamInnings.get(i).getScore());
                    } else {
                        back_slash_score2.setVisibility(View.GONE);
                        overText2.setVisibility(View.GONE);
                        team2_score.setText(View.GONE);
                    }
                    if (bowTeamInnings.get(i).getWkts() != null) {
                        team2_wkt.setText(bowTeamInnings.get(i).getWkts());
                    } else {
                        team2_wkt.setText(View.GONE);
                        back_slash_score2.setVisibility(View.GONE);
                        overText2.setVisibility(View.GONE);
                    }

                    if (bowTeamInnings.get(i).getOvers() != null) {
                        team2_over.setText(bowTeamInnings.get(i).getOvers());
                    } else {
                        team2_over.setText(View.GONE);
                        back_slash_score2.setVisibility(View.GONE);
                        overText2.setVisibility(View.GONE);
                    }
                }

            } else {
                for (int i = 0; i < bowTeamInnings.size(); i++) {
                    team2_score.setText(View.GONE);
                    team2_wkt.setText(View.GONE);
                    team2_over.setText(View.GONE);
                }
            }
        }
    }
}


