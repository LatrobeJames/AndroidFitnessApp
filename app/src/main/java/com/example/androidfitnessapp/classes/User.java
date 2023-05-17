package com.example.androidfitnessapp.classes;

public class User {
    private String firstName;
    private String lastName;
    private int fitnessScore;

    public User(String firstName, String lastName, int fitnessScore) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fitnessScore = fitnessScore;
    }

    public User() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getFitnessScore() {
        return fitnessScore;
    }

    public void setFitnessScore(int fitnessScore) {
        this.fitnessScore = fitnessScore;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fitnessScore=" + fitnessScore +
                '}';
    }
}