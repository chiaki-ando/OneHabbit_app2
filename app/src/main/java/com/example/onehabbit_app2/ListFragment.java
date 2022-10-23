package com.example.onehabbit_app2;

import android.os.Bundle;
import android.view.*;
import android.widget.*;

import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.*;

public class ListFragment extends Fragment {
    ListFragment() {
        super(R.layout.fragment_list);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListFragmentViewModel viewmodel = new ViewModelProvider(requireActivity()).get(ListFragmentViewModel.class);

        ScheduleAdapter adapter = new ScheduleAdapter();
        viewmodel.getScheduleList().observe(getViewLifecycleOwner(), scheduleList -> adapter.put(scheduleList));
        viewmodel.getDayOfWeek().observe(getViewLifecycleOwner(), dayOfWeek -> adapter.setTarget(dayOfWeek));

        ListView listView = view.findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, v, position, id) ->
                ((MainActivity)requireActivity()).editSchedule((Schedule)listView.getItemAtPosition(position))
        );

        TextView label = view.findViewById(R.id.headerLabel);
        viewmodel.getDayOfWeek().observe(getViewLifecycleOwner(), dayOfWeek ->
                label.setText(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()))
        );

        Button prevButton = view.findViewById(R.id.prevButton);
        prevButton.setOnClickListener(v -> viewmodel.setDayOfWeek(viewmodel.getDayOfWeek().getValue().minus(1)));
        Button nextButton = view.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> viewmodel.setDayOfWeek(viewmodel.getDayOfWeek().getValue().plus(1)));
    }
}

class Schedule implements Serializable {
    final DayOfWeek dayOfWeek;
    final int startHour; //0～23
    final String title, contents;

    Schedule(@NonNull DayOfWeek dayOfWeek, int startHour, String title, String contents) {
        this.dayOfWeek = dayOfWeek;
        if(startHour < 0 || 23 < startHour) throw new IllegalArgumentException("startHour=" + startHour);
        this.startHour = startHour;
        this.title = title == null ? "" : title;
        this.contents = contents == null ? "" : contents;
    }

    Schedule replace(String title, String contents) {
        return new Schedule(dayOfWeek, startHour, title, contents);
    }
}

class ScheduleAdapter extends BaseAdapter {
    private static class ViewHolder {
        final TextView times, title;
        ViewHolder(View itemView) {
            times = itemView.findViewById(R.id.times);
            title = itemView.findViewById(R.id.title);
        }
    }

    private Map<DayOfWeek,Map<Integer,Schedule>> scheduleMap = new HashMap<>();
    private DayOfWeek target = DayOfWeek.MONDAY;

    void setTarget(DayOfWeek dayOfWeek) {
        target = dayOfWeek;
        notifyDataSetChanged();
    }

    void put(List<Schedule> scheduleList) {
        scheduleMap.clear();
        for(Schedule schedule : scheduleList) put(schedule);
    }
    void put(Schedule schedule) {
        Map<Integer,Schedule> map = scheduleMap.get(schedule.dayOfWeek);
        if(map == null) {
            map = new HashMap<>();
            scheduleMap.put(schedule.dayOfWeek, map);
        }
        map.put(schedule.startHour, schedule);
        notifyDataSetChanged();
    }

    boolean contains(DayOfWeek dayOfWeek, int startHour) {
        Map<Integer,Schedule> map = scheduleMap.get(dayOfWeek);
        if(map == null) return false;
        return map.containsKey(startHour);
    }

    @Override
    public int getCount() { return 24; }
    @Override
    public Object getItem(int position) {
        return contains(target, position) ? scheduleMap.get(target).get(position) : new Schedule(target, position, "", "");
    }
    @Override
    public long getItemId(int position) { return position; }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder vh = (ViewHolder)convertView.getTag();

        vh.times.setText(position + ":00");
        String title = contains(target, position) ? scheduleMap.get(target).get(position).title : "";
        vh.title.setText(title);

        return convertView;
    }
}