package com.sksanwar.cricketbangla.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.BatTeam;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.BowTeam;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Inning;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Match;
import com.sksanwar.cricketbangla.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.INVISIBLE;
import static com.sksanwar.cricketbangla.UI.MainActivityFragment.dictonary;

/**
 * Created by sksho on 18-Nov-17.
 */

public class AdapterLiveMatches extends
        RecyclerView.Adapter<AdapterLiveMatches.LiveMatchViewHolder> {

    private static final String TAG = AdapterLiveMatches.class.getSimpleName();

    final private ListItemClickListener mOnClickListener;

    public List<Match> matchList;
    private BatTeam batTeam;
    private BowTeam bowTeam;
    private List<Inning> batTeamInnings;
    private List<Inning> bowTeamInnings;

    public AdapterLiveMatches(ListItemClickListener mOnClickListener, List<Match> matchList) {
        this.mOnClickListener = mOnClickListener;
        this.matchList = matchList;
    }

    @Override
    public LiveMatchViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.match_card_view_component, viewGroup, false);

        return new LiveMatchViewHolder(view);

    }

    @Override
    public void onBindViewHolder(LiveMatchViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (null == matchList) return 0;
        return matchList.size();
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    //LiveMatchView Holder
    class LiveMatchViewHolder extends
            RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.matches_desc)
        TextView match_desc;
        @BindView(R.id.series_name)
        TextView series_name;
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
        @BindView(R.id.matchstatus)
        TextView matchstatus;
        @BindView(R.id.team1_over)
        TextView team1_overs;
        @BindView(R.id.team2_over)
        TextView team2_overs;
        @BindView(R.id.over_text)
        TextView overText1;
        @BindView(R.id.over_text2)
        TextView overText2;
        @BindView(R.id.match_string)
        TextView match_string;
        @BindView(R.id.back_slash_score_team1)
        TextView back_slash_score1;
        @BindView(R.id.back_slash_score_team2)
        TextView back_slash_score2;


        public LiveMatchViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        //Bind the View
        public void bind(int position) {
            if (!matchList.isEmpty()) {

                match_desc.setText(matchList.get(position).getHeader().getMatch_desc());
                series_name.setText(matchList.get(position).getSeries_Name());
                matchstatus.setText(matchList.get(position).getHeader().getStatus());
                team1_country_name.setText(matchList.get(position).getTeam1().getName());
                team2_country_name.setText(matchList.get(position).getTeam2().getName());

                overText1.setText(dictonary.getOvers());
                overText2.setText(dictonary.getOvers());
                match_string.setText(dictonary.getMatches());

                //Visibility of Score if match State is preview
                if (matchList.get(position).getHeader().getState().equals("preview")) {
                    back_slash_score1.setVisibility(INVISIBLE);
                    back_slash_score2.setVisibility(INVISIBLE);
                    overText1.setVisibility(INVISIBLE);
                    overText2.setVisibility(INVISIBLE);
                }

//                if (matchList.get(position).getHeader().getType().matches("TEST")) {
//                    match_type.setText(dictonary.getTest());
//                } else if (matchList.get(position).getHeader().getType().equals("T20")) {
//                    match_type.setText(dictonary.getT20());
//                } else {
//                    match_type.setText(dictonary.getOdi());
//                }

                match_type.setText(convertFromUnix(matchList.get(position).getHeader().getStart_time(),
                        matchList.get(position).getVenue().getTimezone()));

//                bowTeamInnings = matchList.get(position).getBowTeam().getInnings();

            }
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }

        public String convertFromUnix(String unix_time, String time_zone)
                throws NullPointerException, IllegalArgumentException {
            String result;
            long time = Long.valueOf(unix_time);
            String TIMEZONE = time_zone;

            Date date = new Date(time * 1000);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, MMM, dd");

            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT" + time_zone));
            result = simpleDateFormat.format(date);
            Log.d("date", simpleDateFormat.format(date));

            return result;
        }
    }
}
