package com.example.onehabbit_app2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.*;

import android.os.Bundle;

import java.time.DayOfWeek;

public class MainActivity extends AppCompatActivity {
    private ListFragmentViewModel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewmodel = new ViewModelProvider(this).get(ListFragmentViewModel.class);
        if(savedInstanceState == null) {
            transList();
        }
    }

    void transList() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new ListFragment())
                .commit();
    }
    void editSchedule(Schedule schedule) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, EditFragment.newInstance(schedule))
                .addToBackStack(null)
                .commit();
    }
    void regist(Schedule schedule) {
        viewmodel.regist(schedule);
        getSupportFragmentManager().popBackStack();
    }
    void cancelEdit() {
        getSupportFragmentManager().popBackStack();
    }
}