package com.tuanle.bookingplatform.controller;


import com.tuanle.bookingplatform.dto.PaymentRequestDTO;
import com.tuanle.bookingplatform.dto.PaymentResponseDTO;
import com.tuanle.bookingplatform.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> processPayment(@RequestBody PaymentRequestDTO requestDTO) {
        PaymentResponseDTO paymentResponseDTO = paymentService.processPayment(requestDTO);
        return new ResponseEntity<>(paymentResponseDTO,HttpStatus.CREATED);
    }
}
