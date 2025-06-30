package com.example.swp.service.Impl;

import com.example.swp.entity.Booking;
import com.example.swp.entity.Chart;
import com.example.swp.repository.DentalCareCustomRepository;
import com.example.swp.repository.DentalCareRepository;
import com.example.swp.service.DentalCareService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DentalCareServiceImpl implements DentalCareService {
    private DentalCareRepository dentalCareRepository;
    private DentalCareCustomRepository dentalCareCustomRepository;
    private static final int PAGE_SIZE = 6;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<com.example.swp.entity.Service> getServices(String key, String value, String type, int pageIndex) {
        int offset = (pageIndex - 1) * PAGE_SIZE;

        return dentalCareCustomRepository.searchServices(key, value, type, PAGE_SIZE, offset);
    }

    @Override
    public int getTotalPages(String key) {
        int totalCount = dentalCareCustomRepository.countServices(key);
        return (int) Math.ceil((double) totalCount / PAGE_SIZE);
    }

    @Override
    public com.example.swp.entity.Service findById(int serviceId) {
        return dentalCareCustomRepository.findById(serviceId);
    }

    @Override
    public List<com.example.swp.entity.Service> getRelatedServices(int serviceId) {
        return dentalCareCustomRepository.getRelatedServices(serviceId);
    }

    @Override
    public List<Booking> getBookingServicesByUserId(int userId) {
        return dentalCareCustomRepository.findBookingsByUserId(userId);
    }

    @Override
    public List<Booking> getBookingServicesByPhoneNumber(String phoneNumber) {
        return dentalCareCustomRepository.findBookingsByPhoneNumber(phoneNumber);
    }

    @Override
    public List<Chart> getChartRevenueArea(String start, int numberOfDay) {
        List<Chart> list = new ArrayList<>();

        for (int i = 0; i < numberOfDay; i++) {

            String sqlSum = """
    SELECT IFNULL(SUM(total_fee), 0)
    FROM invoices
    WHERE status = 'Paid'
      AND created_date BETWEEN ? AND DATE_ADD(?, INTERVAL ? DAY)
    """;

            Integer value = jdbcTemplate.queryForObject(
                    sqlSum,
                    new Object[]{start, start, i},
                    Integer.class
            );

            // Lấy ngày DATE_ADD(start, INTERVAL i DAY)
            String sqlDate = "SELECT DATE_ADD(?, INTERVAL ? DAY)";

            Date date = jdbcTemplate.queryForObject(
                    sqlDate,
                    new Object[]{start, i},
                    Date.class
            );

            Chart c = new Chart();
            c.setDate(date);
            c.setValue(value != null ? value : 0);
            list.add(c);
        }
        return list;
    }

    @Override
    public long getTotalService() {
        return dentalCareRepository.count();
    }
}
