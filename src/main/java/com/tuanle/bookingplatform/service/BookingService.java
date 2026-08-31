package com.tuanle.bookingplatform.service;

import com.tuanle.bookingplatform.dto.BookingRequestDTO;
import com.tuanle.bookingplatform.dto.BookingResponseDTO;

public interface BookingService {
    BookingResponseDTO createBooking (BookingRequestDTO bookingRequestDTO);
}
