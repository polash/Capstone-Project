package com.sksanwar.cricketbangla.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sksanwar.cricketbangla.FetchData.JsonFetchTask;
import com.sksanwar.cricketbangla.FetchData.ServiceGenerator;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Match;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Score;
import com.sksanwar.cricketbangla.Pojo.RecentMatchPojo.RecentMatchDetails;
import com.sksanwar.cricketbangla.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.INVISIBLE;
import static com.sksanwar.cricketbangla.Activities.MainActivity.dictonary;
import static com.sksanwar.cricketbangla.Adapters.AdapterLiveMatches.matchTime;
import static com.sksanwar.cricketbangla.Adapters.AdapterLiveMatches.matchTimeDate;
import static com.sksanwar.cricketbangla.Adapters.AdapterLiveMatches.matchTimeDay;
import static com.sksanwar.cricketbangla.Adapters.AdapterLiveMatches.matchTimeMonth;

/**
 * Created by sksho on 26-Nov-17.
 */

public class RecentMatchDetailsFragment extends Fragment {

    private static final String TAG = RecentMatchDetailsFragment.class.getSimpleName();
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
    @BindView(R.id.match_info)
    TextView match_info;
    @BindView(R.id.tv_series_name)
    TextView tv_series_name;
    @BindView(R.id.tv_venu)
    TextView tv_venu;
    @BindView(R.id.tv_date)
    TextView tv_date;
    @BindView(R.id.tv_umpire)
    TextView tv_umpire;
    @BindView(R.id.tv_3rd_umpire)
    TextView tv_3rd_umpire;
    @BindView(R.id.tv_referee)
    TextView tv_referee;
    @BindView(R.id.series_name_venu)
    TextView series_name_venu;
    @BindView(R.id.venu_name)
    TextView venu_name;
    @BindView(R.id.umpire_name)
    TextView umpire_name;
    @BindView(R.id.rd3_umpire_name)
    TextView rd3_umpire_name;
    @BindView(R.id.referee_name)
    TextView referee_name;
    @BindView(R.id.match_date)
    TextView match_date;
    @BindView(R.id.umpire_colon)
    TextView umpire_colon;
    @BindView(R.id.rd3_umpire_colon)
    TextView rd3_umpire_colon;
    @BindView(R.id.referee_colon)
    TextView referee_colon;
    @BindView(R.id.match_start_message)
    TextView match_start_message;

    private ArrayList<Match> recentMatchList;
    private int recentMatchListIndex;
    private String recentmatchId;
    private RecentMatchDetails recentMatchDetails;


