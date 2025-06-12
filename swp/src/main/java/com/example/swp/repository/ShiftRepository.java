package com.example.swp.repository;

import com.example.swp.entity.Shift;
import com.example.swp.entity.WorkingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Integer> {
}
