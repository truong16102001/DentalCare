package com.example.swp.service;

import com.example.swp.entity.Room;
import com.example.swp.entity.Session;

import java.util.List;
import java.util.Optional;

public interface SessionService {
    List<Session> findAll();
    Optional<Session> findById(int sessionId);
    Session save(Session session);
}
