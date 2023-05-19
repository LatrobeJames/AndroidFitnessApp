package com.example.androidfitnessapp.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidfitnessapp.R;
import com.example.androidfitnessapp.WelcomeActivity;
import com.example.androidfitnessapp.databinding.FragmentSettingsBinding;
import com.google.firebase.auth.FirebaseAuth;
import android.util.Log;


public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SettingsViewModel settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // This was provided as part of the template
        // Repurposed for settings menu
        final TextView textView = binding.textNotifications;
        settingsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        Button returnButton = root.findViewById(R.id.logOut);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                signOut();
                Toast.makeText(requireActivity(), "Successfully Logged Out", Toast.LENGTH_SHORT).show();
                returnToWelcomeActivity();
            }
        });

        return root;
    }

    private void returnToWelcomeActivity() {
        Intent intent = new Intent(requireActivity(), WelcomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        Log.d("SignOut", "User signed out");
        // Add any additional code to handle the sign out
    }
}
