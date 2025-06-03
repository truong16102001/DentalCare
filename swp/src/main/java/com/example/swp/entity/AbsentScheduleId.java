package com.example.swp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AbsentScheduleId implements Serializable {
    private Integer employee; // đây là ID của User (employee_id)
    private Date absentDate;
}
