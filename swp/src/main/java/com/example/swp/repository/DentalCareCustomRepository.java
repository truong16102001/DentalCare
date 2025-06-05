package com.example.swp.repository;

import com.example.swp.entity.Service;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DentalCareCustomRepository {
    List<Service> searchServices(String key, String value, String type, int limit, int offset);
    int countServices(String key);
}
