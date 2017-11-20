package com.sksanwar.cricketbangla.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Match;
import com.sksanwar.cricketbangla.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sksho on 18-Nov-17.
 */

public class AdapterLiveMatches extends
        RecyclerView.Adapter<AdapterLiveMatches.LiveMatchViewHolder> {

    final private ListItemClickListener mOnClickListener;

    private List<Match> matchList;


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
        @BindView(R.id.team1_flag)
        ImageView team1_flag;
        @BindView(R.id.team2_flag)
        ImageView team2_flag;

        public LiveMatchViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        //Bind the View
        public void bind(int position) {
            if (!matchList.isEmpty()) {
                match_desc.setText(matchList.get(position).getHeader().getMatchDesc());
                series_name.setText(matchList.get(position).getSeriesName());
                match_type.setText(matchList.get(position).getHeader().getType());
                matchstatus.setText(matchList.get(position).getHeader().getStatus());

                team1_country_name.setText(matchList.get(position).getTeam1().getName());
                team2_country_name.setText(matchList.get(position).getTeam2().getName());
//
//                if (matchList.get(position).getBatTeam().getId() != matchList.get(position).getTeam1().getId()){
//                    team1_score.setText(matchList.get(position).getBatTeam().getInnings().get(position).getScore());
//                    team1_wkt.setText((matchList.get(position).getBatTeam().getInnings().get(position).getWkts()));
//                }
//                else {
//                    team2_score.setText(matchList.get(position).getBowTeam().getInnings().get(position).getScore());
//                    team2_wkt.setText((matchList.get(position).getBowTeam().getInnings().get(position).getWkts()));
//                }

                if (matchList.get(position).getTeam1().getFlag().isEmpty()) {
                    team1_flag.setImageResource(R.drawable.ic_launcher_background);
                } else {
                    Picasso.with(itemView.getContext())
                            .load(matchList.get(position).getTeam1().getFlag())
                            .into(team1_flag);
                }

                if (matchList.get(position).getTeam2().getFlag().isEmpty()) {
                    team1_flag.setImageResource(R.drawable.ic_launcher_background);
                } else {
                    Picasso.with(itemView.getContext())
                            .load(matchList.get(position).getTeam2().getFlag())
                            .into(team1_flag);
                }

            }
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
