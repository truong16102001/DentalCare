package com.example.swp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sessionId;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "slot_id")
    private Slot slot;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "schedule_id")
    private WorkingSchedule schedule;

}