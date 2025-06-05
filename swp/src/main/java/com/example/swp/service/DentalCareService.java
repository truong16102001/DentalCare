package com.example.swp.service;

import com.example.swp.entity.Service;

import java.util.List;

public interface DentalCareService {
    List<Service> getServices(String key, String value, String type, int pageIndex);
    int getTotalPages(String key);
}
