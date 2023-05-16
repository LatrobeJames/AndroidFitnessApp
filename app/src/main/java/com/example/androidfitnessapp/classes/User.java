package com.example.androidfitnessapp.classes;

public class User {
    private String Name;
    private int Score;

    public User(String name, int score) {
        Name = name;
        Score = score;
    }

    public User() {}

    public String getName() { return Name; }
    public void SetName(String name) {Name = name;}

    public int getScore() { return Score;}
    public void setScore(int score) { Score = score; }

    @Override
    public String toString() {
        return "User{" +
                "Name=' " + Name + '\'' +
                ", Score='" + Score + '\'' +
                '}';
    }
}