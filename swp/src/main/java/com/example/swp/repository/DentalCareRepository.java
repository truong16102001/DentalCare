package com.example.swp.repository;

import com.example.swp.entity.Service;
import com.example.swp.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DentalCareRepository extends JpaRepository<Service, Integer> {
}
