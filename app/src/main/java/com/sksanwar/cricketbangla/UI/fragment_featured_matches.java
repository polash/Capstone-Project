package com.sksanwar.cricketbangla.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sksanwar.cricketbangla.R;

/**
 * Created by sksho on 15-Nov-17.
 */

public class fragment_featured_matches extends Fragment {
    public fragment_featured_matches() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_pager_featured_matches, container, false);

        CardView cardView = rootView.findViewById(R.id.matches_card_view);







        return rootView;
    }
}
