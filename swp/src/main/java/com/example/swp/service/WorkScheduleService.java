package com.example.swp.service;

import com.example.swp.entity.WorkingSchedule;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface WorkScheduleService {
    List<WorkingSchedule> getByWorkingDate(LocalDate date);
    WorkingSchedule save(WorkingSchedule w);
}
