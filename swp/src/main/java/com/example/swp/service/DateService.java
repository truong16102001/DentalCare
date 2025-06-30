package com.example.swp.service;

import com.example.swp.entity.DateObject;
import org.springframework.stereotype.Service;

public interface DateService {
    DateObject get7day();
    int countDayByStartEnd(String start, String end);
}
