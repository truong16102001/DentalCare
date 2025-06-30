package com.example.swp.service;

import com.example.swp.entity.Booking;
import com.example.swp.entity.Chart;
import com.example.swp.entity.Service;

import java.util.List;

public interface DentalCareService {
    List<Service> getServices(String key, String value, String type, int pageIndex);
    int getTotalPages(String key);

    Service findById(int serviceId);

    List<Service> getRelatedServices(int serviceId);

    List<Booking> getBookingServicesByUserId(int userId);
    List<Booking> getBookingServicesByPhoneNumber(String phoneNumber);
    List<Chart> getChartRevenueArea(String start, int numberOfDay);
    long getTotalService();
}
