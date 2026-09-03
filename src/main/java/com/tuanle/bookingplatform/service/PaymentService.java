package com.tuanle.bookingplatform.service;

import com.tuanle.bookingplatform.dto.PaymentRequestDTO;
import com.tuanle.bookingplatform.dto.PaymentResponseDTO;

public interface PaymentService {
    PaymentResponseDTO processPayment(PaymentRequestDTO paymentRequestDTO);
}
