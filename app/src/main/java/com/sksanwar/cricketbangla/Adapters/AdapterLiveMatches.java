package com.sksanwar.cricketbangla.Adapters;

import android.content.Context;
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
    public ListItemClickListener mOnClickListener;
    private ArrayList<Match> matchList;
    private Context mContext;


    public AdapterLiveMatches(Context context, ListItemClickListener mOnClickListener,
                              ArrayList<Match> matchList, DictonaryPojo dictonary) {
        this.mOnClickListener = mOnClickListener;
        this.matchList = matchList;
        this.dictonary = dictonary;
        this.mContext = context;
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
                    date_text.append(dictonary.getVenue_time() + mContext.getString(R.string.colon_string));
                }

                //Setting the flag Image
                String defaultFlagImage = mContext.getString(R.string.default_image_address);
                if (matchList.get(position).getTeam1().getFlag() != null) {
                    String imageUrlTeam1 = mContext.getString(R.string.flag_image_url) + matchList.get(position).getTeam1().getFlag();
                    Picasso
                            .with(itemView.getContext())
                            .load(imageUrlTeam1)
                            .into(team1_flag);
                } else {
                    Picasso
                            .with(itemView.getContext())
                            .load(defaultFlagImage)
                            .into(team1_flag);
                }


                if (matchList.get(position).getTeam2().getFlag() != null) {
                    String imageUrlTeam2 = mContext.getString(R.string.flag_image_url) + matchList.get(position).getTeam2().getFlag();
                    Picasso
                            .with(itemView.getContext())
                            .load(imageUrlTeam2)
                            .into(team2_flag);
                } else {
                    Picasso
                            .with(itemView.getContext())
                            .load(defaultFlagImage)
                            .into(team2_flag);
                }


                series_name.setText(matchList.get(position).getSeries_name());
                matchstatus.setText(matchList.get(position).getHeader().getStatus());
                team1_country_name.setText(matchList.get(position).getTeam1().getName());
                team2_country_name.setText(matchList.get(position).getTeam2().getName());

                //Visibility of Score if match State is preview
                if (matchList.get(position).getHeader().getState().equals(mContext.getString(R.string.preview_string))) {
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

                match_date.append(matchTimeDay + mContext.getString(R.string.comma_string) + matchTimeDate +
                        mContext.getString(R.string.comma_string) + matchTimeMonth + mContext.getString(R.string.comma_string) + matchTime);

                //show score
                if (matchList.get(position).getBat_team() != null || matchList.get(position).getBow_team() != null) {
                    List<Inning> batTeamInnings = matchList.get(position).getBat_team().getInnings();
                    List<Inning> bowTeamInnings = matchList.get(position).getBow_team().getInnings();

                    String team1ID = matchList.get(position).getTeam1().getId();
                    String team2ID = matchList.get(position).getTeam2().getId();

                    String batTeamID = matchList.get(position).getBat_team().getId();
                    String bowTeamID = matchList.get(position).getBow_team().getId();

                    if (batTeamID.equals(team1ID)) {
                        if (batTeamInnings != null) {
                            //show team1 1 score
                            for (int i = 0; i < batTeamInnings.size(); i++) {
                                if (batTeamInnings.get(i).getScore() != null) {
                                    team1_score.setText(batTeamInnings.get(i).getScore());
                                } else {
                                    team1_score.setVisibility(View.GONE);
                                }

                                if (batTeamInnings.get(i).getWkts() != null) {
                                    team1_wkt.setText(batTeamInnings.get(i).getWkts());
                                } else {
                                    team1_wkt.setVisibility(View.GONE);
                                }

                                if (batTeamInnings.get(i).getOvers() != null) {
                                    team1_overs.setText(batTeamInnings.get(i).getOvers());
                                } else {
                                    team1_overs.setVisibility(View.GONE);
                                }
                            }
                        } else {
                            team1_score.setVisibility(View.GONE);
                            team1_wkt.setVisibility(View.GONE);
                            team1_overs.setVisibility(View.GONE);
                            back_slash_score1.setText(" - ");
                            overText1.setVisibility(View.GONE);
                        }

                    } else {
                        if (bowTeamInnings != null) {
                            for (int k = 0; k < bowTeamInnings.size(); k++) {
                                if (bowTeamInnings.get(k).getScore() != null) {
                                    team1_score.setText(bowTeamInnings.get(k).getScore());
                                } else {
                                    team1_score.setVisibility(View.GONE);
                                }

                                if (bowTeamInnings.get(k).getWkts() != null) {
                                    team1_wkt.setText(bowTeamInnings.get(k).getWkts());
                                } else {
                                    team1_wkt.setVisibility(View.GONE);
                                }

                                if (bowTeamInnings.get(k).getOvers() != null) {
                                    team1_overs.setText(bowTeamInnings.get(k).getOvers());
                                } else {
                                    team1_overs.setVisibility(View.GONE);
                                }
                            }
                        } else {
                            team1_score.setVisibility(View.GONE);
                            team1_wkt.setVisibility(View.GONE);
                            team1_overs.setVisibility(View.GONE);
                            back_slash_score1.setText(" - ");
                            overText1.setVisibility(View.GONE);
                        }

                    }

                    if (bowTeamID.equals(team2ID)) {
                        if (bowTeamInnings != null) {
                            for (int j = 0; j < bowTeamInnings.size(); j++) {
                                if (bowTeamInnings.get(j).getScore() != null) {
                                    team2_score.setText(bowTeamInnings.get(j).getScore());
                                } else {
                                    team2_score.setVisibility(View.GONE);
                                }
                                if (bowTeamInnings.get(j).getWkts() != null) {
                                    team2_wkt.setText(bowTeamInnings.get(j).getWkts());
                                } else {
                                    team2_wkt.setVisibility(View.GONE);
                                }

                                if (bowTeamInnings.get(j).getOvers() != null) {
                                    team2_overs.setText(bowTeamInnings.get(j).getOvers());
                                } else {
                                    team2_overs.setVisibility(View.GONE);
                                }
                            }
                        } else {
                            team2_score.setVisibility(View.GONE);
                            team2_wkt.setVisibility(View.GONE);
                            team2_overs.setVisibility(View.GONE);
                            overText2.setVisibility(View.GONE);
                            back_slash_score2.setText(" - ");
                        }
                        //show team 2 score

                    } else {
                        if (batTeamInnings != null) {
                            for (int l = 0; l < batTeamInnings.size(); l++) {
                                if (batTeamInnings.get(l).getScore() != null) {
                                    team2_score.setText(batTeamInnings.get(l).getScore());
                                } else {
                                    team2_score.setVisibility(View.GONE);
                                }

                                if (batTeamInnings.get(l).getWkts() != null) {
                                    team2_wkt.setText(batTeamInnings.get(l).getWkts());
                                } else {
                                    team2_wkt.setVisibility(View.GONE);
                                }

                                if (batTeamInnings.get(l).getOvers() != null) {
                                    team2_overs.setText(batTeamInnings.get(l).getOvers());
                                } else {
                                    team2_overs.setVisibility(View.GONE);
                                }

                            }
                        } else {
                            team2_score.setVisibility(View.GONE);
                            team2_wkt.setVisibility(View.GONE);
                            team2_overs.setVisibility(View.GONE);
                            overText2.setVisibility(View.GONE);
                            back_slash_score2.setText(" - ");
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

        private String convertDayFromUnix(String unix_time, String time_zone)
                throws NullPointerException, IllegalArgumentException {
            String result;
            long time = Long.valueOf(unix_time);
            String TIMEZONE = mContext.getString(R.string.time_GMT) + time_zone;
            Date date = new Date(time * 1000);
            SimpleDateFormat simpleDayFormat = new SimpleDateFormat(mContext.getString(R.string.time_Day), Locale.ENGLISH);
            simpleDayFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE));
            result = simpleDayFormat.format(date);

            //Condition to show from dictonary
            if (dictonary != null) {
                if (result.contains(mContext.getString(R.string.day_Friday))) {
                    return result.replace(mContext.getString(R.string.day_Friday), dictonary.getWeek_fri());
                } else if (result.contains(mContext.getString(R.string.day_Saturday))) {
                    return result.replace(mContext.getString(R.string.day_Saturday), dictonary.getWeek_sat());
                } else if (result.contains(mContext.getString(R.string.day_Sunday))) {
                    return result.replace(mContext.getString(R.string.day_Sunday), dictonary.getWeek_sun());
                } else if (result.contains(mContext.getString(R.string.day_Monday))) {
                    return result.replace(mContext.getString(R.string.day_Monday), dictonary.getWeek_mon());
                } else if (result.contains(mContext.getString(R.string.day_Tuesday))) {
                    return result.replace(mContext.getString(R.string.day_Tuesday), dictonary.getWeek_tue());
                } else if (result.contains(mContext.getString(R.string.day_Wednesday))) {
                    return result.replace(mContext.getString(R.string.day_Wednesday), dictonary.getWeek_wed());
                } else if (result.contains(mContext.getString(R.string.day_Thursday))) {
                    return result.replace(mContext.getString(R.string.day_Thursday), dictonary.getWeek_thu());
                } else {
                    Toast.makeText(itemView.getContext(), mContext.getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }
            return result;
        }

        //Month Format
        private String convertMonthFromUnix(String unix_time, String time_zone)
                throws NullPointerException, IllegalArgumentException {
            String resultMonth;
            long time = Long.valueOf(unix_time);
            String TIMEZONE = mContext.getString(R.string.time_GMT) + time_zone;
            Date date = new Date(time * 1000);
            SimpleDateFormat simpleDayFormat = new SimpleDateFormat(mContext.getString(R.string.time_Month), Locale.ENGLISH);
            simpleDayFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE));
            resultMonth = simpleDayFormat.format(date);

            //Condition to show from dictonary
            if (dictonary != null) {
                if (resultMonth.contains(mContext.getString(R.string.month_Jan))) {
                    return resultMonth.replace(mContext.getString(R.string.month_Jan), dictonary.getMonth_jan());
                } else if (resultMonth.contains(mContext.getString(R.string.month_Feb))) {
                    return resultMonth.replace(mContext.getString(R.string.month_Feb), dictonary.getMonth_feb());
                } else if (resultMonth.contains(mContext.getString(R.string.month_Mar))) {
                    return resultMonth.replace(mContext.getString(R.string.month_Mar), dictonary.getMonth_mar());
                } else if (resultMonth.contains(mContext.getString(R.string.month_Apr))) {
                    return resultMonth.replace(mContext.getString(R.string.month_Apr), dictonary.getMonth_apr());
                } else if (resultMonth.contains(mContext.getString(R.string.month_May))) {
                    return resultMonth.replace(mContext.getString(R.string.month_May), dictonary.getMonth_may());
                } else if (resultMonth.contains(mContext.getString(R.string.month_Jun))) {
                    return resultMonth.replace(mContext.getString(R.string.month_Jun), dictonary.getMonth_jun());
                } else if (resultMonth.contains(mContext.getString(R.string.month_Jul))) {
                    return resultMonth.replace(mContext.getString(R.string.month_Jul), dictonary.getMonth_jul());
                } else if (resultMonth.contains(mContext.getString(R.string.month_Aug))) {
                    return resultMonth.replace(mContext.getString(R.string.month_Aug), dictonary.getMonth_aug());
                } else if (resultMonth.contains(mContext.getString(R.string.month_Sep))) {
                    return resultMonth.replace(mContext.getString(R.string.month_Sep), dictonary.getMonth_sep());
                } else if (resultMonth.contains(mContext.getString(R.string.month_Oct))) {
                    return resultMonth.replace(mContext.getString(R.string.month_Oct), dictonary.getMonth_oct());
                } else if (resultMonth.contains(mContext.getString(R.string.month_Nov))) {
                    return resultMonth.replace(mContext.getString(R.string.month_Nov), dictonary.getMonth_nov());
                } else if (resultMonth.contains(mContext.getString(R.string.month_Dec))) {
                    return resultMonth.replace(mContext.getString(R.string.month_Dec), dictonary.getMonth_dec());
                } else {
                    Toast.makeText(itemView.getContext(), mContext.getString(R.string.error_data), Toast.LENGTH_SHORT).show();
                }
            }
            return resultMonth;
        }

        //Data format
        private String convertTimeFromUnix(String unix_time, String time_zone)
                throws NullPointerException, IllegalArgumentException {
            String result;
            long time = Long.valueOf(unix_time);
            String TIMEZONE = mContext.getString(R.string.time_GMT) + time_zone;
            Date date = new Date(time * 1000);
            SimpleDateFormat simpleDayFormat = new SimpleDateFormat(mContext.getString(R.string.time_hrFormat), Locale.ENGLISH);
            simpleDayFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE));
            result = simpleDayFormat.format(date);

            return result;
        }

        //Data format
        private String convertDateFromUnix(String unix_time, String time_zone)
                throws NullPointerException, IllegalArgumentException {
            String result;
            long time = Long.valueOf(unix_time);
            String TIMEZONE = mContext.getString(R.string.time_GMT) + time_zone;
            Date date = new Date(time * 1000);
            SimpleDateFormat simpleDayFormat = new SimpleDateFormat(mContext.getString(R.string.time_Date), Locale.ENGLISH);
            simpleDayFormat.setTimeZone(TimeZone.getTimeZone(TIMEZONE));
            result = simpleDayFormat.format(date);

            return result;
        }
    }

}
