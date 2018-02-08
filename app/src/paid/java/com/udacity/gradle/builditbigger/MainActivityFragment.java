package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public static ProgressBar mProgressBar;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mProgressBar = root.findViewById(R.id.progress_bar);

        // Set up the tell joke button
        Button jokeButton = root.findViewById(R.id.joke_button);
        jokeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                retrieveJokeFromGCE();
            }
        });

        return root;
    }

    public void retrieveJokeFromGCE(){
        EndpointsAsyncTask task = new EndpointsAsyncTask(getContext());
        task.execute();
    }
}
