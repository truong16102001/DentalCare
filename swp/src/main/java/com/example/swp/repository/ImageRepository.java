package com.example.swp.repository;

import com.example.swp.entity.Image;
import com.example.swp.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
