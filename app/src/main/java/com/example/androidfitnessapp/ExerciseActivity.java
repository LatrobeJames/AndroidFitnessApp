package com.example.androidfitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Handler;
import android.widget.Toast;
import java.util.Date;
import com.example.androidfitnessapp.classes.FbHelper;
import java.util.UUID;


public class ExerciseActivity extends AppCompatActivity {

    private Button backButtonEX;
    private Button streak;
    private TextView visibleStreak;
    private TextView exercise1Counter;
    private TextView exercise2Counter;
    private TextView exercise3Counter;
    private TextView exercise4Counter;
    private int streakCount;
    private int normalExerciseCount;
    private int easyExerciseCount;
    private SharedPreferences sharedPreferences;
    private boolean canIncrementStreak = true;
    private Handler handler;
    private Runnable delayRunnable;
    private long lastButtonClickTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        // call design elements from xml
        backButtonEX = findViewById(R.id.backButtonEX);
        streak = findViewById(R.id.streak);
        visibleStreak = findViewById(R.id.visibleStreak);
        exercise1Counter = findViewById(R.id.exercise1Counter);
        exercise2Counter = findViewById(R.id.exercise2Counter);
        exercise3Counter = findViewById(R.id.exercise3Counter);
        exercise4Counter = findViewById(R.id.exercise4Counter);

        // call variables from those defined in this class
        sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
        streakCount = sharedPreferences.getInt("streakCount", 0);
        normalExerciseCount = sharedPreferences.getInt("normalExerciseCount", 1);
        easyExerciseCount = sharedPreferences.getInt("easyExerciseCount", 3);
        lastButtonClickTime = sharedPreferences.getLong("lastButtonClickTime", 0);

        // set variables for the first time or when recalled after onCreate() and onDestroy()
        visibleStreak.setText(String.valueOf(streakCount));
        exercise1Counter.setText(String.valueOf(normalExerciseCount));
        exercise2Counter.setText(String.valueOf(normalExerciseCount));
        exercise3Counter.setText(String.valueOf(easyExerciseCount));
        exercise4Counter.setText(String.valueOf(easyExerciseCount));

        backButtonEX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //handler that resets the streak based on time. Since the timer is cooked, this is not being called with timers at all
        handler = new Handler();
        delayRunnable = new Runnable() {
            @Override
            public void run() {
                resetValuesToDefault();
                Toast.makeText(ExerciseActivity.this, "Time expired! Values reset to default.", Toast.LENGTH_SHORT).show();
            }
        };

        // Increment streak and exercises
        streak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long currentTime = new Date().getTime();
                long elapsedTime = currentTime - lastButtonClickTime;

                if (canIncrementStreak && elapsedTime >= 30000) {
                    canIncrementStreak = false; // Disable further button presses
                    streakCount++;
                    normalExerciseCount++;
                    easyExerciseCount++;

                    exercise1Counter.setText(String.valueOf(normalExerciseCount));
                    exercise2Counter.setText(String.valueOf(normalExerciseCount));
                    exercise3Counter.setText(String.valueOf(easyExerciseCount));
                    exercise4Counter.setText(String.valueOf(easyExerciseCount));

                    visibleStreak.setText(String.valueOf(streakCount));




                    saveData(currentTime);

                    handler.postDelayed(delayRunnable, 30000); // 30 seconds delay before reset
                }
                  // This was supposed to reset the streak but I ran into too many problems for now
                  //  else if (elapsedTime >= 60000) {
                  //  resetValuesToDefault();
                  //  Toast.makeText(ExerciseActivity.this, "Time expired! Values reset to default.", Toast.LENGTH_SHORT).show() }

                // I don't know why but the exercise page needs to be closed and re-opened for this toast to show
                // else if (elapsedTime < 30000) {
                //    long remainingTime = 30000 - elapsedTime;
                //    Toast.makeText(ExerciseActivity.this, "You're too early! Button can be pressed again in " + remainingTime / 1000 + " seconds.", Toast.LENGTH_SHORT).show();
                // }
            }
        });

    }
    // SharedPreferences saves data to allow for local storage to be used
    private void saveData(long currentTime) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("streakCount", streakCount);
        editor.putInt("normalExerciseCount", normalExerciseCount);
        editor.putInt("easyExerciseCount", easyExerciseCount);
        editor.putLong("lastButtonClickTime", currentTime);

        //grab user details
        String firstName = sharedPreferences.getString("firstName", "");
        String lastName = sharedPreferences.getString("lastName", "");
        String userId = sharedPreferences.getString( "userId", "4");
        FbHelper fbHelper = new FbHelper();

        //create new user if one doesn't exist otherwise, update existing user
        if (userId.equals("4")){
            userId = UUID.randomUUID().toString();
            editor.putString("userId", userId);
            fbHelper.createUser(userId, firstName, lastName, streakCount);

        }else {
            fbHelper.updateUser(userId, firstName, lastName, streakCount);
        }


        editor.apply();







        editor.apply();


    }

    // Function that resets all values when streak is reset
    private void resetValuesToDefault() {
//        streakCount = 0;
//        normalExerciseCount = 1;
//        easyExerciseCount = 3;

 //       exercise1Counter.setText(String.valueOf(normalExerciseCount));
 //       exercise2Counter.setText(String.valueOf(normalExerciseCount));
 //       exercise3Counter.setText(String.valueOf(easyExerciseCount));
 //       exercise4Counter.setText(String.valueOf(easyExerciseCount));

  //      visibleStreak.setText(String.valueOf(streakCount));
  //      saveData(0); // Reset last button click time to 0
  //      canIncrementStreak = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // handler.removeCallbacks(delayRunnable);
    }
}