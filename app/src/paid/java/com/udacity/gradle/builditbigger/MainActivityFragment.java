package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.androidjokes.AndroidMainActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.builditbigger.JavaJokes.JavaJoker;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public static final String JOKE_EXTRA = "jokeExtra";

    JavaJoker joker = new JavaJoker();
    String joke = joker.getJoke();


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        // Set up the tell joke button
        Button jokeButton = root.findViewById(R.id.joke_button);
        jokeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //showJoke();
                //sendJokeAsIntentExtra();
                retrieveJokeFromGCE();
            }
        });

        return root;
    }

    // Display the joke as a toast message
    public void showJoke(){
        Toast.makeText(getContext(), joke, Toast.LENGTH_LONG).show();
    }

    public void sendJokeAsIntentExtra(){
        Intent intent = new Intent(getContext(), AndroidMainActivity.class);
        intent.putExtra(JOKE_EXTRA, joke);
        startActivity(intent);
    }

    public void retrieveJokeFromGCE(){
        EndpointsAsyncTask task = new EndpointsAsyncTask(getContext());
        task.execute();
    }
}
