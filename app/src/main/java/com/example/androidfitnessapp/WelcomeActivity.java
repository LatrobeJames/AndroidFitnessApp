package com.example.androidfitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.ApiException;
import android.util.Log;
import android.view.Gravity;
import android.content.SharedPreferences;
import android.content.Context;


public class WelcomeActivity extends AppCompatActivity {


    private static final String TAG = "WelcomeActivity";
    ImageButton startButton;
    Button button123;
    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);

        startButton = findViewById(R.id.google);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleSignInHelper();
            }
        });

        // This button skips google SSO. No login but uses local storage for streak
        button123 = findViewById(R.id.start_button);
        button123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName1 = "dev";
                String lastName1 = "account";
                sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("firstName", firstName1);
                editor.putString("lastName", lastName1);
                editor.apply();
                Toast toast =Toast.makeText(WelcomeActivity.this, "Logged in as: " + firstName1 + " " + lastName1, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, -600);
                toast.show();

                Intent intent2 = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent2);
            }
        });


        }

    private void GoogleSignInHelper() {
        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent, 100);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // doing google SSO
        if (requestCode == 100) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    String firstName = account.getGivenName();
                    String lastName = account.getFamilyName();

                    // Use the first and last names as needed
                    Log.d(TAG, "First Name: " + firstName);
                    Log.d(TAG, "Last Name: " + lastName);

                    // Pass the names to the MainActivity
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    intent.putExtra("firstName", firstName);
                    intent.putExtra("lastName", lastName);

                    // moves the toast to upper centre and gives it the name of the google sso
                    Toast toast =Toast.makeText(this, "Logged in as: " + firstName + " " + lastName, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, -600);
                    toast.show();

                    // puts the google SSO names into the shared preferences
                    sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("firstName", firstName);
                    editor.putString("lastName", lastName);
                    editor.apply();

                    //starts the new activity (main activity)
                    startActivity(intent);
                }
            } catch (ApiException e) {
                String errorMessage = e.getMessage();
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
