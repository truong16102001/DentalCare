package com.example.swp.service.Impl;

import com.example.swp.entity.Booking;
import com.example.swp.repository.BookingRepository;
import com.example.swp.repository.TokenRepository;
import com.example.swp.service.BookingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;
    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }
}
