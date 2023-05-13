package com.example.androidfitnessapp;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.androidfitnessapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);






/////////////////////////////////////////GOOGLE SSO////////////////////////////////
/////////////////////////////////////////GOOGLE SSO////////////////////////////////
/////////////////////////////////////////GOOGLE SSO////////////////////////////////
//        signInRequest = BeginSignInRequest.builder()
//                .setGoogleIdTokenRequestOptions(
//                        BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
//                                .setSupported(true)
//                                // Your server's client ID, not your Android client ID.
//                                .setServerClientId(getString(R.string.your_web_client_id))
//                                // i swear its   "client_id": "107375448509330110640", but wont work
//
//                                // Only show accounts previously used to sign in.
//                                .setFilterByAuthorizedAccounts(true)
//                                .build())
//                .build()
/////////////////////////////////////////GOOGLE SSO////////////////////////////////
/////////////////////////////////////////GOOGLE SSO////////////////////////////////
/////////////////////////////////////////GOOGLE SSO////////////////////////////////





    }

}