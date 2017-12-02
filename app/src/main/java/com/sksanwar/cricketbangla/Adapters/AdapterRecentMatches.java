package com.sksanwar.cricketbangla.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Match;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Score;
import com.sksanwar.cricketbangla.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.INVISIBLE;
import static com.sksanwar.cricketbangla.Activities.MainActivity.dictonary;


/**
 * Created by sksho on 18-Nov-17.
 */

public class AdapterRecentMatches extends
        RecyclerView.Adapter<AdapterRecentMatches.RecentMatchViewHolder> {

    private static final String TAG = AdapterRecentMatches.class.getSimpleName();
    public static String matchTimeMonth;
    public static String matchTimeDay;
    public static String matchTimeDate;
    public static String matchTime;

    final private ListItemClickListener mOnClickListener;
    private ArrayList<Match> recentMatchList;


    public AdapterRecentMatches(ListItemClickListener mOnClickListener, ArrayList<Match> matchList) {
        this.mOnClickListener = mOnClickListener;
        this.recentMatchList = matchList;
    }

    @Override
    public RecentMatchViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.match_card_view_component, viewGroup, false);

        return new RecentMatchViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecentMatchViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if (null == recentMatchList) return 0;
        return recentMatchList.size();
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    //LiveMatchView Holder
    class RecentMatchViewHolder extends
            RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.series_name)
        TextView series_name;
        @BindView(R.id.match_date)
        TextView match_date;
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
        @BindView(R.id.back_slash_score_team1)
        TextView back_slash_score1;
        @BindView(R.id.back_slash_score_team2)
        TextView back_slash_score2;
        @BindView(R.id.team1_flag)
        ImageView team1_flag;
        @BindView(R.id.team2_flag)
        ImageView team2_flag;
        @BindView(R.id.date_text)
        TextView date_text;

        public RecentMatchViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        //Bind the View
        public void bind(int position) {
            if (recentMatchList != null) {

                //get over text from the Dictonary
                if (dictonary != null) {
                    overText1.setText(dictonary.getOvers());
                    overText2.setText(dictonary.getOvers());
                    date_text.setText(dictonary.getVenue_time() + ":");
                }
                String imageUrlTeam1 = "http://i.cricketcb.com/cbzvernacular/flags/" + recentMatchList.get(position).getTeam1().getFlag();
                String imageUrlTeam2 = "http://i.cricketcb.com/cbzvernacular/flags/" + recentMatchList.get(position).getTeam2().getFlag();
                Picasso
                        .with(itemView.getContext())
                        .load(imageUrlTeam1)
                        .into(team1_flag);

                Picasso
                        .with(itemView.getContext())
                        .load(imageUrlTeam2)
                        .into(team2_flag);


                series_name.setText(recentMatchList.get(position).getSeries_name());
                matchstatus.setText(recentMatchList.get(position).getHeader().getStatus());
                team1_country_name.setText(recentMatchList.get(position).getTeam1().getName());
                team2_country_name.setText(recentMatchList.get(position).getTeam2().getName());

                //Visibility of Score if match State is preview
                if (recentMatchList.get(position).getHeader().getState().equals("preview")) {
                    back_slash_score1.setVisibility(INVISIBLE);
                    back_slash_score2.setVisibility(INVISIBLE);
                    overText1.setVisibility(INVISIBLE);
                    overText2.setVisibility(INVISIBLE);
                }

                //setting matchtime into day getting text from dictonary
                matchTimeDay = convertDayFromUnix(recentMatchList.get(position).getHeader().getStart_time(),
                        recentMatchList.get(position).getVenue().getTimezone());

                //setting matchtime into MOnth getting text from dictonary
                matchTimeMonth = convertMonthFromUnix(recentMatchList.get(position).getHeader().getStart_time(),
                        recentMatchList.get(position).getVenue().getTimezone());

                //setting matchtime into date getting text from dictonary
                matchTimeDate = convertDateFromUnix(recentMatchList.get(position).getHeader().getStart_time(),
                        recentMatchList.get(position).getVenue().getTimezone());

                //setting matchtime into date getting text from dictonary
                matchTime = convertTimeFromUnix(recentMatchList.get(position).getHeader().getStart_time(),
                        recentMatchList.get(position).getVenue().getTimezone());

                match_date.setText(matchTimeDay + ", " + matchTimeDate + ", " + matchTimeMonth);

                //show score
                if (recentMatchList.get(position).getTeam1() != null || recentMatchList.get(position).getTeam2() != null) {
                    List<Score> score = recentMatchList.get(position).getScore();

                    for (int i = 0; i < score.size(); i++) {
                        if (recentMatchList.get(position).getTeam1().getId().equals(score.get(i).getTeam_id())) {
                            team1_score.setText(score.get(i).getScore());
                            team1_wkt.setText(score.get(i).getWkts());
                            team1_overs.setText(score.get(i).getOvers());
                        }

                        if (recentMatchList.get(position).getTeam2().getId().equals(score.get(i).getTeam_id())) {
                            team2_score.setText(score.get(i).getScore());
                            team2_wkt.setText(score.get(i).getWkts());
                            team2_overs.setText(score.get(i).getOvers());
                        }

                    }
                }

            }
        }


        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }

        /**
         * Method to convert unix time into day only
         *
         * @param unix_time
         * @param time_zone
         * @return
         * @throws NullPointerException
         * @throws IllegalArgumentException
         */

        public String convertDayFromUnix(String unix_time, String time_zone)
                throws NullPointerException, IllegalArgumentException {
            String result;
            long time = Long.valueOf(unix_time);
            String TIMEZONE = "GMT" + time_zone;
            Date date = new Date(time * 1000);
            SimpleDateFormat simpleDayFormat = new SimpleDateFormat("EEEE", Locale.ENGLISH);
            simpleDayFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE));
            result = simpleDayFormat.format(date);

            //Condition to show from dictonary
            if (result.contains("Friday")) {
                return result.replace("Friday", dictonary.getWeek_fri());
            } else if (result.contains("Saturday")) {
                return result.replace("Saturday", dictonary.getWeek_sat());
            } else if (result.contains("Sunday")) {
                return result.replace("Sunday", dictonary.getWeek_sun());
            } else if (result.contains("Monday")) {
                return result.replace("Monday", dictonary.getWeek_mon());
            } else if (result.contains("Tuesday")) {
                return result.replace("Tuesday", dictonary.getWeek_tue());
            } else if (result.contains("Wednesday")) {
                return result.replace("Wednesday", dictonary.getWeek_wed());
            } else if (result.contains("Thursday")) {
                return result.replace("Thursday", dictonary.getWeek_thu());
            } else {
                return result;
            }
        }

        //Month Format
        public String convertMonthFromUnix(String unix_time, String time_zone)
                throws NullPointerException, IllegalArgumentException {
            String result;
            long time = Long.valueOf(unix_time);
            String TIMEZONE = "GMT" + time_zone;
            Date date = new Date(time * 1000);
            SimpleDateFormat simpleDayFormat = new SimpleDateFormat("MMM", Locale.ENGLISH);
            simpleDayFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE));
            result = simpleDayFormat.format(date);

            //Condition to show from dictonary
            if (result.contains("Jan")) {
                return result.replace("Jan", dictonary.getMonth_jan());
            } else if (result.contains("Feb")) {
                return result.replace("Feb", dictonary.getMonth_feb());
            } else if (result.contains("Mar")) {
                return result.replace("Mar", dictonary.getMonth_mar());
            } else if (result.contains("Apr")) {
                return result.replace("Apr", dictonary.getMonth_apr());
            } else if (result.contains("May")) {
                return result.replace("May", dictonary.getMonth_may());
            } else if (result.contains("Jun")) {
                return result.replace("Jun", dictonary.getMonth_jun());
            } else if (result.contains("Jul")) {
                return result.replace("Jul", dictonary.getMonth_jul());
            } else if (result.contains("Aug")) {
                return result.replace("Aug", dictonary.getMonth_aug());
            } else if (result.contains("Sep")) {
                return result.replace("Sep", dictonary.getMonth_sep());
            } else if (result.contains("Oct")) {
                return result.replace("Oct", dictonary.getMonth_oct());
            } else if (result.contains("Nov")) {
                return result.replace("Nov", dictonary.getMonth_nov());
            } else if (result.contains("Dec")) {
                return result.replace("Dec", dictonary.getMonth_dec());
            } else {
                return result;
            }
        }

        //Data format
        public String convertTimeFromUnix(String unix_time, String time_zone)
                throws NullPointerException, IllegalArgumentException {
            String result;
            long time = Long.valueOf(unix_time);
            String TIMEZONE = "GMT" + time_zone;
            Date date = new Date(time * 1000);
            SimpleDateFormat simpleDayFormat = new SimpleDateFormat("HH:mm a", Locale.ENGLISH);
            simpleDayFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE));
            result = simpleDayFormat.format(date);

            return result;
        }

        //Data format
        public String convertDateFromUnix(String unix_time, String time_zone)
                throws NullPointerException, IllegalArgumentException {
            String result;
            long time = Long.valueOf(unix_time);
            String TIMEZONE = "GMT" + time_zone;
            Date date = new Date(time * 1000);
            SimpleDateFormat simpleDayFormat = new SimpleDateFormat("dd", Locale.ENGLISH);
            simpleDayFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE));
            result = simpleDayFormat.format(date);

            return result;
        }
    }

}
