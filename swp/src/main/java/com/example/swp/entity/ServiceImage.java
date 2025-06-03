package com.example.swp.entity;

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
@IdClass(ServiceImageId.class)
@Table(name = "Service_Image")
public class ServiceImage {
    @Id
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @Id
    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;
}
