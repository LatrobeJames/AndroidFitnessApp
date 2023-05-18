package com.example.androidfitnessapp.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {
    private MutableLiveData<String> dashboardText;

    // Displays text which can be edited from here
    public DashboardViewModel() {
        dashboardText = new MutableLiveData<>();
        dashboardText.setValue("This was supposed to be a friends page but have a picture of Benji instead :)");
    }

    public LiveData<String> getDashboardText() {
        return dashboardText;
    }
}
