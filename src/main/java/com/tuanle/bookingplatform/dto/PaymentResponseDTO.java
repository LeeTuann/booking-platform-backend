package com.tuanle.bookingplatform.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class PaymentResponseDTO {
    private Long id;
    private Long bookingId;
    private String paymentMethod;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private String status;
}
