package com.sksanwar.cricketbangla.UI;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import android.widget.TextView;
import android.widget.Toast;

import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sksanwar.cricketbangla.Activities.LiveMatchDetailsActivity;
import com.sksanwar.cricketbangla.Adapters.AdapterLiveMatches;
import com.sksanwar.cricketbangla.FetchData.JsonFetchTask;
import com.sksanwar.cricketbangla.FetchData.ServiceGenerator;
import com.sksanwar.cricketbangla.Pojo.AsyncListner;
import com.sksanwar.cricketbangla.Pojo.DictonaryPojo;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.LiveMatches;
import com.sksanwar.cricketbangla.Pojo.LiveMatchPojo.Match;
import com.sksanwar.cricketbangla.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sksho on 20-Nov-17.
 */

public class MainActivityFragment extends Fragment implements AsyncListner,
        AdapterLiveMatches.ListItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    public static final String LIVE_MATCH_LIST = "live_match_list";
    public static final String POSITION = "position";
    private static final String TAG = MainActivityFragment.class.getSimpleName();

    public static DictonaryPojo dictonary;
    public ArrayList<Match> matchesList;

    @BindView(R.id.rv_livematches)
    RecyclerView recyclerViewLiveMatches;
    @BindView(R.id.swip_to_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.no_network)
    TextView no_network;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDictonaryDatabaseReference;
    private DatabaseReference mMatchListDatabaseReference;

    private AdapterLiveMatches adapterLiveMatches;

    public MainActivityFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.mainactivity_fragment, container, false);
        ButterKnife.bind(this, rootView);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDictonaryDatabaseReference = mFirebaseDatabase.getReference().child("Dictonary");
        mMatchListDatabaseReference = mFirebaseDatabase.getReference().child("matchList");


        no_network.setVisibility(View.GONE);
        swipeRefreshLayout.setOnRefreshListener(this);

        liveMatchDownloadFromJson();
        networkCheck();
        return rootView;
    }

    //Network Checks
    private boolean networkCheck() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public void liveMatchDownloadFromJson() {
        if (networkCheck()) {
            swipeRefreshLayout.setRefreshing(true);
            /**
             * For dictonary data fetching
             */
            JsonFetchTask jsonFetchTask = ServiceGenerator.createService(JsonFetchTask.class);
            Call<DictonaryPojo> call = jsonFetchTask.dictonaryForCricket();
            /**
             * AsyncTask for Dictonary
             */
            call.enqueue(new Callback<DictonaryPojo>() {
                @Override
                public void onResponse(Call<DictonaryPojo> call, Response<DictonaryPojo> response) {
                    DictonaryPojo pojo = response.body();
                    if (pojo != null) {
                        dictonary = pojo;
                        mDictonaryDatabaseReference.push().setValue(dictonary);
                    } else {
                        Toast.makeText(getContext(), "There is an error loading data", Toast.LENGTH_SHORT).show();
                    }
                    Log.d(TAG, "Dictonary Data:  " + dictonary);
                }

                @Override
                public void onFailure(Call<DictonaryPojo> call, Throwable t) {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            });

            /**
             * For Live Match Data Fetching
             */
            Call<LiveMatches> liveMatchesCall = jsonFetchTask.liveMatch();
            liveMatchesCall.enqueue(new Callback<LiveMatches>() {
                @Override
                public void onResponse(Call<LiveMatches> call, Response<LiveMatches> response) {
                    LiveMatches liveMatches = response.body();
                    matchesList = liveMatches.getMatches();
                    if (matchesList != null) {
                        mMatchListDatabaseReference.push().setValue(matchesList);
                        loadViews(matchesList);
                    }
                    Log.d(TAG, "Match List: " + matchesList);
                }

                @Override
                public void onFailure(Call<LiveMatches> call, Throwable t) {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            no_network.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(LIVE_MATCH_LIST)) {
                matchesList = savedInstanceState.getParcelable(LIVE_MATCH_LIST);
            }
        }
        //this condition checks if the matchesList is null thn run the task
        if (matchesList != null && dictonary != null) {
            loadViews(matchesList);
        } else {
            return;
        }
    }

    private void loadViews(ArrayList<Match> matchList) {
        recyclerViewLiveMatches.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapterLiveMatches = new AdapterLiveMatches(this, matchList);
        swipeRefreshLayout.setRefreshing(false);
        recyclerViewLiveMatches.setAdapter(adapterLiveMatches);

        // add pager behavior
        SnapHelper snapHelper = new GravitySnapHelper(Gravity.START);
        snapHelper.attachToRecyclerView(recyclerViewLiveMatches);

        // pager indicator
        recyclerViewLiveMatches.addItemDecoration(new LinePagerIndicatorDecoration());
    }


    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(getContext(), LiveMatchDetailsActivity.class);
        intent.putParcelableArrayListExtra(LIVE_MATCH_LIST, matchesList);
        intent.putExtra(POSITION, clickedItemIndex);
        startActivity(intent);

        Toast.makeText(getContext(), "Match id: " + matchesList.get(clickedItemIndex).getMatch_id(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void returnLiveMatchList(ArrayList<Match> matchList) {
        matchesList = matchList;
        loadViews(matchesList);
    }

    @Override
    public void onRefresh() {
        liveMatchDownloadFromJson();
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
