package com.example.onehabbit_app2;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.annotation.*;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class EditFragment extends Fragment {
    private static final String PARAM_SCHEDULE = "schedule";

    EditFragment() {
        super(R.layout.fragment_edit);
    }

    static EditFragment newInstance(Schedule schedule) {
        EditFragment f = new EditFragment();
        Bundle args = new Bundle();
        args.putSerializable(PARAM_SCHEDULE, schedule);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Schedule schedule = (Schedule)getArguments().getSerializable(PARAM_SCHEDULE);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(schedule.startHour + ":00 ～ " + (schedule.startHour + 1) + ":00");
        EditText title = view.findViewById(R.id.title);
        title.setText(schedule.title);
        EditText contents = view.findViewById(R.id.contents);
        contents.setText(schedule.contents);

        Button cancelButton = view.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(v -> ((MainActivity)requireActivity()).cancelEdit());
        Button registButton = view.findViewById(R.id.registButton);
        registButton.setOnClickListener(v -> ((MainActivity)requireActivity()).regist(
                schedule.replace(title.getText().toString(), contents.getText().toString())
        ));
    }
}