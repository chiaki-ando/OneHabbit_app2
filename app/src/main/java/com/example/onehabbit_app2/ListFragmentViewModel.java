package com.example.onehabbit_app2;

import androidx.lifecycle.*;

import java.time.DayOfWeek;
import java.util.*;

public class ListFragmentViewModel extends ViewModel {
    private MutableLiveData<DayOfWeek> dayOfWeekLiveData = new MutableLiveData<>(DayOfWeek.MONDAY);
    LiveData<DayOfWeek> getDayOfWeek() { return dayOfWeekLiveData; }
    void setDayOfWeek(DayOfWeek value) { dayOfWeekLiveData.setValue(value); }

    private MutableLiveData<List<Schedule>> ScheduleListLiveData = new MutableLiveData<>(new ArrayList<>());
    LiveData<List<Schedule>> getScheduleList() { return ScheduleListLiveData; }
    void regist(Schedule schedule) {
        List<Schedule> list = ScheduleListLiveData.getValue();
        for(int i=0; i<list.size(); i++) {
            Schedule item = list.get(i);
            if(item.dayOfWeek == schedule.dayOfWeek && item.startHour == schedule.startHour) {
                list.remove(item);
                break;
            }
        }
        list.add(schedule);
        ScheduleListLiveData.setValue(list); //notify
    }
}
