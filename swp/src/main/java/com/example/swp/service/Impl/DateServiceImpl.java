package com.example.swp.service.Impl;

import com.example.swp.entity.DateObject;
import com.example.swp.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DateServiceImpl implements DateService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public DateObject get7day() {
        String sql = "SELECT CURDATE() AS end_date, DATE_SUB(CURDATE(), INTERVAL 6 DAY) AS start_date";

        return jdbcTemplate.query(sql, rs -> {
            if (rs.next()) {
                DateObject date = new DateObject();
                date.setStart(rs.getDate("start_date"));
                date.setEnd(rs.getDate("end_date"));
                return date;
            }
            return null;
        });
    }

    @Override
    public int countDayByStartEnd(String start, String end) {
        String sql = "SELECT DATEDIFF(?, ?) + 1";
        Integer days = jdbcTemplate.queryForObject(
                sql,
                new Object[]{end, start},
                Integer.class
        );
        return days != null ? days : 0;
    }
}
