package com.tuanle.bookingplatform.service.impl;

import com.tuanle.bookingplatform.dto.PaymentRequestDTO;
import com.tuanle.bookingplatform.dto.PaymentResponseDTO;
import com.tuanle.bookingplatform.entity.Booking;
import com.tuanle.bookingplatform.entity.Payment;
import com.tuanle.bookingplatform.repository.BookingRepository;
import com.tuanle.bookingplatform.repository.PaymentRepository;
import com.tuanle.bookingplatform.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    @Override
    @Transactional
    public PaymentResponseDTO processPayment(PaymentRequestDTO requestDTO) {
        //Tim thong tin dat phong
        Booking booking = bookingRepository.findById(requestDTO.getBookingId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy mã đặt phòng: " + requestDTO.getBookingId()));
        //Kiem tra trang thai thanh toan
        if (!"PENDING".equalsIgnoreCase(booking.getStatus())) {
            throw new RuntimeException("Đơn đặt phòng này không ở trạng thái chờ thanh toán!");
        }
        //Tao lich su giao dich
        Payment payment = Payment.builder()
                .booking(booking)
                .paymentMethod(requestDTO.getPaymentMethod())
                .amount(booking.getTotalPrice()) // Lấy tổng tiền trực tiếp từ hóa đơn đặt phòng
                .paymentDate(LocalDateTime.now())
                .status("COMPLETED")
                .build();

        Payment savedPayment = paymentRepository.save(payment);
        // Cap nhat trang thai booking
        booking.setStatus("CONFIRMED");
        bookingRepository.save(booking);

        return PaymentResponseDTO.builder()
                .id(savedPayment.getId())
                .bookingId(booking.getId())
                .paymentMethod(savedPayment.getPaymentMethod())
                .amount(savedPayment.getAmount())
                .paymentDate(savedPayment.getPaymentDate())
                .status(savedPayment.getStatus())
                .build();
    }
}
