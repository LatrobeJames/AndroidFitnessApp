package com.example.androidfitnessapp.classes;

import android.util.Log;

import androidx.annotation.NonNull;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import java.util.ArrayList;
import java.util.List;
import com.google.firebase.database.Query;
import java.util.Map;
import java.util.HashMap;

public class FbHelper {
    private DatabaseReference usersRef; // Declare the usersRef as an instance variable

    private String TAG = "FireStore";

    public FbHelper() {
        // Assuming you have a DatabaseReference for "users" in the Realtime Database
        usersRef = FirebaseDatabase.getInstance("https://fitness-app-7d01d-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference()
                .child("users");
    }

    public interface OnUserLoadedListener {
        void onUserLoaded(User user);
    }


    public void getHighestFitnessUser(final OnUserLoadedListener listener) {
        Query query = usersRef.orderByChild("fitnessScore").limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = null;
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    user = childSnapshot.getValue(User.class);
                }
                listener.onUserLoaded(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Failed to retrieve highest fitness user: " + databaseError.getMessage());
                listener.onUserLoaded(null);
            }
        });
    }




    public void getSecondHighestFitnessUser(final OnUserLoadedListener listener) {
        Query query = usersRef.orderByChild("fitnessScore").limitToLast(2);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<User> userList = new ArrayList<>();
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    userList.add(childSnapshot.getValue(User.class));
                }

                if (userList.size() >= 2) {
                    User secondHighestUser = userList.get(0);
                    User highestUser = userList.get(1);
                    listener.onUserLoaded(secondHighestUser);
                } else {
                    listener.onUserLoaded(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Failed to retrieve second highest fitness user: " + databaseError.getMessage());
                listener.onUserLoaded(null);
            }
        });
    }






    public void createUser(String firstName, String lastName, int fitnessScore) {
        String userId = usersRef.push().getKey();

        User user = new User(firstName, lastName, fitnessScore);
        usersRef.child(userId).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "User created successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error creating user: " + e.getMessage());
                    }
                });
    }
}