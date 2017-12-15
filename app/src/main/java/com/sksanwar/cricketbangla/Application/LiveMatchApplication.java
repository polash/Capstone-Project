package com.sksanwar.cricketbangla.Application;

import android.app.Application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by sksho on 15-Dec-17.
 */

public class LiveMatchApplication extends Application {

    //Firebase references
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDictonaryDatabaseReference;
    public static DatabaseReference mMatchListDatabaseReference;

    @Override
    public void onCreate() {
        super.onCreate();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseDatabase.setPersistenceEnabled(true);

        mDictonaryDatabaseReference = mFirebaseDatabase.getReference().child("Dictonary");
        mDictonaryDatabaseReference.keepSynced(true);
        mMatchListDatabaseReference = mFirebaseDatabase.getReference().child("MatchList");
        mMatchListDatabaseReference.keepSynced(true);
    }
}
