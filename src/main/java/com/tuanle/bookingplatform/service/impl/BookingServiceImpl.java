package com.tuanle.bookingplatform.service.impl;

import com.tuanle.bookingplatform.dto.BookingRequestDTO;
import com.tuanle.bookingplatform.dto.BookingResponseDTO;
import com.tuanle.bookingplatform.entity.Booking;
import com.tuanle.bookingplatform.entity.Room;
import com.tuanle.bookingplatform.entity.User;
import com.tuanle.bookingplatform.repository.BookingRepository;
import com.tuanle.bookingplatform.repository.RoomRepository;
import com.tuanle.bookingplatform.repository.UserRepository;
import com.tuanle.bookingplatform.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Override
    @Transactional
    public BookingResponseDTO createBooking(BookingRequestDTO requestDTO) {
        // 1. Kiểm tra User có tồn tại không
        User user = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy User với ID: " + requestDTO.getUserId()));

        // 2. Kiểm tra Room có tồn tại và đang trống không
        Room room = roomRepository.findById(requestDTO.getRoomId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Phòng với ID: " + requestDTO.getRoomId()));

        if (!"AVAILABLE".equalsIgnoreCase(room.getStatus())) {
            throw new RuntimeException("Phòng này hiện không trống!");
        }

        // 3. Tính toán số ngày lưu trú
        long daysBetween = ChronoUnit.DAYS.between(requestDTO.getCheckInDate(), requestDTO.getCheckOutDate());
        if (daysBetween <= 0) {
            throw new RuntimeException("Ngày trả phòng phải sau ngày nhận phòng!");
        }

        // 4. Tính tổng tiền (Số ngày * Giá mỗi đêm)
        BigDecimal totalPrice = room.getPricePerNight().multiply(BigDecimal.valueOf(daysBetween));

        // 5. Tạo đối tượng Booking và lưu xuống DB
        Booking booking = Booking.builder()
                .user(user)
                .room(room)
                .checkInDate(requestDTO.getCheckInDate())
                .checkOutDate(requestDTO.getCheckOutDate())
                .totalPrice(totalPrice)
                .status("PENDING") // Chờ thanh toán
                .build();

        // Cập nhật trạng thái phòng thành đang được đặt
        room.setStatus("BOOKED");
        roomRepository.save(room);

        Booking savedBooking = bookingRepository.save(booking);

        // 6. Trả về DTO
        return BookingResponseDTO.builder()
                .id(savedBooking.getId())
                .roomName(room.getName())
                .username(user.getUsername())
                .checkInDate(savedBooking.getCheckInDate())
                .checkOutDate(savedBooking.getCheckOutDate())
                .totalPrice(savedBooking.getTotalPrice())
                .status(savedBooking.getStatus())
                .build();
    }

}
