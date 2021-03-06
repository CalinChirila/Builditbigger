package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public static InterstitialAd mInterstitialAd;
    public static ProgressBar mProgressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mProgressBar = root.findViewById(R.id.progress_bar);

        configureInterstitialAd();

        // Set up the tell joke button
        Button jokeButton = root.findViewById(R.id.joke_button);
        jokeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showInterstitialAd();
                retrieveJokeFromGCE();
            }
        });

        AdView mAdView = root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }


    public void retrieveJokeFromGCE(){
        EndpointsAsyncTask task = new EndpointsAsyncTask(getContext());
        task.execute();
    }

    private void configureInterstitialAd(){
        Context context = getContext();
        if(context != null) {
            String interstitialAdId = context.getResources().getString(R.string.interstitial_ad_unit_id);
            MobileAds.initialize(context, interstitialAdId);
            mInterstitialAd = new InterstitialAd(context);
            mInterstitialAd.setAdUnitId(interstitialAdId);
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }
    }

    public void showInterstitialAd(){
        if(mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }
    }

}
