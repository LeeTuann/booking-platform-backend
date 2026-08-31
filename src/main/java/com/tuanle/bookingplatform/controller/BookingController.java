package com.tuanle.bookingplatform.controller;

import com.tuanle.bookingplatform.dto.BookingRequestDTO;
import com.tuanle.bookingplatform.dto.BookingResponseDTO;
import com.tuanle.bookingplatform.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
@Tag(name = "Booking Management", description = "Các API quản lý đặt phòng")
public class BookingController {
    private final BookingService bookingService;
    @Operation(summary = "Tạo mới lượt đặt phòng", description = "Nhận thông tin người dùng, phòng và ngày tháng để tạo Booking")
    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking(@RequestBody BookingRequestDTO requestDTO) {
        BookingResponseDTO createdBooking = bookingService.createBooking(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
    }
}
