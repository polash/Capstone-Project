package com.sksanwar.cricketbangla.UI;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
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
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Toast;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.sksanwar.cricketbangla.Activities.RecentMatchDetailsActivity;
import com.sksanwar.cricketbangla.Adapters.AdapterRecentMatches;
import com.sksanwar.cricketbangla.FetchData.JsonFetchTask;
import com.sksanwar.cricketbangla.FetchData.ServiceGenerator;
import com.sksanwar.cricketbangla.Pojo.AsyncListner;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Match;
import com.sksanwar.cricketbangla.Pojo.RecentMatchPojo.RecentMatches;
import com.sksanwar.cricketbangla.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecentMatchActivityFragment extends Fragment implements AsyncListner,
        AdapterRecentMatches.ListItemClickListener {

    public static final String RECENT_MATCH_LIST = "recent_match_list";
    public static final String POSITION = "position";
    private static final String TAG = RecentMatchActivityFragment.class.getSimpleName();
    @BindView(R.id.rv_livematches)
    RecyclerView recyclerViewLiveMatches;

    private ArrayList<Match> recentMatchesList;

    private AdapterRecentMatches adapterRecentMatches;

    public RecentMatchActivityFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mainactivity_fragment, container, false);
        ButterKnife.bind(this, rootView);

        recentMatchDownloadFromJson();

        return rootView;
    }

    private void recentMatchDownloadFromJson() {
        /**
         * For Live Match Data Fetching
         */
        JsonFetchTask jsonFetchTask = ServiceGenerator.createService(JsonFetchTask.class);
        Call<RecentMatches> recentMatchesCall = jsonFetchTask.recentMatches();
        recentMatchesCall.enqueue(new Callback<RecentMatches>() {
            @Override
            public void onResponse(Call<RecentMatches> call, Response<RecentMatches> response) {
                RecentMatches recentMatches = response.body();
                recentMatchesList = recentMatches.getMatches();
                if (recentMatchesList != null) {
                    loadViews(recentMatchesList);
                }
                Log.d(TAG, "Match List: " + recentMatchesList);
            }

            @Override
            public void onFailure(Call<RecentMatches> call, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadViews(ArrayList<Match> recentMatchesList) {
        recyclerViewLiveMatches.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewLiveMatches.setHasFixedSize(true);
        adapterRecentMatches = new AdapterRecentMatches(this, recentMatchesList);
        recyclerViewLiveMatches.setAdapter(adapterRecentMatches);

        // add pager behavior
        SnapHelper snapHelper = new GravitySnapHelper(Gravity.START);
        snapHelper.attachToRecyclerView(recyclerViewLiveMatches);

        // pager indicator
        recyclerViewLiveMatches.addItemDecoration(new LinePagerIndicatorDecoration());
    }


    @Override
    public void returnMatchList(ArrayList<Match> matcheList) {
        recentMatchesList = matcheList;
        loadViews(recentMatchesList);

    }


    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(getContext(), RecentMatchDetailsActivity.class);
        intent.putParcelableArrayListExtra(RECENT_MATCH_LIST, recentMatchesList);
        intent.putExtra(POSITION, clickedItemIndex);
        startActivity(intent);
    }


    private class LinePagerIndicatorDecoration extends RecyclerView.ItemDecoration {

        private final float DP = Resources.getSystem().getDisplayMetrics().density;
        /**
         * Height of the space the indicator takes up at the bottom of the view.
         */
        private final int mIndicatorHeight = (int) (DP * 16);
        /**
         * Indicator stroke width.
         */
        private final float mIndicatorStrokeWidth = DP * 2;
        /**
         * Indicator width.
         */
        private final float mIndicatorItemLength = DP * 16;
        /**
         * Padding between indicators.
         */
        private final float mIndicatorItemPadding = DP * 4;
        /**
         * Some more natural animation interpolation
         */
        private final Interpolator mInterpolator = new AccelerateDecelerateInterpolator();
        private final Paint mPaint = new Paint();

        private int colorActive = getContext().getResources().getColor(R.color.color_black);
        private int colorInactive = getContext().getResources().getColor(R.color.color_white);

        public LinePagerIndicatorDecoration() {
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mPaint.setStrokeWidth(mIndicatorStrokeWidth);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setAntiAlias(true);
        }


        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);

            int itemCount = parent.getAdapter().getItemCount();

            // center horizontally, calculate width and subtract half from center
            float totalLength = mIndicatorItemLength * itemCount;
            float paddingBetweenItems = Math.max(0, itemCount - 1) * mIndicatorItemPadding;
            float indicatorTotalWidth = totalLength + paddingBetweenItems;
            float indicatorStartX = (parent.getWidth() - indicatorTotalWidth) / 2F;

            // center vertically in the allotted space
            float indicatorPosY = parent.getHeight() - mIndicatorHeight / 2F;

            drawInactiveIndicators(c, indicatorStartX, indicatorPosY, itemCount);


            // find active page (which should be highlighted)
            LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
            int activePosition = layoutManager.findFirstVisibleItemPosition();
            if (activePosition == RecyclerView.NO_POSITION) {
                return;
            }

            // find offset of active page (if the user is scrolling)
            final View activeChild = layoutManager.findViewByPosition(activePosition);
            int left = activeChild.getLeft();
            int width = activeChild.getWidth();

            // on swipe the active item will be positioned from [-width, 0]
            // interpolate offset for smooth animation
            float progress = mInterpolator.getInterpolation(left * -1 / (float) width);

            drawHighlights(c, indicatorStartX, indicatorPosY, activePosition, progress, itemCount);
        }

        private void drawInactiveIndicators(Canvas c, float indicatorStartX, float indicatorPosY, int itemCount) {
            mPaint.setColor(colorInactive);

            // width of item indicator including padding
            final float itemWidth = mIndicatorItemLength + mIndicatorItemPadding;

            float start = indicatorStartX;
            for (int i = 0; i < itemCount; i++) {
                // draw the line for every item
                c.drawLine(start, indicatorPosY, start + mIndicatorItemLength, indicatorPosY, mPaint);
                start += itemWidth;
            }
        }

        private void drawHighlights(Canvas c, float indicatorStartX, float indicatorPosY,
                                    int highlightPosition, float progress, int itemCount) {
            mPaint.setColor(colorActive);

            // width of item indicator including padding
            final float itemWidth = mIndicatorItemLength + mIndicatorItemPadding;

            if (progress == 0F) {
                // no swipe, draw a normal indicator
                float highlightStart = indicatorStartX + itemWidth * highlightPosition;
                c.drawLine(highlightStart, indicatorPosY,
                        highlightStart + mIndicatorItemLength, indicatorPosY, mPaint);
            } else {
                float highlightStart = indicatorStartX + itemWidth * highlightPosition;
                // calculate partial highlight
                float partialLength = mIndicatorItemLength * progress;

                // draw the cut off highlight
                c.drawLine(highlightStart + partialLength, indicatorPosY,
                        highlightStart + mIndicatorItemLength, indicatorPosY, mPaint);

                // draw the highlight overlapping to the next item as well
                if (highlightPosition < itemCount - 1) {
                    highlightStart += itemWidth;
                    c.drawLine(highlightStart, indicatorPosY,
                            highlightStart + partialLength, indicatorPosY, mPaint);
                }
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.bottom = mIndicatorHeight;
        }
    }
}
