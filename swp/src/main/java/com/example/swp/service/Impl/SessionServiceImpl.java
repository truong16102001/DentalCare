package com.example.swp.service.Impl;

import com.example.swp.entity.Session;
import com.example.swp.repository.SessionRepository;
import com.example.swp.service.SessionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SessionServiceImpl implements SessionService {
    SessionRepository sessionRepository;

    @Override
    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    @Override
    public Optional<Session> findById(int sessionId) {
        return sessionRepository.findById(sessionId);
    }

    @Override
    public Session save(Session session) {
        return sessionRepository.save(session);
    }
}
