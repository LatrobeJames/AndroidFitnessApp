package com.example.androidfitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

import com.example.androidfitnessapp.classes.FbHelper;
import com.example.androidfitnessapp.classes.User;

public class LeaderboardActivity extends AppCompatActivity implements FbHelper.OnUserLoadedListener {


    User highestFitnessUser;
    private String firstName;
    private String lastName;
    private int fitnessScore;
    private String firstName2;
    private String lastName2;
    private int fitnessScore2;

    private Button backButtonLB;
    private static final String TAG = "LeaderboardActivity";
    private FbHelper fbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        fbHelper = new FbHelper();

        backButtonLB = findViewById(R.id.backButtonLB);
        backButtonLB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Retrieve the user with the highest fitness score
        fbHelper.getHighestFitnessUser(this);
        fbHelper.getSecondHighestFitnessUser(this);

    }

    @Override
    public void onUserLoaded(User user) {
        if (user != null) {
            if (firstName == null || lastName == null || fitnessScore == 0) {
                // Update the TextViews with the details of the first user
                firstName = user.getFirstName();
                lastName = user.getLastName();
                fitnessScore = user.getFitnessScore();

                TextView nameTextView1 = findViewById(R.id.name1);
                nameTextView1.setText(firstName);

                TextView nameTextView1b = findViewById(R.id.name1b);
                nameTextView1b.setText(lastName);

                TextView scoreTextView1 = findViewById(R.id.score1);
                scoreTextView1.setText(String.valueOf(fitnessScore));

            } else {
                // Update the TextViews with the details of the second user
                firstName2 = user.getFirstName();
                lastName2 = user.getLastName();
                fitnessScore2 = user.getFitnessScore();

                TextView nameTextView2 = findViewById(R.id.name2);
                nameTextView2.setText(firstName2);

                TextView nameTextView2b = findViewById(R.id.name2b);
                nameTextView2b.setText(lastName2);

                TextView scoreTextView2 = findViewById(R.id.score2);
                scoreTextView2.setText(String.valueOf(fitnessScore2));
            }
        }
    }
}