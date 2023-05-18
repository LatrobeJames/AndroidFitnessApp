package com.example.androidfitnessapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.androidfitnessapp.ExerciseActivity;
import com.example.androidfitnessapp.LeaderboardActivity;
import com.example.androidfitnessapp.R;
import com.example.androidfitnessapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //In order to have a rotated button, these coded in bindings allow for it to exist without rendering errors
        Button startButton = binding.startButton;
        startButton.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.black));

        Button leaderboardButton = binding.leaderboardButton;

        leaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLeaderboardActivity();
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExerciseActivity();
            }
        });

        return root;
    }

    private void openLeaderboardActivity() {
        Intent intent = new Intent(requireContext(), LeaderboardActivity.class);
        startActivity(intent);
    }

    private void openExerciseActivity() {
        Intent intent = new Intent(requireContext(), ExerciseActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
