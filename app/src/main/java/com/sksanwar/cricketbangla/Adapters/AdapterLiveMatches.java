package com.sksanwar.cricketbangla.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sksanwar.cricketbangla.Pojo.DictonaryPojo;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Inning;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Match;
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



/**
 * Created by sksho on 18-Nov-17.
 */

public class AdapterLiveMatches extends
        RecyclerView.Adapter<AdapterLiveMatches.LiveMatchViewHolder> {

    private static final String TAG = AdapterLiveMatches.class.getSimpleName();
    public static String matchTimeMonth;
    public static String matchTimeDay;
    public static String matchTimeDate;
    public static String matchTime;
    public DictonaryPojo dictonary;
    private ListItemClickListener mOnClickListener;
    private ArrayList<Match> matchList;

    public AdapterLiveMatches(ListItemClickListener mOnClickListener,
                              ArrayList<Match> matchList, DictonaryPojo dictonary) {
        this.mOnClickListener = mOnClickListener;
        this.matchList = matchList;
        this.dictonary = dictonary;
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

        public LiveMatchViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        //Bind the View
        public void bind(int position) {
            if (matchList != null) {
                //get over text from the Dictonary
                if (dictonary != null) {
                    overText1.setText(dictonary.getOvers());
                    overText2.setText(dictonary.getOvers());
                    date_text.setText(dictonary.getVenue_time() + ":");
                }
                String imageUrlTeam1 = "http://i.cricketcb.com/cbzvernacular/flags/" + matchList.get(position).getTeam1().getFlag();
                String imageUrlTeam2 = "http://i.cricketcb.com/cbzvernacular/flags/" + matchList.get(position).getTeam2().getFlag();
                Picasso
                        .with(itemView.getContext())
                        .load(imageUrlTeam1)
                        .into(team1_flag);

                Picasso
                        .with(itemView.getContext())
                        .load(imageUrlTeam2)
                        .into(team2_flag);


                series_name.setText(matchList.get(position).getSeries_name());
                matchstatus.setText(matchList.get(position).getHeader().getStatus());
                team1_country_name.setText(matchList.get(position).getTeam1().getName());
                team2_country_name.setText(matchList.get(position).getTeam2().getName());

                //Visibility of Score if match State is preview
                if (matchList.get(position).getHeader().getState().equals("preview")) {
                    back_slash_score1.setVisibility(INVISIBLE);
                    back_slash_score2.setVisibility(INVISIBLE);
                    overText1.setVisibility(INVISIBLE);
                    overText2.setVisibility(INVISIBLE);
                }

                //setting matchtime into day getting text from dictonary
                matchTimeDay = convertDayFromUnix(matchList.get(position).getHeader().getStart_time(),
                        matchList.get(position).getVenue().getTimezone());

                //setting matchtime into MOnth getting text from dictonary
                matchTimeMonth = convertMonthFromUnix(matchList.get(position).getHeader().getStart_time(),
                        matchList.get(position).getVenue().getTimezone());

                //setting matchtime into date getting text from dictonary
                matchTimeDate = convertDateFromUnix(matchList.get(position).getHeader().getStart_time(),
                        matchList.get(position).getVenue().getTimezone());

                //setting matchtime into date getting text from dictonary
                matchTime = convertTimeFromUnix(matchList.get(position).getHeader().getStart_time(),
                        matchList.get(position).getVenue().getTimezone());

                match_date.setText(matchTimeDay + ", " + matchTimeDate + ", " + matchTimeMonth + ", " + matchTime);

                //show score
                if (matchList.get(position).getBat_team() != null || matchList.get(position).getBow_team() != null) {
                    List<Inning> batTeamInnings = matchList.get(position).getBat_team().getInnings();
                    List<Inning> bowTeamInnings = matchList.get(position).getBow_team().getInnings();

                    String team1ID = matchList.get(position).getTeam1().getId();
                    String team2ID = matchList.get(position).getTeam2().getId();

                    if (matchList.get(position).getBat_team().getId().equals(team1ID) ||
                            matchList.get(position).getBat_team().getId().equals(team2ID)) {
                        //show team1 1 score
                        for (int i = 0; i < batTeamInnings.size(); i++) {
                            if (batTeamInnings.get(i).getScore() != null) {
                                team1_score.setText(batTeamInnings.get(i).getScore());
                            }

                            if (batTeamInnings.get(i).getWkts() != null) {
                                team1_wkt.setText(batTeamInnings.get(i).getWkts());
                            }

                            if (batTeamInnings.get(i).getOvers() != null) {
                                team1_overs.setText(batTeamInnings.get(i).getOvers());
                            }
                        }
                    }

                    if (matchList.get(position).getBow_team().getId().equals(team1ID) ||
                            matchList.get(position).getBow_team().getId().equals(team2ID)) {
                        //show team1 2 score
                        for (int i = 0; i < bowTeamInnings.size(); i++) {
                            if (bowTeamInnings.get(i).getScore() != null) {
                                team2_score.setText(bowTeamInnings.get(i).getScore());
                            }
                            if (bowTeamInnings.get(i).getWkts() != null) {
                                team2_wkt.setText(bowTeamInnings.get(i).getWkts());
                            }

                            if (bowTeamInnings.get(i).getOvers() != null) {
                                team2_overs.setText(bowTeamInnings.get(i).getOvers());
                            }
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
            if (dictonary != null) {
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
                    Toast.makeText(itemView.getContext(), "Error Getting Data", Toast.LENGTH_SHORT).show();
                }
            }
            return result;
        }

        //Month Format
        public String convertMonthFromUnix(String unix_time, String time_zone)
                throws NullPointerException, IllegalArgumentException {
            String resultMonth;
            long time = Long.valueOf(unix_time);
            String TIMEZONE = "GMT" + time_zone;
            Date date = new Date(time * 1000);
            SimpleDateFormat simpleDayFormat = new SimpleDateFormat("MMM", Locale.ENGLISH);
            simpleDayFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE));
            resultMonth = simpleDayFormat.format(date);

            //Condition to show from dictonary
            if (dictonary != null) {
                if (resultMonth.contains("Jan")) {
                    return resultMonth.replace("Jan", dictonary.getMonth_jan());
                } else if (resultMonth.contains("Feb")) {
                    return resultMonth.replace("Feb", dictonary.getMonth_feb());
                } else if (resultMonth.contains("Mar")) {
                    return resultMonth.replace("Mar", dictonary.getMonth_mar());
                } else if (resultMonth.contains("Apr")) {
                    return resultMonth.replace("Apr", dictonary.getMonth_apr());
                } else if (resultMonth.contains("May")) {
                    return resultMonth.replace("May", dictonary.getMonth_may());
                } else if (resultMonth.contains("Jun")) {
                    return resultMonth.replace("Jun", dictonary.getMonth_jun());
                } else if (resultMonth.contains("Jul")) {
                    return resultMonth.replace("Jul", dictonary.getMonth_jul());
                } else if (resultMonth.contains("Aug")) {
                    return resultMonth.replace("Aug", dictonary.getMonth_aug());
                } else if (resultMonth.contains("Sep")) {
                    return resultMonth.replace("Sep", dictonary.getMonth_sep());
                } else if (resultMonth.contains("Oct")) {
                    return resultMonth.replace("Oct", dictonary.getMonth_oct());
                } else if (resultMonth.contains("Nov")) {
                    return resultMonth.replace("Nov", dictonary.getMonth_nov());
                } else if (resultMonth.contains("Dec")) {
                    return resultMonth.replace("Dec", dictonary.getMonth_dec());
                } else {
                    Toast.makeText(itemView.getContext(), "Error Getting Data", Toast.LENGTH_SHORT).show();
                }
            }
            return resultMonth;
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
