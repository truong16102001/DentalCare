package com.example.swp.service.Impl;

import com.example.swp.entity.Booking;
import com.example.swp.repository.BookingRepository;
import com.example.swp.service.BookingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;
    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> searchBookings(String status, Date registered_time_from, Date registered_time_to) {
        if (status == null || status.isEmpty()) {
            return bookingRepository.findByLastUpdatedTimeBetween(registered_time_from, registered_time_to);
        }
        return bookingRepository.findByStatusAndLastUpdatedTimeBetween(status, registered_time_from, registered_time_to);
    }

    @Override
    public Optional<Booking> findById(Integer bookingId) {
        return bookingRepository.findById(bookingId);
    }

    @Override
    public void delete(Booking booking) {
        bookingRepository.delete(booking);
    }
}
