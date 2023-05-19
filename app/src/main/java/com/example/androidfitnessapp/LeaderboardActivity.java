package com.example.androidfitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

import com.example.androidfitnessapp.classes.FbHelper;
import com.example.androidfitnessapp.classes.User;
import android.content.SharedPreferences;
import android.content.Context;

public class LeaderboardActivity extends AppCompatActivity implements FbHelper.OnUserLoadedListener {


    User highestFitnessUser;
    private String firstName;
    private String lastName;
    private int fitnessScore;
    private String firstName2;
    private String lastName2;
    private int fitnessScore2;
    private String firstName3;
    private String lastName3;
    private int fitnessScore3;
    private String firstName4;
    private String lastName4;
    private int fitnessScore4;
    private String firstName5;
    private String lastName5;
    private int fitnessScore5;





    private TextView visibleStreak;
    private SharedPreferences sharedPreferences;
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




        // Retrieve the users with the highest fitness scores
        fbHelper.getHighestFitnessUser(this);









        // retrieve current user and submit to the current user tab
        //SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);

        String firstName = sharedPreferences.getString("firstName", "");
        String lastName = sharedPreferences.getString("lastName", "");
        TextView currentUser = findViewById(R.id.currentUser);
        TextView currentUser1b = findViewById(R.id.currentUser1b);

        // Retrieve the streakCount value from SharedPreferences and update all 3 values.
        int streakLeaderBoard = sharedPreferences.getInt("streakCount", 66);
        TextView visibleStreak = findViewById(R.id.currentUserScore);
        visibleStreak.setText(String.valueOf(streakLeaderBoard));
        currentUser.setText(firstName);
        currentUser1b.setText(lastName);

    }



    @Override
    public void onUserLoaded(User user) {
        // every time a user is found, it goes through this, filling out the leaderboard
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

                //starts the loop again looking for second
                fbHelper.getSecondHighestFitnessUser(this);

            } else if (firstName2 == null || lastName2 == null || fitnessScore2 == 0) {
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

                //starts the loop again looking for third
                fbHelper.getThirdHighestFitnessUser(this);
            } else if (firstName3 == null || lastName3 == null || fitnessScore3 == 0) {
                // Update the TextViews with the details of the third user
                firstName3 = user.getFirstName();
                lastName3 = user.getLastName();
                fitnessScore3 = user.getFitnessScore();

                TextView nameTextView3 = findViewById(R.id.name3);
                nameTextView3.setText(firstName3);

                TextView nameTextView3b = findViewById(R.id.name3b);
                nameTextView3b.setText(lastName3);

                TextView scoreTextView3 = findViewById(R.id.score3);
                scoreTextView3.setText(String.valueOf(fitnessScore3));

                //starts the loop again looking for fourth
                fbHelper.getFourthHighestFitnessUser(this);
            } else if (firstName4 == null || lastName4 == null || fitnessScore4 == 0) {
                // Update the TextViews with the details of the fourth user
                firstName4 = user.getFirstName();
                lastName4 = user.getLastName();
                fitnessScore4 = user.getFitnessScore();

                TextView nameTextView4 = findViewById(R.id.name4);
                nameTextView4.setText(firstName4);

                TextView nameTextView4b = findViewById(R.id.name4b);
                nameTextView4b.setText(lastName4);

                TextView scoreTextView4 = findViewById(R.id.score4);
                scoreTextView4.setText(String.valueOf(fitnessScore4));

                //starts the loop again looking for fifth
                fbHelper.getFifthHighestFitnessUser(this);
            } else {
                // Update the TextViews with the details of the fifth user
                firstName5 = user.getFirstName();
                lastName5 = user.getLastName();
                fitnessScore5 = user.getFitnessScore();

                TextView nameTextView5 = findViewById(R.id.name5);
                nameTextView5.setText(firstName5);

                TextView nameTextView5b = findViewById(R.id.name5b);
                nameTextView5b.setText(lastName5);

                TextView scoreTextView5 = findViewById(R.id.score5);
                scoreTextView5.setText(String.valueOf(fitnessScore5));
            }
        }
    }
}