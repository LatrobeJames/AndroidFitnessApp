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

    public void getThirdHighestFitnessUser(final OnUserLoadedListener listener) {
        Query query = usersRef.orderByChild("fitnessScore").limitToLast(3);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<User> userList = new ArrayList<>();
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    userList.add(childSnapshot.getValue(User.class));
                }

                if (userList.size() >= 3) {
                    User thirdHighestUser = userList.get(0);
                    User secondHighestUser = userList.get(1);
                    User highestUser = userList.get(2);
                    listener.onUserLoaded(thirdHighestUser);
                } else {
                    listener.onUserLoaded(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Failed to retrieve third highest fitness user: " + databaseError.getMessage());
                listener.onUserLoaded(null);
            }
        });
    }


    public void getFourthHighestFitnessUser(final OnUserLoadedListener listener) {
        Query query = usersRef.orderByChild("fitnessScore").limitToLast(4);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<User> userList = new ArrayList<>();
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    userList.add(childSnapshot.getValue(User.class));
                }

                if (userList.size() >= 4) {
                    User fourthHighestUser = userList.get(0);
                    User thirdHighestUser = userList.get(1);
                    User secondHighestUser = userList.get(2);
                    User highestUser = userList.get(3);
                    listener.onUserLoaded(fourthHighestUser);
                } else {
                    listener.onUserLoaded(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Failed to retrieve fourth highest fitness user: " + databaseError.getMessage());
                listener.onUserLoaded(null);
            }
        });
    }


    public void getFifthHighestFitnessUser(final OnUserLoadedListener listener) {
        Query query = usersRef.orderByChild("fitnessScore").limitToLast(5);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<User> userList = new ArrayList<>();
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    userList.add(childSnapshot.getValue(User.class));
                }

                if (userList.size() >= 5) {
                    User fifthHighestUser = userList.get(0);
                    User fourthHighestUser = userList.get(1);
                    User thirdHighestUser = userList.get(2);
                    User secondHighestUser = userList.get(3);
                    User highestUser = userList.get(4);
                    listener.onUserLoaded(fifthHighestUser);
                } else {
                    listener.onUserLoaded(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Failed to retrieve fifth highest fitness user: " + databaseError.getMessage());
                listener.onUserLoaded(null);
            }
        });
    }





    public void createUser(String userId, String firstName, String lastName, int fitnessScore) {
        User user = new User(firstName, lastName, fitnessScore);
        usersRef.child(userId).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "User updated successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating user: " + e.getMessage());
                    }
                });
    }

    public void updateUser(String userId, String firstName, String lastName, int fitnessScore) {
        DatabaseReference userRef = usersRef.child(userId);
        userRef.child("firstName").setValue(firstName);
        userRef.child("lastName").setValue(lastName);
        userRef.child("fitnessScore").setValue(fitnessScore)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "User updated successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating user: " + e.getMessage());
                    }
                });
    }


}