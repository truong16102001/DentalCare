package com.example.swp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(AbsentScheduleId.class)
@Table(name = "AbsentSchedule")
public class AbsentSchedule {
    @Id
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private User employee;

    @Id
    private Date absentDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;
}