    public RecentMatchDetailsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.live_match_details_fragment, container, false);
        ButterKnife.bind(this, rootView);

        //getting extra data into matchdetails list with the position
        recentMatchList = getActivity().getIntent().getParcelableArrayListExtra(RecentMatchActivityFragment.RECENT_MATCH_LIST);
        recentMatchListIndex = getActivity().getIntent().getExtras().getInt(RecentMatchActivityFragment.POSITION);

        recentmatchId = recentMatchList.get(recentMatchListIndex).getMatch_id();
        recentMatchDetailsDownloadFromJson();

        return rootView;
    }

    private void recentMatchDetailsDownloadFromJson() {

        JsonFetchTask jsonFetchTask = ServiceGenerator.createService(JsonFetchTask.class);
        Call<RecentMatchDetails> recentMatchesCall = jsonFetchTask.recentMatchDetails(recentmatchId);
        recentMatchesCall.enqueue(new Callback<RecentMatchDetails>() {
            @Override
            public void onResponse(Call<RecentMatchDetails> call, Response<RecentMatchDetails> response) {
                recentMatchDetails = response.body();
                if (recentMatchDetails != null) {
                    setData();
                }
            }

            @Override
            public void onFailure(Call<RecentMatchDetails> call, Throwable t) {

            }
        });

    }

    private void setData() {
        String imageUrlTeam1 = "http://i.cricketcb.com/cbzvernacular/flags/" + recentMatchDetails.getTeam1().getFlag();
        String imageUrlTeam2 = "http://i.cricketcb.com/cbzvernacular/flags/" + recentMatchDetails.getTeam2().getFlag();
        Picasso
                .with(getContext())
                .load(imageUrlTeam1)
                .into(team1_flag);

        Picasso
                .with(getContext())
                .load(imageUrlTeam2)
                .into(team2_flag);

        series_name.setText(recentMatchDetails.getSeries_name());
        match_desc.setText(recentMatchDetails.getHeader().getMatch_desc());
        team1_country_name.setText(recentMatchDetails.getTeam1().getName());
        team2_country_name.setText(recentMatchDetails.getTeam2().getName());

        String match_types = recentMatchDetails.getHeader().getType();
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
        matchstatus.setText(recentMatchDetails.getHeader().getStatus());
        toss_match.setText(dictonary.getToss() + ": ");
        toss_details.setText(recentMatchDetails.getHeader().getToss());

        if (recentMatchList.get(recentMatchListIndex).getHeader().getState().equals("preview")) {
            back_slash_score1.setVisibility(INVISIBLE);
            overText1.setVisibility(INVISIBLE);
            back_slash_score2.setVisibility(INVISIBLE);
            overText2.setVisibility(INVISIBLE);
            match_start_message.setVisibility(View.VISIBLE);
            match_start_message.setText("খেলা এখনো শুরু হয়নি");


        } else  //show score
            if (recentMatchList.get(recentMatchListIndex).getTeam1() != null || recentMatchList.get(recentMatchListIndex).getTeam2() != null) {
                List<Score> score = recentMatchList.get(recentMatchListIndex).getScore();

                for (int i = 0; i < score.size(); i++) {
                    if (recentMatchList.get(recentMatchListIndex).getTeam1().getId().equals(score.get(i).getTeam_id())) {
                        team1_score.setText(score.get(i).getScore());
                        team1_wkt.setText(score.get(i).getWkts());
                        team1_over.setText(score.get(i).getOvers());
                    }

                    if (recentMatchList.get(recentMatchListIndex).getTeam2().getId().equals(score.get(i).getTeam_id())) {
                        team2_score.setText(score.get(i).getScore());
                        team2_wkt.setText(score.get(i).getWkts());
                        team2_over.setText(score.get(i).getOvers());
                    }

                }
            }


        //Match info

        match_info.setText(dictonary.getMatch_info());
        tv_series_name.setText(dictonary.getSeries_name());
        tv_venu.setText(dictonary.getVenue());
        tv_date.setText(dictonary.getDate());

        match_date.setText(matchTimeDay + ", " + matchTimeDate + ", " + matchTimeMonth + ", " + matchTime);
        series_name_venu.setText(recentMatchDetails.getSeries_name());
        venu_name.setText(recentMatchDetails.getVenue().getName() + ", " + recentMatchDetails.getVenue().getLocation());

        if (recentMatchDetails.getOfficial() != null) {
            tv_umpire.setText(dictonary.getUmpires());
            tv_3rd_umpire.setText(dictonary.getUmpire_3());
            tv_referee.setText(dictonary.getReferee());

            umpire_name.setText(recentMatchDetails.getOfficial().getUmpire().getName() + ", " +
                    recentMatchDetails.getOfficial().getUmpire2().getName());

            if (recentMatchDetails.getOfficial().getUmpire3() != null) {
                rd3_umpire_name.setText(recentMatchDetails.getOfficial().getUmpire3().getName());
            } else {
                rd3_umpire_name.setVisibility(View.GONE);
                tv_3rd_umpire.setVisibility(View.GONE);
                rd3_umpire_colon.setVisibility(View.GONE);
            }

            if (recentMatchDetails.getOfficial().getReferee() != null) {
                referee_name.setText(recentMatchDetails.getOfficial().getReferee().getName());
            } else {
                referee_name.setVisibility(View.GONE);
                referee_colon.setVisibility(View.GONE);
                tv_referee.setVisibility(View.GONE);
            }

        } else {
            tv_umpire.setVisibility(View.GONE);
            tv_3rd_umpire.setVisibility(View.GONE);
            tv_referee.setVisibility(View.GONE);
            umpire_name.setVisibility(View.GONE);
            rd3_umpire_name.setVisibility(View.GONE);
            referee_name.setVisibility(View.GONE);
            umpire_colon.setVisibility(View.GONE);
            rd3_umpire_colon.setVisibility(View.GONE);
            referee_colon.setVisibility(View.GONE);
        }
    }
}


